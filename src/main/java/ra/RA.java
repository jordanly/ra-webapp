package ra;
import grammar.RAEvalVisitor;
import grammar.gen.RAGrammarLexer;
import grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;


/**
 * Created by jordanly on 10/6/15.
 */
public class RA {
    public static void main(String[] args) {
        ANTLRInputStream inputStream = new ANTLRInputStream("\\select_{test=test1} Likes;");
        RAGrammarLexer lexer = new RAGrammarLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RAGrammarParser parser = new RAGrammarParser(tokenStream);

        try {
//            RAGrammarParser.Exp0Context context = parser.exp0();
//            System.out.println(context);

            ParseTree tree = parser.exp0();
            System.out.println("value = ");
            System.out.println(new RAEvalVisitor().visit(tree));

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
