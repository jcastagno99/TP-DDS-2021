package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Asociacion.UbicacionDeDominio;
import domain.Mascotas.*;
import domain.Roles.*;
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

public class EncontreMascotaController {

  private AutenticadorController autenticadorController = new AutenticadorController();

  public ModelAndView mostrarFormDeEncuentroDeMascotaSinChapitaParaDuenio(Request request, Response response) {
    IngresoController ingresoController = new IngresoController();
    Duenio duenio = (Duenio) ingresoController.buscarUsuarioPorSessionYMostrarVista(request, response).getModel();
    return new ModelAndView(duenio, "encontreMascotaLogueado.hbs");
  }

  public ModelAndView mostrarFormDeEncuentroDeMascotaSinChapita(Request request, Response response) {
    Map<String,Object> model = new HashMap<>();
    model.put("fechaActual",LocalDate.now());
    //TODO: La asociacion deberia ser la mas cercana
    List<Asociacion> asociaciones = RepositorioAsociaciones.instance().obtenerAsociaciones();
    model.put("asociaciones",asociaciones);
    return new ModelAndView(model,"encontreMascota.hbs");
  }

  // El post se hace contra dos rutas distintas, pero que llaman al mismo método
  public ModelAndView crearPublicacionMascotaPerdida(Request request, Response response) {

    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    TipoMascota tipoMascota = TipoMascota.valueOf(request.queryParams("tipoMascota"));
    Tamanio tamanioMascota = Tamanio.valueOf(request.queryParams("tamanioMascota"));
    LocalDate fechaEncuentro = LocalDate.parse(request.queryParams("fechaEncuentro"));
    String descripcionEncuentro = request.queryParams("descripcionEncuentro");

    if (autenticadorController.usuarioAutenticado(request)) {

      Duenio duenio = RepositorioUsuarios.instance().buscarDuenioPorId(request.session().attribute("idUsuario"));

      String direccion = request.queryParams("direccion");

      DatosFormulario datosFormulario = new DatosFormulario(duenio.getNombre(), duenio.getApellido(),
          duenio.getFechaNacimiento(), duenio.getTipoDocumento(), duenio.getNumeroDocumento(), duenio.getContacto(), direccion);

      Rescatista rescatista = new Rescatista(datosFormulario);

      //TODO hardcodeo algunos de estos datos, hay que ver la implementacion de la ubicacion y de las fotos
      UbicacionDeDominio ubiEncuentro = new UbicacionDeDominio(55,55);
      DatosDeEncuentroDeMascota datosEncuentro = new DatosDeEncuentroDeMascota(descripcionEncuentro,ubiEncuentro,"asd");

      MascotaPerdidaSinChapita mascotaPerdidaSinChapita = new MascotaPerdidaSinChapita(rescatista,datosEncuentro,tamanioMascota,tipoMascota,fechaEncuentro);

      Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionA_LaQuePertenece(duenio);
      asociacion.crearPublicacion(mascotaPerdidaSinChapita,rescatista);

      transaction.begin();
      //TODO ver merge
      entityManager.persist(asociacion);
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

    Contacto contactoRescatista = new Contacto(telefono,email);

    DatosFormulario datosFormulario = new DatosFormulario(nombre,apellido,fechaNacimiento,tipoDocumento,numeroDocumento,contactoRescatista,direccion);

    Rescatista rescatista = new Rescatista(datosFormulario);

    //TODO hardcodeo algunos de estos datos, hay que ver la implementacion de la ubicacion y de las fotos
    UbicacionDeDominio ubiEncuentro = new UbicacionDeDominio(55,55);
    DatosDeEncuentroDeMascota datosEncuentro = new DatosDeEncuentroDeMascota(descripcionEncuentro,ubiEncuentro,"asd");

    MascotaPerdidaSinChapita mascotaPerdidaSinChapita = new MascotaPerdidaSinChapita(rescatista,datosEncuentro,tamanioMascota,tipoMascota,fechaEncuentro);

    //TODO: DEBERIA USAR LA ASOCIACION MAS CERCANA PERO NO FUNCAAAAAAAA
    UbicacionDeDominio ubiAnimalitos = new UbicacionDeDominio(99, 99);
    Asociacion animalitos = new Asociacion(ubiAnimalitos,"animalitos");
    animalitos.crearPublicacion(mascotaPerdidaSinChapita,rescatista);

    transaction.begin();
    entityManager.persist(animalitos);
    transaction.commit();

    return new ModelAndView(null,"home.hbs");

  }
}
