import ra.Query;
import ra.RA;
import util.TempUtil;

/**
 * Temp class for testing simple RA queries
 */
public class RAQueryTest {
    public static void main(String[] args) {
        String query = ("\\project_{bar, beer} (\n" +
                "\t(\n" +
                "\t\t\\project_{bar, beer} Serves\n" +
                "\t\t\\diff\n" +
                "\\project_{bar1, beer1} (\n" +
                "\\rename_{bar1, beer1, price1} Serves\n" +
                "\\rename_{bar2, beer2, price2} Serves\n" +
                "\t   )\n" +
                "\t)\n" +
                "\t\\join\n" +
                "\t\\select_{drinker='Dan'} Likes\n" +
                ");\n");
        System.out.println(query);
        RA ra = new RA(TempUtil.createLocalDBConnection());
        Query ans = ra.evaluateRAQuery(query);
        System.out.println(ans.toJson().toString(4));
//        System.out.println(ans.getAstTreeJson().toString(4));

//        try {
//            ra.evaluateSQLQuery("SELECT * FROM (  ( SELECT * FROM frequents t51 )  ) t80 WHERE drinker = 'Ben' ");
//            System.out.println("Success");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
