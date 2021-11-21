package domain.Servidor;


import domain.Controllers.*;
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
    EncontreMascotaController encontreMascotaController = new EncontreMascotaController();

    Spark.get("/",homeController::index, engineTemplate); // listo

    Spark.get("/ingresar", ingresoController::ingresarComunmente, engineTemplate); // listo

    Spark.post("/ingresar", ingresoController::mostrarPerfil, engineTemplate); // Listo

    Spark.get("/registrarme", registroController::registrarUsuario, engineTemplate);

    Spark.post("/registrarme", registroController::crearUsuario, engineTemplate);

    Spark.get("/ingresarParaRegistrarMascota", ingresoController::preIngreso, engineTemplate);

    Spark.post("/ingresarParaRegistrarMascota", ingresoController::ingresarParaRegistrarMascota, engineTemplate);

    Spark.get("/registrarMascota", mascotasController::mostrarFormDeRegistro, engineTemplate);

    Spark.post("/registrarMascota", mascotasController::registrarMascota, engineTemplate);

    Spark.get("/cerrarSesion", ingresoController::cerrarSesion, engineTemplate);

    Spark.post("/miPerfil", ingresoController::mostrarPerfil, engineTemplate);

    Spark.get("/encontreUnaMascota",encontreMascotaController::encontreUnaMascota,engineTemplate);

    Spark.get("/formularioSinChapita",encontreMascotaController::registrarMascotaSinChapita, engineTemplate);

    Spark.post("/formularioSinChapita",encontreMascotaController::crearPublicacionMascotaPerdida, engineTemplate);

    Spark.post("/formularioSinChapitaLogueado",encontreMascotaController::crearPublicacionMascotaPerdidaLogueado, engineTemplate);

  }

}
