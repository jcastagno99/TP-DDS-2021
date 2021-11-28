package domain.Servidor;


import domain.Controllers.*;
import org.dom4j.rule.Mode;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
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
    AutenticadorController autenticadorController = new AutenticadorController();

    Spark.get("/", homeController::index, engineTemplate); // LISTO

    Spark.get("/ingreso", ingresoController::mostrarFormDeIngreso, engineTemplate); // LISTO

    Spark.post("/ingreso", ingresoController::ingresar, engineTemplate); // LISTO

    // Solamente se usa para cuando el usuario está logueado, muestra el home logueado
    Spark.get("/miPerfil", ingresoController::buscarUsuarioPorSessionYMostrarVista, engineTemplate); // LISTO

    //Spark.post("/miPerfil", ingresoController::ingresar, engineTemplate); // Listo

    Spark.get("/registroDeUsuario", registroController::mostrarFormDeRegistroDeUsuario, engineTemplate); // LISTO

    Spark.post("/registroDeUsuario", registroController::registrarUsuario, engineTemplate); // LISTO

    Spark.get("/ingreso/registroDeMascota", ingresoController::preIngreso, engineTemplate); // LISTO

    Spark.post("/ingreso/registroDeMascota", ingresoController::ingresarParaRegistrarMascota, engineTemplate);

    Spark.get("/miPerfil/registroDeMascota", mascotasController::mostrarFormDeRegistro, engineTemplate); // LISTO

    Spark.post("/miPerfil/registroDeMascota", mascotasController::registrarMascota, engineTemplate); // LISTO

    Spark.get("/miPerfil/sesion/cerrar", ingresoController::cerrarSesion, engineTemplate); // LISTO

    //Spark.post("/miPerfil", ingresoController::buscarUsuario, engineTemplate);

    Spark.get("/miPerfil/encuentroDeMascota", encontreMascotaController::mostrarFormDeEncuentroDeMascotaSinChapitaParaDuenio,engineTemplate); // LISTO

    Spark.post("/miPerfil/encuentroDeMascota", encontreMascotaController::crearPublicacionMascotaPerdida, engineTemplate); // LISTO

    Spark.get("/encuentroDeMascota", encontreMascotaController::mostrarFormDeEncuentroDeMascotaSinChapita,engineTemplate);

    Spark.post("/encuentroDeMascota", encontreMascotaController::crearPublicacionMascotaPerdida,engineTemplate);

    Spark.get("/qr", mascotasController::crearQRParaMascota, engineTemplate);


    /*Spark.before("/miPerfil/*", (request, response) -> {
      if (request.session().attribute("idUsuario") == null) {
        //response.status(404);
      }
    });
    Spark.before("/miPerfil", (request, response) -> {
      if (request.session().attribute("idUsuario") == null) {
        response.status(404);
      }
    });*/


    /*-----------------------------------------DELEGAR-----------------------------------------*/

    Spark.before("/", (request, response) -> {
      if (autenticadorController.usuarioAutenticado(request)) {
        response.redirect("/miPerfil");
        // Podría redirigirse a miPerfil, y nunca cerraría sesión
        // response.redirect("/miPerfil/sesion/cerrar");
      }
    });

    /*Spark.before("/ingreso", (request, response) -> {
      if(AutenticadorController.usuarioAutenticado(request)) {
        response.redirect("/miPerfil");
      }
    });*/

    Spark.before("/miPerfil", (request, response) -> {
      if(!autenticadorController.usuarioAutenticado(request)) {
        response.redirect("/");
      }});

    Spark.before("/registroDeUsuario", (request, response) -> {
      if(autenticadorController.usuarioAutenticado(request)) {
        response.redirect("/miPerfil");
      }
    });

    Spark.before("/ingreso/registroDeMascota", (request, response) -> {
      if(autenticadorController.usuarioAutenticado(request)) {
        response.redirect("/miPerfil/registroDeMascota");
      }
    });

    Spark.before("/miPerfil/*", (request, response) -> {
      if (!autenticadorController.usuarioAutenticado(request)) {
        response.redirect("/");
      }
    });

    Spark.after("/*", (request, response) -> {
      response.header("Cache-Control", "no-store, must-revalidate");
    });

  }
}


