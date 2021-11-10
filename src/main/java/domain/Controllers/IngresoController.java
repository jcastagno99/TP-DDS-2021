package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import domain.Roles.Usuario;
import exception.BusquedaEnBaseDeDatosException;
import exception.UsuarioYaRegistradoException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IngresoController{

  public ModelAndView preIngreso(Request request, Response response) {

    return new ModelAndView(null, "yaTeRegistraste.hbs");
  }

  public ModelAndView ingresarUsuarioYContrasenia(Request request, Response response) {

    return new ModelAndView(null, "ingresarParaRegistrarMascota.hbs");
  }

  private Duenio buscarDuenio(Request request, Response response) {

    String nombreUsuario = request.queryParams("nombreDeUsuario");
    String contrasenia = request.queryParams("contrasenia");

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
    return new ModelAndView(null, "formularioDeIngresoComun.hbs");
  }

  public ModelAndView mostrarPerfil(Request request, Response response) {
    try {
      Duenio duenio = this.buscarDuenio(request, response);
      return new ModelAndView(duenio, "perfil.hbs");
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "usuarioNoEncontradoComunmente.hbs");
    }
  }



}
