import ra.Query;
import ra.RA;
import util.TempUtil;

/**
 * Temp class for testing simple RA queries
 */
public class RAQueryTest {
    public static void main(String[] args) {
        String query = ("\n" +
                "\\project_{beer, bar}\n" +
                "(\n" +
                "\t\\project_{beer}\n" +
                "\t\t(\n" +
                "\t\t\t\\select_{drinker = 'Dan'} Likes\n" +
                "\t\t)\n" +
                "\t\\join Serves\n" +
                ")\n" +
                "\\diff\n" +
                "\\project_{beer, bar}\n" +
                "(\n" +
                "\t\\project_{beer}\n" +
                "\t(\n" +
                "\t\t\\select_{drinker = 'Dan'} Likes\n" +
                "\t)\n" +
                "\t\\join Serves\n" +
                "\n" +
                "\t\\join_{beer = beer2 and price > price2}\n" +
                "\t\\rename_{beer2, bar2, price2}\n" +
                "\t(\n" +
                "\t\t\\project_{beer}\n" +
                "\t\t(\n" +
                "\t\t\t\\select_{drinker = 'Dan'} Likes\n" +
                "\t\t)\n" +
                "\t\t\\join Serves\n" +
                "\t)\n" +
                ");");
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
