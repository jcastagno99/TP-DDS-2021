package domain.Controllers;
import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import exception.UsuarioYaRegistradoException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegistroController {

  public ModelAndView registrarUsuario(Request request, Response response) {

    Map<String,Object> model = new HashMap<>();
    model.put("fechaActual",LocalDate.now());
    List<Asociacion> asociaciones = RepositorioAsociaciones.instance().obtenerAsociaciones();
    model.put("asociaciones",asociaciones);

    return new ModelAndView(model, "registrarUsuario.hbs");
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

        Asociacion asociacionElegida = RepositorioAsociaciones.instance().obtenerAsociacionPorNombre(request.queryParams("asociaciones"));

        Contacto contactoDuenio = new Contacto(telefono, email);
        Duenio nuevoDuenio = new Duenio(nombreUsuario, contrasenia, asociacionElegida, nombre, apellido, fechaNacimiento, tipoDocumento, numeroDocumento, contactoDuenio);

        asociacionElegida.agregarNuevoDuenio(nuevoDuenio);

        transaction.begin();
        entityManager.persist(asociacionElegida);
        transaction.commit();

        response.cookie("nombreDeUsuario",nombreUsuario);
        response.cookie("contrasenia",contrasenia);

        return new ModelAndView(nuevoDuenio, "homeLogueado.hbs");

    } catch (UsuarioYaRegistradoException a) {
      return new ModelAndView(a, "usuarioEncontradoAlRegistrar.hbs");
    }
  }

}