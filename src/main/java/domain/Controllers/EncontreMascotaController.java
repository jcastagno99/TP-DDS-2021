package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Asociacion.UbicacionDeDominio;
import domain.Mascotas.*;
import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Roles.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncontreMascotaController implements WithGlobalEntityManager {

  private AutenticadorController autenticadorController = new AutenticadorController();

  public ModelAndView mostrarFormDeEncuentroDeMascotaSinChapitaParaDuenio(Request request, Response response) {
    IngresoController ingresoController = new IngresoController();
    Duenio duenio = (Duenio) ingresoController.buscarUsuarioPorSessionYMostrarVista(request, response).getModel();
    return new ModelAndView(duenio, "encontreMascotaLogueado.hbs");
  }

  public ModelAndView mostrarFormDeEncuentroDeMascotaSinChapita(Request request, Response response) {
    if(autenticadorController.usuarioAutenticado(request)) {
      response.redirect("/miPerfil/encuentroDeMascota");
      return null;
    }
    else {
      Map<String,Object> model = new HashMap<>();
      model.put("fechaActual", LocalDate.now());
      //TODO: La asociacion deberia ser la mas cercana
      List<Asociacion> asociaciones = RepositorioAsociaciones.instance().obtenerAsociaciones();
      model.put("asociaciones", asociaciones);
      return new ModelAndView(model, "encontreMascota.hbs");
    }
  }

  // El post se hace contra dos rutas distintas, pero que llaman al mismo método
  public ModelAndView crearPublicacionMascotaPerdida(Request request, Response response) {

    EntityManager entityManager = this.entityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    TipoMascota tipoMascota = TipoMascota.valueOf(request.queryParams("tipoMascota"));
    Tamanio tamanioMascota = Tamanio.valueOf(request.queryParams("tamanioMascota"));
    LocalDate fechaEncuentro = LocalDate.parse(request.queryParams("fechaEncuentro"));
    String descripcionEncuentro = request.queryParams("descripcionEncuentro");

    // TODO la ubicacion deberia ser seleccoinable de un mapa

    UbicacionDeDominio ubicacionEncuentro = new UbicacionDeDominio(55,55);

    if (autenticadorController.usuarioAutenticado(request)) {

      Duenio duenio = RepositorioUsuarios.instance().buscarDuenioPorId(request.session().attribute("idUsuario"));
      Asociacion asociacionDondeSePublicara =  RepositorioAsociaciones.instance().obtenerAsociacionA_LaQuePerteneceDuenio(duenio);
      String direccion = request.queryParams("direccion");

      DatosFormulario datosFormulario = new DatosFormulario(duenio.getNombre(), duenio.getApellido(),
          duenio.getFechaNacimiento(), duenio.getTipoDocumento(), duenio.getNumeroDocumento(), duenio.getContacto(), direccion);

      Rescatista rescatista = new Rescatista(datosFormulario);

      //TODO hardcodeo algunos de estos datos, hay que ver la implementacion de la ubicacion y de las fotos
      // La ubicacion deberia ser seleccionable en ambos casos

      DatosDeEncuentroDeMascota datosEncuentro = new DatosDeEncuentroDeMascota(descripcionEncuentro, ubicacionEncuentro, "asd");

      MascotaPerdidaSinChapita mascotaPerdidaSinChapita = new MascotaPerdidaSinChapita(rescatista, datosEncuentro, tamanioMascota, tipoMascota, fechaEncuentro);

      asociacionDondeSePublicara.crearPublicacion(mascotaPerdidaSinChapita,rescatista);

      transaction.begin();
      entityManager.persist(asociacionDondeSePublicara);
      transaction.commit();

      return new ModelAndView(duenio,"homeLogueado.hbs");
    }

    String nombre = request.queryParams("nombre");
    String apellido = request.queryParams("apellido");
    LocalDate fechaNacimiento = LocalDate.parse(request.queryParams("fechaNacimiento"));
    String tipoDocumento = request.queryParams("tipoDocumento");
    int numeroDocumento = Integer.parseInt(request.queryParams("numeroDocumento"));
    int telefono = Integer.parseInt(request.queryParams("telefono"));
    String email = request.queryParams("email");
    String direccion = request.queryParams("direccion");
    // TODO debería ser la más cercana en función de la ubicación de encuentro o del usuario
    String asociacion = request.queryParams("asociacion");
    Asociacion asociacionDondeSePublicara = RepositorioAsociaciones.instance().obtenerAsociacionPorNombre(asociacion);

    Contacto contactoRescatista = new Contacto(telefono, email);

    DatosFormulario datosFormulario = new DatosFormulario(nombre, apellido, fechaNacimiento, tipoDocumento, numeroDocumento, contactoRescatista, direccion);

    Rescatista rescatista = new Rescatista(datosFormulario);

    DatosDeEncuentroDeMascota datosEncuentro = new DatosDeEncuentroDeMascota(descripcionEncuentro,ubicacionEncuentro,"asd");

    MascotaPerdidaSinChapita mascotaPerdidaSinChapita = new MascotaPerdidaSinChapita(rescatista, datosEncuentro, tamanioMascota, tipoMascota, fechaEncuentro);

    asociacionDondeSePublicara.crearPublicacion(mascotaPerdidaSinChapita, rescatista);

    asociacionDondeSePublicara.crearPublicacion(mascotaPerdidaSinChapita,rescatista);

    transaction.begin();
    entityManager.persist(asociacionDondeSePublicara);
    transaction.commit();

    return new ModelAndView(null,"home.hbs");

  }

  public ModelAndView mostrarPublicacionesDeMascotasPerdidas(Request request, Response response) {
    return new ModelAndView(null, "publicacionesDeMascotasPerdidas.hbs");
  }
}

