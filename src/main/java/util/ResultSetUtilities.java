package util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordanly on 10/18/15.
 */
public final class ResultSetUtilities {
    private ResultSetUtilities() { }

    public static String asString(ResultSet rs) {
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

    public static JSONArray toJSONArray(ResultSet rs) {
        JSONArray rows = new JSONArray();

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] columns = new String[rsmd.getColumnCount()];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = rsmd.getColumnName(i + 1);
            }

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                for (int i = 0; i < columns.length; i++) {
                    obj.put(columns[i], rs.getString(i + 1));
                }
                rows.put(obj);
            }
        } catch (Exception e) {
            e.printStackTrace(); // TODO throw real error
        }

        return rows;
    }


    public static void print(ResultSet rs) {
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
