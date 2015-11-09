package constants;

/**
 * Created by Jennie on 11/8/15.
 */
public final class SQLQueryConstants {
    public static final String RELATIONS_QUERY = "SELECT n.nspname as \"Schema\",\n" +
            "  c.relname as \"Name\",\n" +
            "  CASE c.relkind WHEN 'r' THEN 'table' " +
            "WHEN 'v' THEN 'view' WHEN 'm' THEN 'materialized view' " +
            "WHEN 'i' THEN 'index' WHEN 'S' THEN 'sequence' WHEN 's' " +
            "THEN 'special' WHEN 'f' THEN 'foreign table' END as \"Type\",\n" +
            "  pg_catalog.pg_get_userbyid(c.relowner) as \"Owner\"\n" +
            "FROM pg_catalog.pg_class c\n" +
            "     LEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace\n" +
            "WHERE c.relkind IN ('r','v','m','S','f','')\n" +
            "      AND n.nspname <> 'pg_catalog'\n" +
            "      AND n.nspname <> 'information_schema'\n" +
            "      AND n.nspname !~ '^pg_toast'\n" +
            "  AND pg_catalog.pg_table_is_visible(c.oid)\n" +
            "ORDER BY 1,2;";

    public static final String TABLE_QUERY = "SELECT a.attname AS \"Column\",\n" +
            "  pg_catalog.format_type(a.atttypid, a.atttypmod) AS \"Type\"\n" +
            "FROM pg_catalog.pg_attribute a, pg_catalog.pg_class c\n" +
            "WHERE c.relname ~ '^(%s)$'\n" +
            "  AND a.attrelid = c.oid\n" +
            "  AND a.attnum > 0 \n" +
            "  AND NOT a.attisdropped\n" +
            "  ORDER BY a.attnum;";

    public static final String LOOKAHEAD_QUERY = "SELECT a.attname AS lookahead\n" +
            "FROM pg_catalog.pg_attribute a, pg_catalog.pg_class c,\n" +
            "(SELECT c.relname\n" +
            "FROM pg_catalog.pg_class c\n" +
            "LEFT JOIN pg_catalog.pg_namespace n \n" +
            "ON n.oid = c.relnamespace\n" +
            "WHERE c.relkind IN ('r','v','m','S','f','')\n" +
            "AND n.nspname <> 'pg_catalog'\n" +
            "AND n.nspname <> 'information_schema'\n" +
            "AND n.nspname !~ '^pg_toast'\n" +
            "AND pg_catalog.pg_table_is_visible(c.oid)) AS t\n" +
            "WHERE c.relname = t.relname\n" +
            "AND a.attrelid = c.oid\n" +
            "AND a.attnum > 0 \n" +
            "AND NOT a.attisdropped\n" +
            "UNION\n" +
            "SELECT c.relname AS lookahead\n" +
            "FROM pg_catalog.pg_class c\n" +
            "LEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace\n" +
            "WHERE c.relkind IN ('r','v','m','S','f','')\n" +
            "AND n.nspname <> 'pg_catalog'\n" +
            "AND n.nspname <> 'information_schema'\n" +
            "AND n.nspname !~ '^pg_toast'\n" +
            "AND pg_catalog.pg_table_is_visible(c.oid)\n" +
            "ORDER BY lookahead;\n";

    private SQLQueryConstants() {
        throw new AssertionError();
    }
}
