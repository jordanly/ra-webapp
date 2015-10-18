package ra;
import grammar.RAEvalVisitor;
import grammar.gen.RAGrammarLexer;
import grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.net.URI;
import java.sql.*;
import java.util.Properties;


/**
 * Created by jordanly on 10/6/15.
 */
public class RA {
    private Connection dbConnection;

    public RA() {
        this.dbConnection = createDBConnection();
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

    public String resultSetToString(ResultSet rs) {
        StringBuilder sb = new StringBuilder();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int numCols = md.getColumnCount();
            for (int i = 1; i <= numCols; i++) {
                sb.append(md.getColumnName(i) + " | ");
            }
            sb.append("<br />");

            while (rs.next()) {
                for (int i = 1; i <= numCols; i++) {
                    sb.append(rs.getString(i) + " | ");
                }
                sb.append("<br />");
            }
            return sb.toString();
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public static void printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData md = rs.getMetaData();
            int numCols = md.getColumnCount();
            for (int i = 1; i <= numCols; i++) {
                System.out.print(md.getColumnName(i) + " ");
            }
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= numCols; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        printResultSet(ra.evaluateRAQuery(query));
    }
}
