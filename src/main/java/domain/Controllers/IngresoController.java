package domain.Controllers;

import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import exception.BusquedaEnBaseDeDatosException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.time.LocalDate;

public class IngresoController {


  public ModelAndView controlarIngreso(Request request, Response response) {
    return new ModelAndView(null, "yaTeRegistraste.hbs");
  }

  public ModelAndView ingreseUsuarioYContrasenia(Request request, Response response) {

    return new ModelAndView(null, "ingreseSusDatos.hbs");


  }

  public ModelAndView preIngreso(Request request, Response response) {

    return new ModelAndView(LocalDate.now(), "yaTeRegistraste.hbs");
  }

  public ModelAndView ingresarUsuarioYContrasenia(Request request, Response response) {

    return new ModelAndView(null, "ingresarParaRegistrarMascota.hbs");
  }

  private Duenio buscarDuenio(Request request, Response response) {
    String nombreUsuario = request.cookie("nombreDeUsuario");
    String contrasenia = request.cookie("contrasenia");

    if(nombreUsuario == null) {
      nombreUsuario = request.queryParams("nombreDeUsuario");
      contrasenia = request.queryParams("contrasenia");
    }

    Duenio duenio = RepositorioUsuarios.instance().buscarDuenio(nombreUsuario, contrasenia);

    response.cookie("nombreDeUsuario", nombreUsuario);
    response.cookie("contrasenia", contrasenia);

    return duenio;
  }

  public ModelAndView ingresarParaRegistrarMascota(Request request, Response response) {
    try {
      Duenio duenio = this.buscarDuenio(request, response);
      return new ModelAndView(duenio, "registrarMascota.hbs");
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "usuarioNoEncontradoRegistroMascota.hbs");
    }
  }

  public ModelAndView ingresarComunmente(Request request, Response response) {
    return new ModelAndView(null, "pantallaDeLogueo.hbs");// formularioDeIngresoComun.hbs
  }

  public ModelAndView mostrarPerfil(Request request, Response response) {
    try {
      Duenio duenio = this.buscarDuenio(request, response);
      return new ModelAndView(duenio, "homeLogueado.hbs");
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "logueoComunUsuarioNoEncontrado.hbs");//usuarioNoEncontradoComunmente.hbs"
    }
  }

  public ModelAndView registrarMascota(Request request, Response response){
    return new ModelAndView(null, "registrarMascota");
  }

}
