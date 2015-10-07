// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/ra/RAParser.g4 by ANTLR 4.5.1
package ra;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RAParser}.
 */
public interface RAParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RAParser#exp0}.
	 * @param ctx the parse tree
	 */
	void enterExp0(RAParser.Exp0Context ctx);
	/**
	 * Exit a parse tree produced by {@link RAParser#exp0}.
	 * @param ctx the parse tree
	 */
	void exitExp0(RAParser.Exp0Context ctx);
	/**
	 * Enter a parse tree produced by {@link RAParser#exp_unit}.
	 * @param ctx the parse tree
	 */
	void enterExp_unit(RAParser.Exp_unitContext ctx);
	/**
	 * Exit a parse tree produced by {@link RAParser#exp_unit}.
	 * @param ctx the parse tree
	 */
	void exitExp_unit(RAParser.Exp_unitContext ctx);
	/**
	 * Enter a parse tree produced by {@link RAParser#exp_unary}.
	 * @param ctx the parse tree
	 */
	void enterExp_unary(RAParser.Exp_unaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RAParser#exp_unary}.
	 * @param ctx the parse tree
	 */
	void exitExp_unary(RAParser.Exp_unaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RAParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(RAParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link RAParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(RAParser.ExpContext ctx);
}