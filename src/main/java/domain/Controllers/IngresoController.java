package domain.Controllers;

import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import exception.BusquedaEnBaseDeDatosException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IngresoController {

  public static ModelAndView controlarIngreso(Request request, Response response) {
    return new ModelAndView(null, "yaTeRegistraste.hbs");
  }

  public static ModelAndView ingreseUsuarioYContrasenia(Request request, Response response) {

    return new ModelAndView(null, "ingreseSusDatos.hbs");
  }


  public static ModelAndView preIngreso(Request request, Response response) {

    return new ModelAndView(null, "yaTeRegistraste.hbs");
  }

  public static ModelAndView ingresarUsuarioYContrasenia(Request request, Response response) {

    return new ModelAndView(null, "ingresarParaRegistrarMascota.hbs");
  }

  private static Duenio buscarDuenio(Request request, Response response) {
    String nombreUsuario = request.queryParams("nombreDeUsuario");
    String contrasenia = request.queryParams("contrasenia");
    Duenio duenio = RepositorioUsuarios.instance().buscarDuenio(nombreUsuario, contrasenia);

    response.cookie("nombreDeUsuario", nombreUsuario);
    response.cookie("contrasenia", contrasenia);

    return duenio;
  }

  public static ModelAndView ingresarParaRegistrarMascota(Request request, Response response) {
    try {
      Duenio duenio = IngresoController.buscarDuenio(request, response);
      return new ModelAndView(duenio, "registrarMascota.hbs");
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "usuarioNoEncontradoRegistroMascota.hbs");
    }
  }

  public static ModelAndView ingresarComunmente(Request request, Response response) {
    return new ModelAndView(null, "pantallaDeLogueo.hbs");// formularioDeIngresoComun.hbs
  }

  public static ModelAndView mostrarPerfil(Request request, Response response) {
    try {
      Duenio duenio = IngresoController.buscarDuenio(request, response);
      return new ModelAndView(duenio, "homeLogueado.hbs");
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "logueoComunUsuarioNoEncontrado.hbs");//usuarioNoEncontradoComunmente.hbs"
    }
  }

}
