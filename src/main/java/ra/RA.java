package ra;
import grammar.gen.RAGrammarLexer;
import grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.*;


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
            RAGrammarParser.Exp0Context context = parser.exp0();
            System.out.println(context);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
