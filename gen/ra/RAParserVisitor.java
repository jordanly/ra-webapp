// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/ra/RAParser.g4 by ANTLR 4.5.1
package ra;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RAParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RAParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RAParser#exp0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp0(RAParser.Exp0Context ctx);
	/**
	 * Visit a parse tree produced by {@link RAParser#exp_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_unit(RAParser.Exp_unitContext ctx);
	/**
	 * Visit a parse tree produced by {@link RAParser#exp_unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_unary(RAParser.Exp_unaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RAParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(RAParser.ExpContext ctx);
}