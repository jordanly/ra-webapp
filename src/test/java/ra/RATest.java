package ra;

import grammar.RAEvalVisitor;
import grammar.gen.RAGrammarLexer;
import grammar.gen.RAGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        ResultSet rs = evaluateQuery("\\project_{bar} (\n" +
                "\t\\select_{drinker = 'Ben'} Frequents\n" +
                ");");
        String[][] ans = new String[][]{{"Satisfaction"}, {"Talk of the Town"}, {"James Joyce Pub"}};

        assertTrue(validateResultSet(rs, ans));
    }

    @org.junit.Test
    public void testQueryB() throws Exception {
        ResultSet rs = evaluateQuery("\\project_{name, address} (\n" +
                "\t(\\select_{bar='James Joyce Pub' and times_a_week > 1} Frequents)\n" +
                "\t\\join_{drinker=name} \n" +
                "\tDrinker\n" +
                ");");
        String[][] ans = new String[][]{{"Amy", "100 W. Main Street"}, {"Eve", "100 W. Main Street"}};
        // TODO still need to implement join on

        assertTrue(validateResultSet(rs, ans));
    }

    @org.junit.Test
    public void testQueryC() throws Exception {
        ResultSet rs = evaluateQuery("\\project_{bar} (\n" +
                "\t\\select_{drinker='Eve'} Likes\n" +
                "\t\\join\n" +
                "\t\\select_{price<=2.75} Serves\n" +
                ");");
        String[][] ans = new String[][]{{"Down Under Pub"}, {"Satisfaction"},
                {"Talk of the Town"}, {"The Edge"}};

        assertTrue(validateResultSet(rs, ans));
    }

    @org.junit.Test
    public void testQueryD() throws Exception {
        ResultSet rs = evaluateQuery("\\project_{drinker} (\n" +
                "\t\\select_{beer='Amstel'} Likes\t\n" +
                "\t)\n" +
                "\\diff\n" +
                "\\project_{drinker} (\n" +
                "\t\\select_{beer='Corona'} Likes\n" +
                "\t)\n" +
                ";");

        String[][] ans = new String[][]{{"Ben"}};

        assertTrue(validateResultSet(rs, ans));
    }

    @org.junit.Test
    public void testQueryE() throws Exception {
        ResultSet rs = evaluateQuery("\\project_{bar, beer} (\n" +
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
                ");\n");
        String[][] ans = new String[][]{{"James Joyce Pub", "Erdinger"},
                {"Satisfaction", "Corona"},
                {"Satisfaction", "Dixie"},
                {"Talk of the Town", "Amstel"},
                {"Talk of the Town", "Budweiser"}};

        assertTrue(validateResultSet(rs, ans));
    }

    @org.junit.Test
    public void testQueryF() throws Exception {
        ResultSet rs = evaluateQuery("Serves\n" +
                "\\diff\n" +
                "\\project_{bar3,beer3,price3}\n" +
                "  \\select_{price1 < price2 and price2 < price3}\n" +
                "    (\\rename_{bar1, beer1, price1} Serves \\cross\n" +
                "     \\rename_{bar2, beer2, price2} Serves \\cross\n" +
                "     \\rename_{bar3, beer3, price3} Serves);");
        String[][] ans = new String[][]{{"Satisfaction", "Budweiser", "2.25"},
                {"Down Under Pub", "Budweiser", "2.25"},
                {"Talk of the Town", "Budweiser", "2.20"}};

        assertTrue(validateResultSet(rs, ans));
    }

    @org.junit.Test
    public void testQueryG() throws Exception {
        ResultSet rs = evaluateQuery("\\project_{drinker,bar} Frequents\n" +
                "\\diff\n" +
                "\\project_{drinker, bar}(\n" +
                "\tLikes\n" +
                "\t\\join\n" +
                "\tServes\n" +
                ")\n" +
                ";");
        String[][] ans = new String[][]{{"Coy", "The Edge"}, {"Coy", "Down Under Pub"}};

        assertTrue(validateResultSet(rs, ans));
    }

    @org.junit.Test
    public void testQueryH() throws Exception {
        ResultSet rs = evaluateQuery("\\project_{name} Drinker\n" +
                "\\diff\n" +
                "\\project_{drinker}\n" +
                "(\n" +
                "\t\\project_{drinker,bar} Frequents\n" +
                "\t\\diff\n" +
                "\t\\project_{drinker, bar}(\n" +
                "\t\tLikes\n" +
                "\t\t\\join\n" +
                "\t\tServes\n" +
                "\t)\n" +
                ");");
        String[][] ans = new String[][]{{"Ben"}, {"Amy"}, {"Eve"}, {"Dan"}};

        assertTrue(validateResultSet(rs, ans));
    }

    @org.junit.Test
    public void testQueryI() throws Exception {
        ResultSet rs = evaluateQuery("\\project_{name} Drinker\n" +
                "\\diff\n" +
                "\\project_{drinker} (\n" +
                "\t\\project_{drinker,bar} (Likes \\join Serves)\n" +
                "\t\\diff\n" +
                "\t\\project_{drinker,bar} Frequents\n" +
                ");");
        String[][] ans = new String[][]{{"Dan"}};

        assertTrue(validateResultSet(rs, ans));
    }

    public ResultSet evaluateQuery(String raQuery) throws Exception {
        ANTLRInputStream inputStream = new ANTLRInputStream(raQuery);
        RAGrammarLexer lexer = new RAGrammarLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        RAGrammarParser parser = new RAGrammarParser(tokenStream);

        ParseTree tree = parser.exp0();
        String sqlQuery = new RAEvalVisitor().visit(tree);

        Statement st = db.createStatement();
        st.execute(sqlQuery);
        return st.getResultSet();
    }

    private boolean validateResultSet(ResultSet rs, String[][] ans) throws Exception {
        ResultSetMetaData md = rs.getMetaData();
        int numCols = md.getColumnCount();
        List<List<String>> rows = new ArrayList<>();
        while (rs.next()) {
            List<String> line = new ArrayList<>();
            for (int i = 1; i <= numCols; i++) {
                line.add(rs.getString(i));
            }
            rows.add(line);
        }

        String[][] out = new String[rows.size()][rows.get(0).size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = rows.get(i).toArray(new String[out[0].length]);
        }

        Arrays.sort(ans, new StringMatrixComparator());
        Arrays.sort(out, new StringMatrixComparator());

        for (int i = 0; i < ans.length; i++) {
            if (!Arrays.equals(ans[i], out[i])) {
                return false;
            }
        }

        return true;
    }

    private class StringMatrixComparator implements Comparator<String[]> {

        @Override
        public int compare(String[] o1, String[] o2) {
            for (int i = 0; i < Math.min(o1.length, o2.length); i++) {
                int val = o1[i].compareTo(o2[i]);
                if (val != 0) return val;
            }

            if (o1.length != o2.length) return o1.length - o2.length;
            return 0;
        }
    }
}