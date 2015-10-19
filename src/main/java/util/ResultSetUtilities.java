package util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
