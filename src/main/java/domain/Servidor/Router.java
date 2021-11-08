package domain.Servidor;

import domain.Controllers.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static Router INSTANCE = new Router();

  public void configure(){
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
    Spark.get("/",((request, response) -> HomeController.index(request,response), engineTemplate));

  }

}
