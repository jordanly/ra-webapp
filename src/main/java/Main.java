import static spark.Spark.*;

import org.json.JSONArray;
import ra.RA;
import ra.SchemaRequest;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import freemarker.template.*;
import util.ResultSetUtilities;
import util.TempUtil;

import java.sql.Connection;
import java.util.*;

/**
 * @author Jennie Ju
 */

public class Main {


    public static void main(String[] args) {
        staticFileLocation("/public");
        Configuration viewDir = new Configuration(Configuration.VERSION_2_3_22);
        viewDir.setClassForTemplateLoading(Main.class, "/templates/");

        Connection conn;
        if (System.getenv("DATABASE_URL") != null) {
            conn = TempUtil.createHerokueDBConnection(); // Heroku DB
            port(getHerokuAssignedPort());
        } else {
            conn = TempUtil.createLocalDBConnection();
            port(8000);
        }
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
            res.header("Access-Control-Allow-Origin", "*");
            JSONArray results = new JSONArray();
            String queryString = req.splat()[0];
            if (queryString != null) {
                // Divide up the multiple queries
                String[] queries = queryString.split(";");
                for (String query : queries)
                {
                    results.put(ra.evaluateRAQuery(query + ";").toJson());
                }
            }
            return results.toString(4);
        });

        /**
         * Schema-request endpoint
         */
        get("/schema/*", (req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            JSONArray results = new JSONArray();
            String requestString = req.splat()[0];
            if (requestString != null) {
                // Divide up the multiple requests
                String[] schemaReqs = requestString.split(";");
                for (String schemaReq : schemaReqs)
                {
                    results.put(new SchemaRequest(ra, schemaReq).toJson());
                }
            }
            return results.toString(4);
        });
    }

    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

