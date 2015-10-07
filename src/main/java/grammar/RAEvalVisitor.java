package grammar;

import grammar.gen.RAGrammarBaseVisitor;
import grammar.gen.RAGrammarParser;

/**
 * Created by jordanly on 10/7/15.
 */
public class RAEvalVisitor extends RAGrammarBaseVisitor<String> {
    @Override
    public String visitUnaryExp(RAGrammarParser.UnaryExpContext ctx) {
        StringBuilder output = new StringBuilder();

        String operation = ctx.getChild(0).getText();
        switch (operation) {
            case "\\select":
                output.append("SELECT * FROM");
                break;
            case "\\proj":
                output.append("SELECT TODO");
                break;
            case "\\rename":
                output.append("TODO");
        }

        output.append(ctx.getChild(2).getText());
        System.out.println(output.toString());
        return output.toString();
    }

    private String extractOperatorOption(String val) {
        return val.substring(2, val.length() - 1);
    }
}
