package ra;
import grammar.RAEvalVisitor;
import grammar.gen.RAGrammarLexer;
import grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.sql.*;
import java.util.Properties;


/**
 * Created by jordanly on 10/6/15.
 */
public class RA {
    public static void main(String[] args) {
//        Test DB
//        Connection conn = connectToDB();
//        try {
//            Statement st = conn.createStatement();
//            st.execute("SELECT row_to_json(t) FROM (SELECT * FROM Serves) t;");
//            ResultSet rs = st.getResultSet();
//            while (rs.next()) {
//                System.out.println(rs.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        // Test AST
        ANTLRInputStream inputStream = new ANTLRInputStream("\\project_{bar} (\n" +
                "\t\\select_{drinker = 'Ben'} Frequents\n" +
                ");");
        RAGrammarLexer lexer = new RAGrammarLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RAGrammarParser parser = new RAGrammarParser(tokenStream);

        try {
            ParseTree tree = parser.exp0();
            String query = new RAEvalVisitor().visit(tree);
            System.out.println(query);

            Connection conn = connectToDB();
            Statement st = conn.createStatement();
            st.execute(query);
            ResultSet rs = st.getResultSet();

            printResultSet(rs);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static Connection connectToDB() {
        String connString = "jdbc:postgresql:beers";
        Properties prop = new Properties();
        prop.setProperty("user", "raservice");
        prop.setProperty("password", "test");
        try {
            Connection conn = DriverManager.getConnection(connString, prop);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
}
