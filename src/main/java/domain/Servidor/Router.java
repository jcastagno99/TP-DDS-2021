package domain.Servidor;

import domain.Controllers.HomeController;
import domain.Controllers.IngresoController;
import domain.Controllers.RegistroController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static Router INSTANCE = new Router();

  public void configure(){
    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
    Spark.staticFileLocation("public");

    Spark.get("/", HomeController::index,engineTemplate);

    Spark.get("/registrarme", RegistroController::registrarUsuario,engineTemplate);
    Spark.post("/registrarme",RegistroController::crearUsuario,engineTemplate);

    Spark.get("/ingreso",IngresoController::controlarIngreso, engineTemplate);

    Spark.get("/yaTengoUnUsuario", IngresoController::ingreseUsuarioYContrasenia, engineTemplate);

    Spark.get("/buscarUsuario", IngresoController::buscarUsuarioYContrasenia, engineTemplate);

  }

}
