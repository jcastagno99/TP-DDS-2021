package domain.Servidor;


import domain.Controllers.HomeController;
import domain.Controllers.IngresoController;
import domain.Controllers.MascotasController;
import domain.Controllers.RegistroController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static Router INSTANCE = new Router();

  public static Router getInstance() {
    return INSTANCE;
  }

  public void configure(){

    HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
    Spark.staticFileLocation("public");

    HomeController homeController = new HomeController();
    RegistroController registroController = new RegistroController();
    IngresoController ingresoController = new IngresoController();
    MascotasController mascotasController = new MascotasController();

    Spark.get("/",homeController::index, engineTemplate);

    Spark.get("/ingresar", ingresoController::ingresarComunmente, engineTemplate);

    Spark.post("/ingresar", ingresoController::mostrarPerfil, engineTemplate);

    Spark.get("/registrarme", registroController::registrarUsuario, engineTemplate);

    Spark.post("/registrarme", registroController::crearUsuario, engineTemplate);

    Spark.get("/ingresarParaRegistrarMascota", ingresoController::preIngreso, engineTemplate);

    Spark.post("/ingresarParaRegistrarMascota", ingresoController::ingresarParaRegistrarMascota, engineTemplate); // No usamos post porque tira

    Spark.get("/registrarMascota", mascotasController::mostrarFormDeRegistro, engineTemplate);

    Spark.post("/registrarMascota", mascotasController::registrarMascota, engineTemplate);

    Spark.get("/cerrarSesion", ingresoController::cerrarSesion, engineTemplate);

    Spark.post("/miPerfil", ingresoController::mostrarPerfil, engineTemplate);

  }

}
