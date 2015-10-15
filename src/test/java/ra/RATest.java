package ra;

import grammar.RAEvalVisitor;
import grammar.gen.RAGrammarLexer;
import grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jordanly on 10/15/15.
 */
public class RATest {
    Connection db;

    @org.junit.Before
    public void setUp() throws Exception {
        db = RA.connectToDB();
    }

    @org.junit.Test
    public void testQueryA() throws Exception {
        ANTLRInputStream inputStream = new ANTLRInputStream("\\project_{bar} (\n" +
                "\t\\select_{drinker = 'Ben'} Frequents\n" +
                ");");
        RAGrammarLexer lexer = new RAGrammarLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RAGrammarParser parser = new RAGrammarParser(tokenStream);

        ParseTree tree = parser.exp0();
        String query = new RAEvalVisitor().visit(tree);

        Statement st = db.createStatement();
        st.execute(query);
        ResultSet rs = st.getResultSet();

        String[][] ans = new String[][]{{"Satisfaction"}, {"Talk of the Town"}, {"James Joyce Pub"}};

        assertTrue(validateResultSet(rs, ans));

    }

    private boolean validateResultSet(ResultSet rs, String[][] ans) throws Exception {
        ResultSetMetaData md = rs.getMetaData();
        int index = 0;
        int numCols = md.getColumnCount();
        while (rs.next()) {
            List<String> line = new ArrayList<>();
            for (int i = 1; i <= numCols; i++) {
                line.add(rs.getString(i));
            }
            String[] out = line.toArray(new String[line.size()]);

            if (!Arrays.equals(ans[index++], out)) {
                return false;
            }
        }

        return true;
    }
}