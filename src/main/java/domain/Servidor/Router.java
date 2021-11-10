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

    Spark.get("/", HomeController::index, engineTemplate);

    Spark.get("/registrarme", RegistroController::registrarUsuario, engineTemplate);
    Spark.post("/registrarme", RegistroController::crearUsuario, engineTemplate);


    // TODO
    /*Spark.get("/",((request, response) -> HomeController.index(request,response), templateEngine));*/

    //Spark.get("/rutaDePrueba", (request, response) -> "Hola mundo");

    Spark.get("/ingreso", IngresoController::preIngreso, engineTemplate);

    Spark.get("/usuarioYaExistente", IngresoController::ingresarUsuarioYContrasenia, engineTemplate);

    Spark.post("/ingresarParaRegistrarMascota", IngresoController::ingresarParaRegistrarMascota, engineTemplate); // No usamos post porque tira 404

    Spark.get("/ingresar", IngresoController::ingresarComunmente, engineTemplate);

    Spark.post("/ingresar", IngresoController::mostrarPerfil, engineTemplate);

    //Spark.get("/miPerfil", ingresoController::mostrarPerfil, templateEngine);

  }

}
