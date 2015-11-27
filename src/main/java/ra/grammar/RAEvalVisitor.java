package ra.grammar;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import ra.Query;
import ra.exceptions.RAException;
import ra.grammar.error.RAErrorParser;
import ra.grammar.gen.RAGrammarBaseVisitor;
import ra.grammar.gen.RAGrammarParser;
import ra.RA;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * RAEvalVisitor contains methods that processes nodes in the AST created by
 * the grammar in 'RAGrammar.g4' (which represents an RA query) and converts
 * them to their corresponding SQL statement. This class will build the query
 * bottom-up.
 *
 * The main call to parse a query will be the method 'visitExp0' as exp0 will
 * always be the root of the RA query (as dictated by the grammar).
 *
 * RAEvalVisitor will attempt to validate each node by sending the command
 * generated to RAErrorParser (which executes each query and contains methods
 * for dealing with different types of exceptions. Right now, RAEvalVisitor will
 * only try to validate unit, unary, binary, and join nodes as they are the only
 * ones that currently generate non-trivial statements.
 */
public class RAEvalVisitor extends RAGrammarBaseVisitor<String> {
    private int tempCount;
    private RA ra;
    private RAErrorParser errorParser;
    private Query query;

    public RAEvalVisitor(RA ra, Query query) {
        this.ra = ra;
        this.query = query;

        this.tempCount = 0;
        this.errorParser = new RAErrorParser(ra);
    }

    @Override
    public String visit(ParseTree tree) {
        if (!query.isValid()) {
            return "";
        }

        return super.visit(tree);
    }

    @Override
    public String visitExp0(RAGrammarParser.Exp0Context ctx) {
        tempCount = 0;

        return String.format(" SELECT * FROM ( %s ) %s ",
                visit(ctx.getChild(0)), generateAlias());
    }

    @Override
    public String visitTableExp(RAGrammarParser.TableExpContext ctx) {
        return ctx.getText().toLowerCase();
    }

    @Override
    public String visitParenExp(RAGrammarParser.ParenExpContext ctx) {
        return String.format(" ( %s ) ", visit(ctx.getChild(1)));
    }

    @Override
    public String visitUnitExp(RAGrammarParser.UnitExpContext ctx) {
        // Corresponds with #unitExp directive not unit_exp rule
        String command = String.format(" ( SELECT * FROM %s %s ) ",
                visit(ctx.getChild(0)), generateAlias());

        return (command != null && errorParser.validate(query, command,
                RAErrorParser.UNIT_ERRORS, ctx) ? command : "ERROR");
    }

    @Override
    public String visitUnaryExp(RAGrammarParser.UnaryExpContext ctx) {
        String operation = ctx.getChild(0).getText();

        String command = null;
        switch (operation) {
            case "\\select":
                command = String.format("SELECT * FROM ( %s ) %s WHERE %s ",
                        visit(ctx.getChild(2)), generateAlias(),
                        extractOperatorOption(ctx.getChild(1).getText(), operation, ctx));

                break;
            case "\\project":
                command = String.format("SELECT DISTINCT %s FROM ( %s ) %s ",
                        extractOperatorOption(ctx.getChild(1).getText(), operation, ctx),
                        visit(ctx.getChild(2)), generateAlias());
                break;
            case "\\rename":
                // Get the columns that we will be renaming from the subquery
                String subQuery = String.format("SELECT * FROM ( %s ) %s ;",
                        visit(ctx.getChild(2)),
                        generateAlias()
                );
                ResultSetMetaData rsmd;
                String[] columnNames;
                try {
                    rsmd = ra.evaluateSQLQuery(subQuery).getMetaData();
                    columnNames = new String[rsmd.getColumnCount()];
                    for (int i = 0; i < columnNames.length; i++) {
                        columnNames[i] = rsmd.getColumnName(i + 1);
                    }
                } catch (SQLException e) {
                    query.setException(new RAException(
                            ctx.getStart(),
                            ctx.getStop(),
                            "SQLException: Unable to rename attributes"
                    ));
                    break;
                }

                // Make sure number of attributes are same
                String[] newNames = extractOperatorOption(
                        ctx.getChild(1).getText(),
                        operation,
                        ctx
                ).split(",");
                if (newNames.length != columnNames.length) {
                    query.setException(new RAException(
                            ctx.getStart(),
                            ctx.getStop(),
                            "RAException: Number of rename columns does not match actual -- "
                                    + "Input: " + Arrays.toString(newNames)
                                    + " vs "
                                    + "Actual: " + Arrays.toString(columnNames)
                    ));
                    break;
                }

                // Basic error checking done, generate final rename query
                StringBuilder output = new StringBuilder();
                output.append("SELECT ");
                for (int i = 0; i < newNames.length; i++) {
                    output.append(columnNames[i] + " AS " + newNames[i] + ",");
                }
                output.deleteCharAt(output.length() - 1); // Delete last ','
                output.append(
                        String.format(" FROM ( %s ) %s",
                                visit(ctx.getChild(2)), generateAlias())
                );

                command = output.toString();
                break;
        }

        return (command != null && errorParser.validate(query, command,
                RAErrorParser.UNARY_ERRORS, ctx) ? command : "ERROR");
    }

    @Override
    public String visitSingleUnaryExp(RAGrammarParser.SingleUnaryExpContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public String visitJoinExp(RAGrammarParser.JoinExpContext ctx) {
        String left = visit(ctx.getChild(0));
        String condition = extractOperatorOption(
                ctx.getChild(2).getText(),
                ctx.getChild(1).getText(),
                ctx
        );
        String right = visit(ctx.getChild(3));

        return generateJoinStatement(left, condition, right);
    }

    @Override
    public String visitBinaryExp(RAGrammarParser.BinaryExpContext ctx) {
        String left = visit(ctx.getChild(0));
        String operation = ctx.getChild(1).getText();
        String right = visit(ctx.getChild(2));

        return generateBinaryStatement(left, right, operation, ctx);
    }

    @Override
    public String visitSingleTermExp(RAGrammarParser.SingleTermExpContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public String visitJoinTermExp(RAGrammarParser.JoinTermExpContext ctx) {
        String left = visit(ctx.getChild(0));
        String condition = extractOperatorOption(
                ctx.getChild(2).getText(),
                ctx.getChild(1).getText(),
                ctx
        );
        String right = visit(ctx.getChild(3));

        return generateJoinStatement(left, condition, right);
    }

    private String generateJoinStatement(String left, String condition, String right) {
        return String.format("( %s ) %s JOIN ( %s ) %s ON ( %s )",
                left, generateAlias(),
                right, generateAlias(),
                condition);
    }

    @Override
    public String visitBinaryTermExp(RAGrammarParser.BinaryTermExpContext ctx) {
        String operation = ctx.getChild(1).getText();
        String left = visit(ctx.getChild(0));
        String right = visit(ctx.getChild(2));

        return generateBinaryStatement(left, right, operation, ctx);
    }

    private String generateBinaryStatement(String leftChild, String rightChild,
                                           String operation, ParserRuleContext ctx) {
        String command = null;
        switch (operation) {
            case "\\join":
                command = String.format("( %s ) %s NATURAL JOIN ( %s ) %s",
                        leftChild, generateAlias(),
                        rightChild, generateAlias());
                break;
            case "\\cross":
                command = String.format("( %s ) %s CROSS JOIN ( %s ) %s",
                        leftChild, generateAlias(),
                        rightChild, generateAlias());
                break;
            case "\\union":
                command = String.format("SELECT * FROM ( %s ) %s UNION SELECT * FROM ( %s ) %s",
                        leftChild, generateAlias(),
                        rightChild, generateAlias());
                break;
            case "\\diff":
                command = String.format("SELECT * FROM ( %s ) %s EXCEPT SELECT * FROM ( %s ) %s",
                        leftChild, generateAlias(),
                        rightChild, generateAlias());
                break;
            case "\\intersect":
                command = String.format("SELECT * FROM ( %s ) %s INTERSECT SELECT * FROM ( %s ) %s",
                        leftChild, generateAlias(),
                        rightChild, generateAlias());
                break;
        }

        return (command != null && errorParser.validate(query, command,
                RAErrorParser.BINARY_ERRORS, ctx) ? command : "ERROR");
    }

    /**
     * This visitor only generates statements that are designed for a
     * PostgreSQL database. One detail to keep in mind is that when having nested
     * subqueries (which is how the query is built), you must give an alias after
     * a 'FROM x'.
     *
     * The method generateAlias() returns a temporary, unique string for use
     * after a 'FROM x' statement.
     */
    private String generateAlias() {
        return "t" + tempCount++;
    }

    private String extractOperatorOption(String val, String operation, ParserRuleContext ctx) {
        String option = val.substring(2, val.length() - 1); // remove "_{" + "}"
        return (errorParser.validateOperatorOption(query, option, operation, ctx)
                ? option : "ERROR");
    }
}
