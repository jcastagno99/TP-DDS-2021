package domain.Servidor;

import domain.Controllers.IngresoController;
import domain.Controllers.PerfilController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static Router INSTANCE = new Router();

  public void configure(){

    HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();
    IngresoController ingresoController = new IngresoController();
    PerfilController perfilController = new PerfilController();

    // TODO
    /*Spark.get("/",((request, response) -> HomeController.index(request,response), templateEngine));*/

    //Spark.get("/rutaDePrueba", (request, response) -> "Hola mundo");

    Spark.get("/ingreso", ingresoController::preIngreso, templateEngine);

    Spark.get("/usuarioYaExistente", ingresoController::ingresarUsuarioYContrasenia, templateEngine);

    Spark.get("/ingresarParaRegistrarMascota", ingresoController::ingresarParaRegistrarMascota, templateEngine); // No usamos post porque tira 404

    Spark.get("/ingresar", ingresoController::ingresarComunmente, templateEngine);

    Spark.get("/miPerfil", ingresoController::mostrarPerfil, templateEngine);
  }

}
