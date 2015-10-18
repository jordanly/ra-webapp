package grammar;

import grammar.gen.RAGrammarBaseVisitor;
import grammar.gen.RAGrammarParser;
import ra.RA;

import java.sql.ResultSetMetaData;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by jordanly on 10/7/15.
 */
public class RAEvalVisitor extends RAGrammarBaseVisitor<String> {
    private Random random;
    private Set<Integer> usedTempNumbers;
    private RA ra;

    public RAEvalVisitor(RA ra) {
        this.random = new Random(12345);
        this.usedTempNumbers = new HashSet<>();
        this.ra = ra;
    }

    @Override
    public String visitExp0(RAGrammarParser.Exp0Context ctx) {
        usedTempNumbers.clear(); // Clear previous temp variables used for aliasing

        return String.format(" SELECT * FROM ( %s ) %s ",
                visit(ctx.getChild(0)), generateAlias(random));
    }

    @Override
    public String visitTableExp(RAGrammarParser.TableExpContext ctx) {
        return ctx.getText(); // Child is just the table name
    }

    @Override
    public String visitParenExp(RAGrammarParser.ParenExpContext ctx) {
        return String.format(" ( %s ) ", visit(ctx.getChild(1)));
    }

    @Override
    public String visitUnitExp(RAGrammarParser.UnitExpContext ctx) {
        // Corresponds with #unitExp directive not unit_exp rule
        return String.format(" ( SELECT * FROM %s %s ) ",
                visit(ctx.getChild(0)), generateAlias(random));
    }

    @Override
    public String visitUnaryExp(RAGrammarParser.UnaryExpContext ctx) {
        StringBuilder output = new StringBuilder();

        String operation = ctx.getChild(0).getText(); // Operation is first child always
        switch (operation) {
            case "\\select":
                return String.format("SELECT * FROM ( %s ) %s WHERE %s ",
                        visit(ctx.getChild(2)), generateAlias(random),
                        extractOperatorOption(ctx.getChild(1).getText()));
            case "\\project":
                return String.format("SELECT DISTINCT %s FROM ( %s ) %s ",
                        extractOperatorOption(ctx.getChild(1).getText()),
                        visit(ctx.getChild(2)), generateAlias(random));
            case "\\rename":
                // Get column names so we can rename them
                // TODO replace with call to RA?
                String subQuery = "SELECT * FROM ( "
                        + visit(ctx.getChild(2))
                        + " ) " + generateAlias(random) + " ; ";

                try { // TODO throw error?
                    ResultSetMetaData rsmd = ra.evaluateSQLQuery(subQuery).getMetaData();
                    String[] newNames = extractOperatorOption(ctx.getChild(1).getText()).split(",");

                    output.append("SELECT ");
                    for (int i = 0; i < newNames.length; i++) {
                        output.append(rsmd.getColumnName(i + 1) + " AS " + newNames[i] + ",");
                    }
                    output.deleteCharAt(output.length() - 1); // delete last ,

                    output.append(" FROM ");
                    output.append(" ( ");
                    output.append(visit(ctx.getChild(2)));
                    output.append(" ) " + generateAlias(random));
                } catch (Exception e) {
                    System.out.println(e.toString());
                    return "ERROR"; // TODO better error response
                }
                break;
        }

        return output.toString();
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

        return "ERROR"; // TODO come up with error scheme...
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
