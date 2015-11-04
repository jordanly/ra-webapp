package util;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by jordanly on 10/19/15.
 */
public class TempUtil {
    public static String CONNECTION_STRING = "jdbc:postgresql:beers";
    public static String DB_USER = "raservice";
    public static String DB_PASSWORD = "test";

    private TempUtil() { }

    public static Connection createHerokueDBConnection() {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL")); // Heroku DB

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            return DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Connection createLocalDBConnection() {
        return createLocalDBConnection(CONNECTION_STRING, DB_USER, DB_PASSWORD);
    }

    public static Connection createLocalDBConnection(String connString, String user, String pass) {
        Properties prop = new Properties();
        prop.setProperty("user", user);
        prop.setProperty("password", pass);

        try {
            return DriverManager.getConnection(connString, prop);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // TODO return real error?
    }
}
