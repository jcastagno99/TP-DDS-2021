package domain.Controllers;
import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import domain.Roles.Rol;
import exception.ContraseniaInvalidaException;
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

  private AutenticadorController autenticadorController = new AutenticadorController();

  public ModelAndView mostrarFormDeRegistroDeUsuario(Request request, Response response) {
   //ModelAndView modelo = new ModelAndView(null, null);
    ModelAndView modelo = null;

    if (autenticadorController.usuarioAutenticado(request)) {
      response.redirect("/miPerfil");
    }

    else {
      Map<String,Object> model = new HashMap<>();

      model.put("fechaActual",LocalDate.now());
      List<Asociacion> asociaciones = RepositorioAsociaciones.instance().obtenerAsociaciones();
      model.put("asociaciones",asociaciones);

      modelo = new ModelAndView(model, "registrarUsuario.hbs");
    }

    return modelo;
  }

  public ModelAndView registrarUsuario(Request request, Response response) {
    ModelAndView modelo = new ModelAndView(null, null);
    if (autenticadorController.usuarioAutenticado(request)) {
      response.redirect("/miPerfil");
    }
    else {
      try {
        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        /* El rol en este caso se utilizaría para hacer trs ifs y determinar qué clase instanciar:
         * Duenio, Voluntario, Administrador. Como por ahora sólo se permite que los duños puedan registrarse,
         * queda una puerta para aportar extensibilidad a futuro. Más abajo, el rol se setea a Rol.Duenio
         */

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

        Duenio nuevoDuenio = new Duenio(nombreUsuario, contrasenia, nombre, apellido, fechaNacimiento, tipoDocumento, numeroDocumento, contactoDuenio);

        nuevoDuenio.registrarEn(asociacionElegida);

        transaction.begin();
        RepositorioUsuarios.instance().actualizarUsuario(nuevoDuenio);
        transaction.commit();

        Duenio duenioAAutenticar = RepositorioUsuarios.instance().buscarDuenio(nombreUsuario, contrasenia);

        autenticadorController.crearSessionUsuario(request, duenioAAutenticar.getId(), Rol.DUENIO);

        response.redirect("/miPerfil");

      } catch (UsuarioYaRegistradoException | ContraseniaInvalidaException e) {
        //buscar asociaciones y meter en map
        modelo = new ModelAndView(e, "registrarUsuario.hbs");
      }
    }
    return modelo;
  }
}