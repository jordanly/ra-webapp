// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/grammar/RAGrammar.g4 by ANTLR 4.5.1
package grammar.gen;
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
	 * Enter a parse tree produced by {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 */
	void enterExp_unit(RAGrammarParser.Exp_unitContext ctx);
	/**
	 * Exit a parse tree produced by {@link RAGrammarParser#exp_unit}.
	 * @param ctx the parse tree
	 */
	void exitExp_unit(RAGrammarParser.Exp_unitContext ctx);
	/**
	 * Enter a parse tree produced by {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 */
	void enterExp_unary(RAGrammarParser.Exp_unaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RAGrammarParser#exp_unary}.
	 * @param ctx the parse tree
	 */
	void exitExp_unary(RAGrammarParser.Exp_unaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(RAGrammarParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link RAGrammarParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(RAGrammarParser.ExpContext ctx);
}