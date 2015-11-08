import ra.Query;
import ra.RA;
import util.TempUtil;

/**
 * Temp class for testing simple RA queries
 */
public class RAQueryTest {
    public static void main(String[] args) {
        String query = ("\\project_{name,address} (\n" +
                "  (\n" +
                "    \\select_{bar = 'James Joyce Pub' and times_a_week > 1} frequents\n" +
                "  ) \\join_{name=drinker} drinker\n" +
                ");\n");
        System.out.println(query);
        RA ra = new RA(TempUtil.createLocalDBConnection());
        Query ans = ra.evaluateRAQuery(query);
        System.out.println(ans.toJson().toString(4));

//        try {
//            ra.evaluateSQLQuery("SELECT * FROM (  ( SELECT * FROM frequents t51 )  ) t80 WHERE drinker = 'Ben' ");
//            System.out.println("Success");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
