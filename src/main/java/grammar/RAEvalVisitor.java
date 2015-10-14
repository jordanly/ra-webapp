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
        return visit(ctx.getChild(0)); // Return value from final exp
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
        return visit(ctx.getChild(0)); // Corresponds #unitExp directive not unit_exp rule
    }

    @Override
    public String visitUnaryExp(RAGrammarParser.UnaryExpContext ctx) {
        StringBuilder output = new StringBuilder();

        String operation = ctx.getChild(0).getText(); // Operation is first child always
        switch (operation) {
            case "\\select":
                output.append("SELECT * FROM ");
                output.append(visit(ctx.getChild(2)));
                output.append(" WHERE ");
                // Get select conditions (ie _{beer = 'Amstel'})
                output.append(extractOperatorOption(ctx.getChild(1).getText()));
                break;
            case "\\project":
                output.append("SELECT ");
                // Get attribute list (ie _{ex1, ex2})
                output.append(extractOperatorOption(ctx.getChild(1).getText()));
                output.append(" FROM ");
                output.append(visit(ctx.getChild(2)));
                output.append(" " + generateAlias(random));
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
        return super.visitJoinExp(ctx);
    }

    @Override
    public String visitBinaryExp(RAGrammarParser.BinaryExpContext ctx) {
        StringBuilder output = new StringBuilder();
        String operation = ctx.getChild(1).getText();

        output.append("( " + visit(ctx.getChild(0)) + " )");
        switch (operation) {
            case "\\join":
                output.append(" NATURAL JOIN ");
                break;
            case "\\cross":
                output.append(" CROSS JOIN ");
                break;
            case "\\union":
                output.append(" UNION ");
                break;
            case "\\diff":
                output.append(" EXCEPT ");
                break;
            case "\\intersect":
                output.append(" INTERSECT ");
                break;
        }
        output.append("( " + visit(ctx.getChild(2)) + " )");

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
