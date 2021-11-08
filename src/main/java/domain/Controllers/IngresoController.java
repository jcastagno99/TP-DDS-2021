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

  public ModelAndView controlarIngreso(Request request, Response response) {

    return new ModelAndView(null, "YaTeRegistraste.hbs");
  }

  public ModelAndView ingreseUsuarioYContrasenia(Request request, Response response) {

    return new ModelAndView(null, "ingreseSusDatos.hbs");
  }

  public ModelAndView buscarUsuarioYContrasenia(Request request, Response response) {

    String nombreUsuario = request.queryParams("nombreDeUsuario");
    String contrasenia = request.queryParams("contrasenia");

    try {
      Duenio duenio = RepositorioUsuarios.instance().buscarDuenio(nombreUsuario, contrasenia);
      // TODO ver por cookies hacer que la pagina guarde el usuario registrado
      return new ModelAndView(duenio, "registrarMascota.hbs");
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "usuarioNoEncontrado.hbs");
    }
  }

}
