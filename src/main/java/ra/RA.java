package ra;
import org.json.JSONArray;
import util.ResultSetUtilities;
import util.TempUtil;

import java.sql.*;

/**
 * Created by jordanly on 10/6/15.
 */
public class RA {
    private Connection dbConnection;

    public RA(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Query evaluateRAQuery(String raQuery) {
        return new Query(this, raQuery);
    }

    public ResultSet evaluateSQLQuery(String sqlQuery) {
        try {
            Statement st = dbConnection.createStatement();
            st.execute(sqlQuery);

            return st.getResultSet();
        } catch (Exception e) {
            System.out.println(e.toString()); // TODO could not query db
        }

        return null;
    }

    public static void main(String[] args) {
        String query = ("\\project_{bar} (\n" +
                "\t\\select_{drinker = 'Ben'} Frequents\n" +
                ");");
        RA ra = new RA(TempUtil.createLocalDBConnection());
        Query ans = ra.evaluateRAQuery(query);
        System.out.println(ans.toJson());
    }
}
