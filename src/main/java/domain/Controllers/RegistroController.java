package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Asociacion.UbicacionDeDominio;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import exception.UsuarioYaRegistradoException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;


public class RegistroController{

  public static ModelAndView registrarUsuario(Request request, Response response) {
    return new ModelAndView(LocalDate.now(), "registrarUsuario.hbs");
  }

  public ModelAndView crearUsuario(Request request, Response response) {

//    List<NameValuePair> pairs = URLEncodedUtils.parse(request.body(), Charset.defaultCharset());
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    String nombreUsuario = request.queryParams("nombreDeUsuario");
    String contrasenia = request.queryParams("contrasenia");
    String nombre = request.queryParams("nombre");
    String apellido = request.queryParams("apellido");
    //LocalDate fechaNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
    LocalDate fechaNacimiento = LocalDate.parse(request.queryParams("fechaDeNacimiento"));
    String tipoDeDocumento = request.queryParams("tipoDocumento");
    int numeroDeDocumento = Integer.parseInt(request.queryParams("numeroDeDocumento"));
    int telefono = Integer.parseInt(request.queryParams("telefono"));
    String email = request.queryParams("email");

    try {
        /*String nombreUsuario = pairs.get(0).getValue();
        String contrasenia = pairs.get(1).getValue();
        String nombreReal = pairs.get(2).getValue();
        String apellido = pairs.get(3).getValue();
        LocalDate fechaNacimiento = LocalDate.parse(pairs.get(4).getValue());
        String tipoDocumento = pairs.get(5).getValue();
        int numeroDocumento = Integer.parseInt(pairs.get(6).getValue());
        int telefono = Integer.parseInt(pairs.get(7).getValue());
        String email = pairs.get(8).getValue();*/

        //TODO: Obtener la asociacion del usuario, esta la hardcodeo
        UbicacionDeDominio ubiHuellas = new UbicacionDeDominio(66, 70);
        Asociacion huellas = new Asociacion(ubiHuellas);

        Contacto contactoDuenio = new Contacto(telefono, email);
        Duenio nuevoDuenio = new Duenio(nombreUsuario, contrasenia, huellas, nombre, apellido, fechaNacimiento, tipoDeDocumento, numeroDeDocumento, contactoDuenio);

        transaction.begin();
        RepositorioUsuarios.instance().guardarUsuario(nuevoDuenio);
        transaction.commit();

        response.redirect("/me");
        return new ModelAndView(nuevoDuenio, "homeLogueado.hbs");

    } catch (UsuarioYaRegistradoException a) {
      return new ModelAndView(a, "usuarioEncontradoAlRegistrar.hbs");
    }
  }


}