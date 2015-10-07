// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/grammar/RAGrammar.g4 by ANTLR 4.5.1
package grammar.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RAGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RAGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RAGrammarParser#exp0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp0(RAGrammarParser.Exp0Context ctx);
	/**
	 * Visit a parse tree produced by {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_unit(RAGrammarParser.Exp_unitContext ctx);
	/**
	 * Visit a parse tree produced by {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_unary(RAGrammarParser.Exp_unaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(RAGrammarParser.ExpContext ctx);
}