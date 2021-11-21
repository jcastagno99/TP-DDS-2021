package domain.Controllers;

import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import exception.BusquedaEnBaseDeDatosException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.time.LocalDate;

public class IngresoController extends Controller{


  public ModelAndView preIngreso(Request request, Response response) {

    return new ModelAndView(LocalDate.now(), "yaTeRegistraste2.hbs");
  }


  private Duenio buscarDuenio(Request request, Response response) {
    String nombreUsuario = request.cookie("nombreDeUsuario");
    //String contrasenia = request.cookie("contrasenia");
    String contrasenia = request.queryParams("contrasenia");

    if(nombreUsuario == null) {
      nombreUsuario = request.queryParams("nombreDeUsuario");
    }

    Duenio duenio = RepositorioUsuarios.instance().buscarDuenio(nombreUsuario, contrasenia);

    response.cookie("nombreDeUsuario", nombreUsuario);
    //response.cookie("contrasenia", contrasenia);

    return duenio;
  }

  public ModelAndView ingresarParaRegistrarMascota(Request request, Response response) {
    try {
      Duenio duenio = this.buscarDuenio(request, response);
      response.redirect("/registrarMascota");
      return null;
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "usuarioNoEncontradoRegistroMascota.hbs");
    }
  }

  // Listo
  public ModelAndView ingresarComunmente(Request request, Response response) {
    return new ModelAndView(null, "pantallaDeLogueo.hbs");
  }

  public ModelAndView mostrarPerfil(Request request, Response response) {
    try {
      Duenio duenio = this.buscarDuenio(request, response);
      return new ModelAndView(duenio, "homeLogueado.hbs");
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "pantallaDeLogueo.hbs");
    }
  }

  public ModelAndView registrarMascota(Request request, Response response){
    return new ModelAndView(null, "registrarMascota.hbs");
  }

  public ModelAndView cerrarSesion(Request request, Response response) {
    response.removeCookie("nombreDeUsuario");
    response.removeCookie("contrasenia");
    response.redirect("/");
    return null;
  }
}
