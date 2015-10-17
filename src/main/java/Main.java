import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import freemarker.template.*;

import java.util.*;

import ra.DummyQueryProcessor;

/**
 * @author Jennie Ju
 */

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Configuration viewDir = new Configuration(Configuration.VERSION_2_3_22);
        viewDir.setClassForTemplateLoading(Main.class, "/templates/");

//        port(8000);

        /**
         * Main page endpoint
         */
//        get("/", (req, res) -> {
//            return new ModelAndView(new HashMap<String, Object>(), "frontend.ftl");
//        }, new FreeMarkerEngine(viewDir));

        DummyQueryProcessor dqp = new DummyQueryProcessor();
        /**
         * RA-query endpoint
         */
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String qString = "";
            if (req.queryParams("ra_query") != null) {
                qString = dqp.processQuery(req.queryParams("ra_query"));
            }

            model.put("ra_output", qString);

            return new ModelAndView(model, "form.ftl");
        }, new FreeMarkerEngine(viewDir));
    }


}

