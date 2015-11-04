// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/ra.grammar/RAGrammar.g4 by ANTLR 4.5.1
package ra.grammar.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RAGrammarParser}.
 */
public interface RAGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RAGrammarParser#exp0}.
	 * @param ctx the parse tree
	 */
	void enterExp0(RAGrammarParser.Exp0Context ctx);
	/**
	 * Exit a parse tree produced by {@link RAGrammarParser#exp0}.
	 * @param ctx the parse tree
	 */
	void exitExp0(RAGrammarParser.Exp0Context ctx);
	/**
	 * Enter a parse tree produced by the {@code tableExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 */
	void enterTableExp(RAGrammarParser.TableExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 */
	void exitTableExp(RAGrammarParser.TableExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 */
	void enterParenExp(RAGrammarParser.ParenExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 */
	void exitParenExp(RAGrammarParser.ParenExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unitExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 */
	void enterUnitExp(RAGrammarParser.UnitExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unitExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 */
	void exitUnitExp(RAGrammarParser.UnitExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExp(RAGrammarParser.UnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExp(RAGrammarParser.UnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleUnaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterSingleUnaryExp(RAGrammarParser.SingleUnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleUnaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitSingleUnaryExp(RAGrammarParser.SingleUnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code joinExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterJoinExp(RAGrammarParser.JoinExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code joinExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitJoinExp(RAGrammarParser.JoinExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExp(RAGrammarParser.BinaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExp(RAGrammarParser.BinaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 */
	void enterSingleTermExp(RAGrammarParser.SingleTermExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 */
	void exitSingleTermExp(RAGrammarParser.SingleTermExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code joinTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 */
	void enterJoinTermExp(RAGrammarParser.JoinTermExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code joinTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 */
	void exitJoinTermExp(RAGrammarParser.JoinTermExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 */
	void enterBinaryTermExp(RAGrammarParser.BinaryTermExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 */
	void exitBinaryTermExp(RAGrammarParser.BinaryTermExpContext ctx);
}