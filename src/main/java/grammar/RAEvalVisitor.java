package grammar;

import grammar.gen.RAGrammarBaseVisitor;
import grammar.gen.RAGrammarParser;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by jordanly on 10/7/15.
 */
public class RAEvalVisitor extends RAGrammarBaseVisitor<String> {
    private Random random = new Random(12345);
    private Set<Integer> usedTempNumbers = new HashSet<>();


    @Override
    public String visitExp0(RAGrammarParser.Exp0Context ctx) {
        usedTempNumbers.clear();
        return "SELECT * FROM ( "
                + visit(ctx.getChild(0))
                + " ) " + generateAlias(random); // Return value from final exp
    }

    @Override
    public String visitTableExp(RAGrammarParser.TableExpContext ctx) {
        return ctx.getText(); // Child is just the table name
    }

    @Override
    public String visitParenExp(RAGrammarParser.ParenExpContext ctx) {
        return "( " + visit(ctx.getChild(1)) + " )"; // TODO check
    }

    @Override
    public String visitUnitExp(RAGrammarParser.UnitExpContext ctx) {
        // Corresponds with #unitExp directive not unit_exp rule
        return " ( SELECT * FROM "
                + visit(ctx.getChild(0))
                + " " + generateAlias(random) + " ) ";
//        return visit(ctx.getChild(0));
    }

    @Override
    public String visitUnaryExp(RAGrammarParser.UnaryExpContext ctx) {
        StringBuilder output = new StringBuilder();

        // TODO use string format as opposed to building from stringbuilder
        String operation = ctx.getChild(0).getText(); // Operation is first child always
        switch (operation) {
            case "\\select":
                output.append("SELECT * FROM ");
                output.append(" ( ");
                output.append(visit(ctx.getChild(2)));
                output.append(" ) " + generateAlias(random));

                output.append(" WHERE ");
                // Get select conditions (ie _{beer = 'Amstel'})
                output.append(extractOperatorOption(ctx.getChild(1).getText()));
                break;
            case "\\project":
                output.append("SELECT DISTINCT ");
                // Get attribute list (ie _{ex1, ex2})
                output.append(extractOperatorOption(ctx.getChild(1).getText()));

                output.append(" FROM ");
                output.append(" ( ");
                output.append(visit(ctx.getChild(2)));
                output.append(" ) " + generateAlias(random));
                break;
            case "\\rename":
                output.append("..."); // TODO get column names and rename
        }

        return output.toString();
    }

    @Override
    public String visitSingleUnaryExp(RAGrammarParser.SingleUnaryExpContext ctx) {
        return visit(ctx.getChild(0)); // Visit single unaryExp child
    }

    @Override
    public String visitJoinExp(RAGrammarParser.JoinExpContext ctx) {
        StringBuilder output = new StringBuilder();

        // TODO multiple conditions such as AND/OR
        String left = visit(ctx.getChild(0));
        String condition = extractOperatorOption(ctx.getChild(2).getText());
        String right = visit(ctx.getChild(3));
        output.append(String.format("( %s ) %s JOIN ( %s ) %s ON ( %s )",
                left, generateAlias(random),
                right, generateAlias(random),
                condition));

        return output.toString();
    }

    @Override
    public String visitBinaryExp(RAGrammarParser.BinaryExpContext ctx) {
        StringBuilder output = new StringBuilder();
        String operation = ctx.getChild(1).getText();

        String left = visit(ctx.getChild(0));
        String right = visit(ctx.getChild(2));
        switch (operation) {
            case "\\join":
                output.append(String.format("( %s ) %s NATURAL JOIN ( %s ) %s",
                        left, generateAlias(random),
                        right, generateAlias(random)));
                break;
            case "\\cross":
                output.append(String.format("( %s ) %s CROSS JOIN ( %s ) %s",
                        left, generateAlias(random),
                        right, generateAlias(random)));
                break;
            case "\\union":
                output.append(String.format("SELECT * FROM ( %s ) %s UNION SELECT * FROM ( %s ) %s",
                        left, generateAlias(random),
                        right, generateAlias(random)));
                break;
            case "\\diff":
                output.append(String.format("SELECT * FROM ( %s ) %s EXCEPT SELECT * FROM ( %s ) %s",
                        left, generateAlias(random),
                        right, generateAlias(random)));
                break;
            case "\\intersect":
                output.append(String.format("SELECT * FROM ( %s ) %s INTERSECT SELECT * FROM ( %s ) %s",
                        left, generateAlias(random),
                        right, generateAlias(random)));
                break;
        }

        return output.toString();
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
