package domain.Controllers;
import domain.Asociacion.Asociacion;
import domain.Asociacion.UbicacionDeDominio;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import exception.UsuarioYaRegistradoException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;



public class RegistroController {

  public ModelAndView registrarUsuario(Request request, Response response) {
    return new ModelAndView(LocalDate.now(), "registrarUsuario.hbs");
  }

  public ModelAndView crearUsuario(Request request, Response response) {

    try {

        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        String nombreUsuario = request.queryParams("nombreUsuario");
        String contrasenia = request.queryParams("contrasenia");
        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        LocalDate fechaNacimiento = LocalDate.parse(request.queryParams("fechaNacimiento"));
        String tipoDocumento = request.queryParams("tipoDocumento");
        int numeroDocumento = Integer.parseInt(request.queryParams("numeroDocumento"));
        int telefono = Integer.parseInt(request.queryParams("telefono"));
        String email = request.queryParams("email");

      //TODO: Obtener la asociacion del usuario, esta la hardcodeo
      UbicacionDeDominio ubiHuellas = new UbicacionDeDominio(66, 70);
      Asociacion huellas = new Asociacion(ubiHuellas);

        Contacto contactoDuenio = new Contacto(telefono, email);
        Duenio nuevoDuenio = new Duenio(nombreUsuario, contrasenia, huellas, nombre, apellido, fechaNacimiento, tipoDocumento, numeroDocumento, contactoDuenio);

      transaction.begin();
      RepositorioUsuarios.instance().guardarUsuario(nuevoDuenio);
      transaction.commit();

        //TODO cookies
        //response.redirect("/me"); TODO: no existe ningun /me
        return new ModelAndView(nuevoDuenio, "homeLogueado.hbs");

    } catch (UsuarioYaRegistradoException a) {
      return new ModelAndView(a, "usuarioEncontradoAlRegistrar.hbs");
    }
  }

}