package domain.Controllers;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import domain.Roles.*;
import exception.BusquedaEnBaseDeDatosException;
import org.omg.CORBA.PUBLIC_MEMBER;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class AutenticadorController {

  // Autentico con usuario y contrasenia (creo una sesion nueva), buscando el id y rol del usuario
  public void autenticarUsuario(Request request, Response response) {
   String nombreDeUsuario = request.queryParams("nombreDeUsuario");
   String contrasenia = request.queryParams("contrasenia");
   Rol rol = Rol.valueOf(request.queryParams("rol"));
   Usuario usuario = null;

   if (rol.equals(Rol.ADMINISTRADOR)) {
     usuario = RepositorioUsuarios.instance().buscarAdministrador(nombreDeUsuario, contrasenia);
   }
   if (rol.equals(Rol.DUENIO)) {
     usuario = RepositorioUsuarios.instance().buscarDuenio(nombreDeUsuario, contrasenia);
   }
   if (rol.equals(Rol.VOLUNTARIO)) {
     usuario = RepositorioUsuarios.instance().buscarVoluntario(nombreDeUsuario, contrasenia);
   }

   //En el caso de que se agregue un nuevo rol, se tendrá un nullPointerExcetion si no se hace otro if
   this.crearSessionUsuario(request, usuario.getId(), rol);
 }

 public void crearSessionUsuario(Request request, long idUsuario, Rol rol) {
   request.session().attribute("idUsuario", idUsuario);
   request.session().attribute("rol", rol);
 }

  // Puede ser parametrizable la ruta para ingresar según las diferentes patanllas
  /*public ModelAndView ingresar(Request request, Response response, String ruta) {
    if(this.usuarioAutenticado(request)) {
      response.redirect("/miPerfil");
      return null;
    }
    else {
      try {
        this.crearSession(request, response);
        response.redirect(ruta);
        return null;
      }
      catch (BusquedaEnBaseDeDatosException e) {
        return new ModelAndView(e, "pantallaDeLogueo.hbs");
      }
    }
 }*/

  public boolean usuarioAutenticado(Request request){
   return request.session().attribute("idUsuario") != null;
  }

  public ModelAndView autenticarDesuso(Request request, Response response) {
   ModelAndView modelo = new ModelAndView(null, null);

   if (!this.usuarioAutenticado(request)) {
     response.status(404);
     modelo = new ModelAndView(null, "paginaNoEncontrada.hbs");
     //Spark.halt();
   }
   return modelo;
  }
}
