import ra.Query;
import ra.RA;
import util.TempUtil;

import java.io.*;

/**
 * Temp class for testing simple RA queries
 */
public class RAFileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/jordanly/Documents/Duke/cs316/test-queries/jju-queries.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        String[] commands = sb.toString().split(";");
        RA ra = new RA(TempUtil.createLocalDBConnection());

        for (String c : commands) {
            String query = c + ";";
            Query ans = ra.evaluateRAQuery(query);
            System.out.println(ans.toJson().toString(4));
        }

//        try {
//            ra.evaluateSQLQuery("SELECT * FROM (  ( SELECT * FROM frequents t51 )  ) t80 WHERE drinker = 'Ben' ");
//            System.out.println("Success");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
