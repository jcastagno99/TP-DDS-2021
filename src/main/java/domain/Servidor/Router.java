package domain.Servidor;

import domain.Controllers.HomeController;
import domain.Controllers.IngresoController;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static Router INSTANCE = new Router();

  public void configure(){

    HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();
    IngresoController ingresoController = new IngresoController();

    // TODO
    /*Spark.get("/",((request, response) -> HomeController.index(request,response), templateEngine));*/

    Spark.get("/rutaDePrueba", (request, response) -> "Hola mundo");

    Spark.get("/ingreso", ingresoController::controlarIngreso, templateEngine);

  }

}
