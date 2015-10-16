import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import freemarker.template.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Configuration viewDir = new Configuration();
        viewDir.setClassForTemplateLoading(Main.class, "/templates");

        get("/hello", (req, res) -> {
            return new ModelAndView(new HashMap<String, Object>(), "frontend.ftl");
        }, new FreeMarkerEngine(viewDir));
    }
}

