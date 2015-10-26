package ra.grammar;

import org.antlr.v4.runtime.tree.ParseTree;
import ra.Query;
import ra.exceptions.RAException;
import ra.grammar.gen.RAGrammarBaseVisitor;
import ra.grammar.gen.RAGrammarParser;
import ra.RA;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RAEvalVisitor extends RAGrammarBaseVisitor<String> {
    private Random random = new Random(12345);
    private Set<Integer> usedTempNumbers = new HashSet<>();
    private RA ra;
    private RAErrorParser errorParser;
    private Query query;

    public RAEvalVisitor(RA ra, Query query) {
        this.ra = ra;
        this.query = query;
        this.errorParser = new RAErrorParser(ra);
    }

    @Override
    public String visit(ParseTree tree) {
        // If exception has already been raised, do not continue
        if (!query.isValid()) {
            return "";
        }

        return super.visit(tree);
    }

    @Override
    public String visitExp0(RAGrammarParser.Exp0Context ctx) {
        usedTempNumbers.clear(); // Clear previous temp variables used for aliasing

        return String.format(" SELECT * FROM ( %s ) %s ",
                visit(ctx.getChild(0)), generateAlias(random));
    }

    @Override
    public String visitTableExp(RAGrammarParser.TableExpContext ctx) {
        String tableName = ctx.getText().toLowerCase();

        // Check if table exists in database
        try {
            ra.evaluateSQLQuery("SELECT * FROM " + tableName + ";");
        } catch (SQLException e) {
            query.setException(new RAException(
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine(),
                    String.format("RAException: No such table '%s'", tableName),
                    e
            ));
        }

        return tableName;
    }

    @Override
    public String visitParenExp(RAGrammarParser.ParenExpContext ctx) {
        return String.format(" ( %s ) ", visit(ctx.getChild(1)));
    }

    @Override
    public String visitUnitExp(RAGrammarParser.UnitExpContext ctx) {
        // Corresponds with #unitExp directive not unit_exp rule
        // Don't need error checking, done in other rules
        return String.format(" ( SELECT * FROM %s %s ) ",
                visit(ctx.getChild(0)), generateAlias(random));
    }

    @Override
    public String visitUnaryExp(RAGrammarParser.UnaryExpContext ctx) {
        String operation = ctx.getChild(0).getText();

        String command = null;
        switch (operation) {
            case "\\select":
                command = String.format("SELECT * FROM ( %s ) %s WHERE %s ",
                        visit(ctx.getChild(2)), generateAlias(random),
                        extractOperatorOption(ctx.getChild(1).getText()));

                break;
            case "\\project":
                command = String.format("SELECT DISTINCT %s FROM ( %s ) %s ",
                        extractOperatorOption(ctx.getChild(1).getText()),
                        visit(ctx.getChild(2)), generateAlias(random));
                break;
            case "\\rename":
                // Get column names so we can rename them
                String subQuery = String.format("SELECT * FROM ( %s ) %s ;",
                        visit(ctx.getChild(2)),
                        generateAlias(random)
                );

                ResultSetMetaData rsmd; // Validate subquery
                String[] columnNames;
                try {
                    rsmd = ra.evaluateSQLQuery(subQuery).getMetaData();
                    columnNames = new String[rsmd.getColumnCount()];
                    for (int i = 0; i < columnNames.length; i++) {
                        columnNames[i] = rsmd.getColumnName(i + 1);
                    }
                } catch (SQLException e) {
                    query.setException(new RAException(
                            ctx.getStart().getLine(),
                            ctx.getStart().getCharPositionInLine(),
                            "SQLException: Unable to rename attributes",
                            e
                    ));
                    break;
                }

                String[] newNames = extractOperatorOption(ctx.getChild(1).getText()).split(",");
                if (newNames.length != columnNames.length) {
                    query.setException(new RAException(
                            ctx.getStart().getLine(),
                            ctx.getStart().getCharPositionInLine(),
                            "RAException: Number of rename columns does not match actual -- "
                                    + "Input: " + Arrays.toString(newNames)
                                    + " vs "
                                    + "Actual: " + Arrays.toString(columnNames)
                    ));
                    break;
                }

                // Generate actually rename query
                StringBuilder output = new StringBuilder();
                output.append("SELECT ");
                for (int i = 0; i < newNames.length; i++) {
                    output.append(columnNames[i] + " AS " + newNames[i] + ",");
                }
                output.deleteCharAt(output.length() - 1); // delete last ,
                output.append(
                        String.format(" FROM ( %s ) %s",
                                visit(ctx.getChild(2)), generateAlias(random))
                );

                command = output.toString();
                break;
        }

        return (command == null || !errorParser.validateUnaryExp(query, command, ctx) ? "ERROR" : command);
    }

    @Override
    public String visitSingleUnaryExp(RAGrammarParser.SingleUnaryExpContext ctx) {
        return visit(ctx.getChild(0)); // Visit single unaryExp child
    }

    @Override
    public String visitJoinExp(RAGrammarParser.JoinExpContext ctx) {
        // TODO multiple conditions such as AND/OR
        String left = visit(ctx.getChild(0));
        String condition = extractOperatorOption(ctx.getChild(2).getText());
        String right = visit(ctx.getChild(3));

        return String.format("( %s ) %s JOIN ( %s ) %s ON ( %s )",
                left, generateAlias(random),
                right, generateAlias(random),
                condition);
    }

    @Override
    public String visitBinaryExp(RAGrammarParser.BinaryExpContext ctx) {
        String left = visit(ctx.getChild(0));
        String operation = ctx.getChild(1).getText();
        String right = visit(ctx.getChild(2));

        return generateBinaryStatement(left, right, operation);
    }

    @Override
    public String visitSingleTermExp(RAGrammarParser.SingleTermExpContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public String visitJoinTermExp(RAGrammarParser.JoinTermExpContext ctx) {
        return visitChildren(ctx); // TODO
    }

    @Override
    public String visitBinaryTermExp(RAGrammarParser.BinaryTermExpContext ctx) {
        String operation = ctx.getChild(1).getText();
        String left = visit(ctx.getChild(0));
        String right = visit(ctx.getChild(2));

        return generateBinaryStatement(left, right, operation);
    }

    private String generateBinaryStatement(String leftChild, String rightChild,
                                           String operation) {
        switch (operation) {
            case "\\join":
                return String.format("( %s ) %s NATURAL JOIN ( %s ) %s",
                        leftChild, generateAlias(random),
                        rightChild, generateAlias(random));
            case "\\cross":
                return String.format("( %s ) %s CROSS JOIN ( %s ) %s",
                        leftChild, generateAlias(random),
                        rightChild, generateAlias(random));
            case "\\union":
                return String.format("SELECT * FROM ( %s ) %s UNION SELECT * FROM ( %s ) %s",
                        leftChild, generateAlias(random),
                        rightChild, generateAlias(random));
            case "\\diff":
                return String.format("SELECT * FROM ( %s ) %s EXCEPT SELECT * FROM ( %s ) %s",
                        leftChild, generateAlias(random),
                        rightChild, generateAlias(random));
            case "\\intersect":
                return String.format("SELECT * FROM ( %s ) %s INTERSECT SELECT * FROM ( %s ) %s",
                        leftChild, generateAlias(random),
                        rightChild, generateAlias(random));
        }

        return "ERROR"; // Should never get here
    }

    private String generateAlias(Random rand) {
        int newVal;
        do {
            newVal = rand.nextInt(100);
        } while (usedTempNumbers.contains(newVal));

        return "t" + newVal;
    }

    private String extractOperatorOption(String val) {
        return val.substring(2, val.length() - 1); // remove "_{" + "}"
    }
}
