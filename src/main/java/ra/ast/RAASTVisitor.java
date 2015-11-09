package ra.ast;

import ra.grammar.gen.RAGrammarBaseVisitor;
import ra.grammar.gen.RAGrammarParser;

/**
 * Created by jordanly on 11/9/15.
 */
public class RAASTVisitor extends RAGrammarBaseVisitor<RAASTNode> {
    @Override
    public RAASTNode visitBinaryExp(RAGrammarParser.BinaryExpContext ctx) {
        RAASTNode left = visit(ctx.getChild(0));
        String operation = ctx.getChild(1).getText();
        RAASTNode right = visit(ctx.getChild(2));

        return generateBinaryNode(left, right, operation);
    }

    @Override
    public RAASTNode visitBinaryTermExp(RAGrammarParser.BinaryTermExpContext ctx) {
        RAASTNode left = visit(ctx.getChild(0));
        String operation = ctx.getChild(1).getText();
        RAASTNode right = visit(ctx.getChild(2));

        return generateBinaryNode(left, right, operation);
    }

    private RAASTNode generateBinaryNode(RAASTNode left, RAASTNode right, String operation) {
        RAASTNode node = new RAASTNode(operation, "");
        node.addChild(left);
        node.addChild(right);

        return node;
    }

    @Override
    public RAASTNode visitExp0(RAGrammarParser.Exp0Context ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public RAASTNode visitJoinExp(RAGrammarParser.JoinExpContext ctx) {
        RAASTNode left = visit(ctx.getChild(0));
        String operation = ctx.getChild(1).getText();
        String subscript = extractOperatorOption(ctx.getChild(2).getText());
        RAASTNode right = visit(ctx.getChild(3));

        return generateJoinNode(left, operation, subscript, right);
    }

    @Override
    public RAASTNode visitJoinTermExp(RAGrammarParser.JoinTermExpContext ctx) {
        RAASTNode left = visit(ctx.getChild(0));
        String operation = ctx.getChild(1).getText();
        String subscript = extractOperatorOption(ctx.getChild(2).getText());
        RAASTNode right = visit(ctx.getChild(3));

        return generateJoinNode(left, operation, subscript, right);
    }

    private RAASTNode generateJoinNode(RAASTNode left, String operation, String subscript, RAASTNode right) {
        RAASTNode node = new RAASTNode(operation, subscript);
        node.addChild(left);
        node.addChild(right);

        return node;
    }

    @Override
    public RAASTNode visitParenExp(RAGrammarParser.ParenExpContext ctx) {
        return visit(ctx.getChild(1));
    }

    @Override
    public RAASTNode visitSingleTermExp(RAGrammarParser.SingleTermExpContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public RAASTNode visitSingleUnaryExp(RAGrammarParser.SingleUnaryExpContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public RAASTNode visitTableExp(RAGrammarParser.TableExpContext ctx) {
        return new RAASTNode(ctx.getChild(0).getText(), "");
    }

    @Override
    public RAASTNode visitUnaryExp(RAGrammarParser.UnaryExpContext ctx) {
        String operation = ctx.getChild(0).getText();
        String subscript = extractOperatorOption(ctx.getChild(1).getText());
        RAASTNode child = visit(ctx.getChild(2));

        RAASTNode node = new RAASTNode(operation, subscript);
        node.addChild(child);

        return node;
    }

    @Override
    public RAASTNode visitUnitExp(RAGrammarParser.UnitExpContext ctx) {
        return visit(ctx.getChild(0));
    }
    private String extractOperatorOption(String val) {
        return val.substring(2, val.length() - 1); // remove "_{" + "}"
    }
}
