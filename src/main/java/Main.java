import static spark.Spark.*;

import ra.RA;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import freemarker.template.*;
import util.ResultSetUtilities;

import java.util.*;

/**
 * @author Jennie Ju
 */

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Configuration viewDir = new Configuration(Configuration.VERSION_2_3_22);
        viewDir.setClassForTemplateLoading(Main.class, "/templates/");
//        port(8000);
        port(getHerokuAssignedPort());

        /**
         * Main page endpoint
         */
//        get("/", (req, res) -> {
//            return new ModelAndView(new HashMap<String, Object>(), "frontend.ftl");
//        }, new FreeMarkerEngine(viewDir));

        RA ra = new RA();
        /**
         * RA-query endpoint
         */
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String qString = "";
            if (req.queryParams("ra_query") != null) {
                qString = ResultSetUtilities.asString(
                        ra.evaluateRAQuery(req.queryParams("ra_query"))
                );
            }

            model.put("ra_output", qString);

            return new ModelAndView(model, "form.ftl");
        }, new FreeMarkerEngine(viewDir));
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

