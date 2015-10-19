package ra;
import grammar.RAEvalVisitor;
import grammar.gen.RAGrammarLexer;
import grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import util.ResultSetUtilities;

import java.net.URI;
import java.sql.*;
import java.util.Properties;


/**
 * Created by jordanly on 10/6/15.
 */
public class RA {
    public static String CONNECTION_STRING = "jdbc:postgresql:beers";
    public static String DB_USER = "raservice";
    public static String DB_PASSWORD = "test";

    private Connection dbConnection;

    public RA() { // Should pass DB into constructor
//        this.dbConnection = createDBConnection(); // Uncomment for heroku
        this.dbConnection = createDBConnection(CONNECTION_STRING, DB_USER, DB_PASSWORD);
    }

    public ResultSet evaluateRAQuery(String query) {
        ANTLRInputStream inputStream = new ANTLRInputStream(query);
        RAGrammarLexer lexer = new RAGrammarLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RAGrammarParser parser = new RAGrammarParser(tokenStream);

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

    private Connection createDBConnection() {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL")); // Heroku DB

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            return DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Connection createDBConnection(String connString, String user, String pass) {
        Properties prop = new Properties();
        prop.setProperty("user", user);
        prop.setProperty("password", pass);

        try {
            return DriverManager.getConnection(connString, prop);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // TODO return real error?
    }


    public static void main(String[] args) {
        String query = "\\project_{bar, beer} (\n" +
                "\t(\n" +
                "\t\t\\project_{bar, beer} Serves\n" +
                "\t\t\\diff\n" +
                "\t\t\\project_{bar1, beer1} (\n" +
                "\t\t    \\rename_{bar1, beer1, price1} Serves\n" +
                "\t\t\t\\join_{beer1=beer2 and price1>price2}\n" +
                "\t\t    \\rename_{bar2, beer2, price2} Serves\n" +
                "\t   )\n" +
                "\t)\n" +
                "\t\\join\n" +
                "\t\\select_{drinker='Dan'} Likes\n" +
                ");";
        RA ra = new RA();
        ResultSetUtilities.print(ra.evaluateRAQuery(query));
    }
}
