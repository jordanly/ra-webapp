// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/ra/grammar/RAGrammar.g4 by ANTLR 4.5.1
package ra.grammar.gen;
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
	 * Visit a parse tree produced by the {@code tableExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableExp(RAGrammarParser.TableExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExp(RAGrammarParser.ParenExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unitExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitExp(RAGrammarParser.UnitExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(RAGrammarParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code singleUnaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleUnaryExp(RAGrammarParser.SingleUnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code joinExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinExp(RAGrammarParser.JoinExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExp}
	 * labeled alternative in {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExp(RAGrammarParser.BinaryExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code singleTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleTermExp(RAGrammarParser.SingleTermExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code joinTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinTermExp(RAGrammarParser.JoinTermExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryTermExp}
	 * labeled alternative in {@link RAGrammarParser#exp1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryTermExp(RAGrammarParser.BinaryTermExpContext ctx);
}