package ra;
import org.json.JSONArray;
import ra.grammar.RAEvalVisitor;
import ra.grammar.RAErrorListener;
import ra.grammar.gen.RAGrammarLexer;
import ra.grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import util.ResultSetUtilities;
import util.TempUtil;

import java.sql.*;

/**
 * Created by jordanly on 10/6/15.
 */
public class RA {
    private Connection dbConnection;

    public RA(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ResultSet evaluateRAQuery(String query) {
        ANTLRInputStream inputStream = new ANTLRInputStream(query);
        RAGrammarLexer lexer = new RAGrammarLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RAGrammarParser parser = new RAGrammarParser(tokenStream);
        parser.addErrorListener(new RAErrorListener());

        ParseTree tree = parser.exp0();
        String sqlQuery = new RAEvalVisitor(this).visit(tree);

        return evaluateSQLQuery(sqlQuery);
    }

    public ResultSet evaluateSQLQuery(String query) {
        try {
            Statement st = dbConnection.createStatement();
            st.execute(query);

            return st.getResultSet();
        } catch (Exception e) {
            System.out.println(e.toString()); // TODO could not query db
        }

        return null;
    }

    public static void main(String[] args) {
        String query = ("\\project_{bar} (\n" +
                "\t\\select_{drinker='Eve'} Likes\n" +
                "\t\\join\n" +
                "\t\\select_{price<=2.75} Serves\n" +
                ");");
        RA ra = new RA(TempUtil.createLocalDBConnection());
        JSONArray rows = ResultSetUtilities.toJSONArray(ra.evaluateRAQuery(query));

        System.out.println(rows);
    }
}
