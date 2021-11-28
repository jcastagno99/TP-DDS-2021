package domain.Controllers;

import com.sun.org.apache.xpath.internal.functions.Function2Args;
import domain.Roles.*;
import exception.BusquedaEnBaseDeDatosException;
import exception.UsuarioYaRegistradoException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

public class IngresoController extends Controller{

  private AutenticadorController autenticadorController = new AutenticadorController();

  private void validarAutenticacion(Request request, Response response, String rutaARedireccionar) {
    if(new AutenticadorController().usuarioAutenticado(request)) {
      response.redirect(rutaARedireccionar);
      throw new UsuarioYaRegistradoException("Usuario ya autenticado");
    }
  }


  public ModelAndView preIngreso(Request request, Response response) {
    if(autenticadorController.usuarioAutenticado(request)) {
      return new ModelAndView(null, "pantallaRegistrarMascota.hbs");
    }
    return new ModelAndView(LocalDate.now(), "yaTeRegistraste2.hbs");
  }


 /* private Duenio buscarDuenio(Request request, Response response) {
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
  }*/

  public ModelAndView ingresarParaRegistrarMascota(Request request, Response response) {
    /*try {
      Duenio duenio = this.buscarDuenio(request, response);
      response.redirect("/registrarMascota");
      return null;
    } catch (BusquedaEnBaseDeDatosException e) {
      return new ModelAndView(e, "usuarioNoEncontradoRegistroMascota.hbs");
    }*/
    // Se fuerza a que este ingreso sólo sea para dueños
    request.session().attribute("rol", "duenio");
    //this.ingresar(request, response, "/miPerfil/registroDeMascota");
    //response.redirect("/miPerfil/registroDeMascota");
    this.ingresar(request, response);
    new MascotasController().mostrarFormDeRegistro(request, response);
    return null;
  }

  // El usuario ya está logueado
  public ModelAndView buscarUsuarioPorSessionYMostrarVista(Request request, Response response) {
   // Como el rol y el id se generan y eliminan en conjunto, con validar que uno sea nulo ya es suficiente
    ModelAndView perfilVista = new ModelAndView(null, null);
    Rol rol = Rol.valueOf(request.session().attribute("rol").toString());
    long usuarioId = Long.parseLong(request.session().attribute("idUsuario").toString());

    if (rol.equals(Rol.ADMINISTRADOR)) {
      Administrador administrador = RepositorioUsuarios.instance().buscarAdministradorPorId(usuarioId);
      perfilVista = new ModelAndView(administrador, "homeLogueadoAdministrador.hbs");
    }
    if (rol.equals(Rol.DUENIO)) {
      Duenio duenio = RepositorioUsuarios.instance().buscarDuenioPorId(usuarioId);
      perfilVista = new ModelAndView(duenio, "homeLogueado.hbs");
    }
    if (rol.equals(Rol.VOLUNTARIO)) {
      Voluntario voluntario = RepositorioUsuarios.instance().buscarVoluntarioPorId(usuarioId);
      perfilVista = new ModelAndView(voluntario, "homeLogueadoVoluntario.hbs");
    }
    return perfilVista;
  }

  public ModelAndView mostrarFormDeIngreso(Request request, Response response) {
    ModelAndView modelo = null;
    if(autenticadorController.usuarioAutenticado(request)) {
      response.redirect("/miPerfil");
    }
    else
    {
      modelo = new ModelAndView(null, "pantallaDeLogueo.hbs");
    }
   return modelo;
  }

  /*
  public ModelAndView buscarUsuarioPorPrimeraVezYMostrarVista(Request request, Response response){
    Rol rol = Rol.valueOf(request.queryParams("rol"));
    String nombreDeUsuario = request.queryParams("nombreDeUsuario");
    String contrasenia = request.queryParams("contrasenia");
    ModelAndView vista = new ModelAndView(null, null);

    if (rol.equals(Rol.ADMINISTRADOR)) {
      Administrador administrador = RepositorioUsuarios.instance().buscarAdministrador(nombreDeUsuario, contrasenia);
      vista = new ModelAndView(administrador, "homeLogueadoAdministrador.hbs");
    }
    if (rol.equals(Rol.DUENIO)) {
      Duenio duenio = RepositorioUsuarios.instance().buscarDuenio(nombreDeUsuario, contrasenia);
      vista = new ModelAndView(duenio, "homeLogueado.hbs");
    }
    if (rol.equals(Rol.VOLUNTARIO)) {
      Voluntario voluntario = RepositorioUsuarios.instance().buscarVoluntario(nombreDeUsuario, contrasenia);
      vista = new ModelAndView(voluntario, "homeLogueadoVoluntario.hbs");
    }
    return vista;
  }*/

  /*public ModelAndView ingresar(Request request, Response response) {
    Rol rol = Rol.valueOf(request.queryParams("rol"));
    ModelAndView vistaDeUsuario;

    // Con verificar con sólo uno de los atributos alcanza, pues los tres se setean en conjunto
    // Si no se tenía una session...
    if(request.session().attribute("idUsuario") == null) {
      try {
        // Se busca por primera vez el usuario y se asignan atributos a la session
        vistaDeUsuario = this.buscarUsuarioPorPrimeraVezYMostrarVista(request, response);
        Usuario usuario = (Usuario) vistaDeUsuario.getModel();
        request.session().attribute("idUsuario", usuario.getId());
        request.session().attribute("rol", rol);
      } catch (BusquedaEnBaseDeDatosException e) {
        return new ModelAndView(e, "pantallaDeLogueo.hbs");
      }
    }
    // Hay una session, por lo tanto un usuario existente en el sistema
    else {
     vistaDeUsuario = this.buscarUsuarioPorSessionYMostrarVista(request, response);
    }
    return vistaDeUsuario;
  }

  private void redirigirA(Request request, Response response, String ruta) {
    response.redirect(ruta);
  }*/

  // LISTO
  //Acá llegan los datos del form, al ingresar por primera vez
  public ModelAndView ingresar(Request request, Response response) {
    ModelAndView modelo = null;
    if(autenticadorController.usuarioAutenticado(request)) {
      response.redirect("/miPerfil");
    }
    else { // No está autenticado, entonces lo autentico, este es el caso por el cual se ingresa por "primera vez"
      try {
        autenticadorController.autenticarUsuario(request, response); // autentico con usuario y contrasenia del form, creo session
        response.redirect("miPerfil"); // ----> GET /miPerfil
      }
      catch (BusquedaEnBaseDeDatosException e) {
        modelo = new ModelAndView(e, "pantallaDeLogueo.hbs");
      }
    }
    return modelo;
  }
/*
  public ModelAndView registrarMascota(Request request, Response response){
    return new ModelAndView(null, "registrarMascota.hbs");
  }*/

  public ModelAndView cerrarSesion(Request request, Response response) {
    request.session().removeAttribute("rol");
    request.session().removeAttribute("idUsuario");
    //request.session().invalidate();
    response.redirect("/");
    // Después de un response.redirect("ruta") se puede poner return null porque justamente la respuesta fue redirigida y null no será procesado por el templateEngine
    return null;
  }
}
