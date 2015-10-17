package ra;

import grammar.RAEvalVisitor;
import grammar.gen.RAGrammarLexer;
import grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Jennie on 10/16/15.
 */
public class DummyQueryProcessor {

    public String processQuery(String raQuery) {
        ANTLRInputStream inputStream = new ANTLRInputStream(raQuery);
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

            return resultSetToString(rs);
        } catch (Exception e) {
            return e.toString();
        }
    }

    public Connection connectToDB() {
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

    private String resultSetToString(ResultSet rs) {
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
}
