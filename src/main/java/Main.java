import static spark.Spark.*;

import freemarker.template.Configuration;
import org.json.JSONArray;
import ra.RA;
import spark.Filter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

import ra.util.ResultSetUtilities;
import ra.util.TempUtil;
import constants.SQLQueryConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * @author Jennie Ju
 */

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Configuration viewDir = new Configuration(Configuration.VERSION_2_3_22);
        viewDir.setClassForTemplateLoading(Main.class, "/templates/");

        // Create DB connection
        // TODO try/catch?
        Connection conn = TempUtil.createLocalDBConnection();
        port(8000);

        /**
         * Add CORS -- TODO remove in production
         */
        enableCORS("*", "*", "*");

        RA ra = new RA(conn);

        /**
         * Main page endpoint
         */
        get("/", (req, res) -> {
            return new ModelAndView(new HashMap<String, Object>(), "frontend.ftl");
        }, new FreeMarkerEngine(viewDir));

        /**
         * RA-query endpoint
         */
        get("/query/*", (req, res) -> {
            JSONArray results = new JSONArray();
            String queryString = req.splat()[0];
            System.out.println(queryString);
            if (queryString != null) {
                results.put(ra.evaluateRAQuery(queryString).toJson());
            }
            return results.toString(4);
        });

        /**
         * Schema-request endpoint
         */
        get("/schema/*", (req, res) -> {
            JSONArray results = new JSONArray();
            String requestString = req.splat()[0];
            if (requestString != null) {
                // Divide up the multiple requests
                String[] schemaReqs = requestString.replace("\n", "").split(";");
                for (String schemaReq : schemaReqs)
                {
                    results.put(new SchemaRequest(ra, schemaReq).toJson());
                }
            }
            return results.toString(4);
        });

        /**
         * Lookahead strings list endpoint
         */
        get("/lookahead/", (req, res) -> {
            JSONArray results = null;
            try {
                ResultSet rs = ra.evaluateSQLQuery(SQLQueryConstants.LOOKAHEAD_QUERY);
                results = ResultSetUtilities.columnValuesAsJSONArray(rs, SQLQueryConstants.LOOKAHEAD_COLUMN);
            } catch (SQLException e) {
                // return empty object
                results = new JSONArray(); // TODO Do something better?
            }
            return results.toString(4);
        });

        /**
         * AST-query endpoint
         */
        get("/ast/*", (req, res) -> {
            JSONArray results = new JSONArray();
            String queryString = req.splat()[0];
            if (queryString != null) {
                // Divide up the multiple queries
                // Should only use the last query for AST tree generation
                String[] queries = queryString.split(";");
                results.put(ra.evaluateRAQuery(queries[queries.length - 1] + ";").getAstTreeJson());
            }
            return results.toString(4);
        });
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {
        before(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                response.header("Access-Control-Allow-Origin", origin);
                response.header("Access-Control-Request-Method", methods);
                response.header("Access-Control-Allow-Headers", headers);
            }
        });
    }
}

