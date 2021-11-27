package domain.Controllers;

import domain.Asociacion.Asociacion;
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

public class EncontreMascotaController {
  private AutenticadorController autenticadorController = new AutenticadorController();

  public ModelAndView mostrarFormDeEncuentroDeMascotaSinChapitaParaDuenio(Request request, Response response) {
    //return new ModelAndView(null,"encontreMascota.hbs");
    IngresoController ingresoController = new IngresoController();
    Duenio duenio = (Duenio) ingresoController.buscarUsuarioPorSessionYMostrarVista(request, response).getModel();
    return new ModelAndView(duenio,"formularioMascotaSinChapitaLogueado.hbs");
  }

  public ModelAndView registrarMascotaSinChapita(Request request, Response response) {
    if(request.cookie("nombreDeUsuario") != null){
      Duenio duenio = RepositorioUsuarios.instance().buscarDuenioMedianteUsuario(request.cookie("nombreDeUsuario"));
      return new ModelAndView(duenio,"formularioMascotaSinChapitaLogueado.hbs");
    }
    return new ModelAndView(null,"formularioMascotaSinChapita.hbs");
  }

  public ModelAndView crearPublicacionMascotaPerdida(Request request, Response response) {

    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    TipoMascota tipoMascota = TipoMascota.valueOf(request.queryParams("tipoMascota"));
    Tamanio tamanioMascota = Tamanio.valueOf(request.queryParams("tamanioMascota"));
    LocalDate fechaEncuentro = LocalDate.parse(request.queryParams("fechaEncuentro"));
    String descripcionEncuentro = request.queryParams("descripcionEncuentro");
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

    //TODO: Obtener la asociacion del usuario, esta la hardcodeo
    UbicacionDeDominio ubiAnimalitos = new UbicacionDeDominio(99, 99);
    Asociacion animalitos = new Asociacion(ubiAnimalitos,"animalitos");
    animalitos.crearPublicacion(mascotaPerdidaSinChapita,rescatista);

    //TODO: Solo funciona porque estoy creando la asociacion de 0, se supone que la asociacion ya tiene que estar cargada de antes y esto tiene que ser un UPDATE
    transaction.begin();
    entityManager.persist(animalitos);
    transaction.commit();

    return new ModelAndView(null,"home.hbs");
  }

  public ModelAndView crearPublicacionMascotaPerdidaLogueado(Request request, Response response) {

    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    Duenio duenio = RepositorioUsuarios.instance().buscarDuenioMedianteUsuario(request.cookie("nombreDeUsuario"));

    TipoMascota tipoMascota = TipoMascota.valueOf(request.queryParams("tipoMascota"));
    Tamanio tamanioMascota = Tamanio.valueOf(request.queryParams("tamanioMascota"));
    LocalDate fechaEncuentro = LocalDate.parse(request.queryParams("fechaEncuentro"));
    String descripcionEncuentro = request.queryParams("descripcionEncuentro");
    String direccion = request.queryParams("direccion");

    DatosFormulario datosFormulario = new DatosFormulario(duenio.getNombre(), duenio.getApellido(),
        duenio.getFechaNacimiento(), duenio.getTipoDocumento(), duenio.getNumeroDocumento(), duenio.getContacto(), direccion);

    Rescatista rescatista = new Rescatista(datosFormulario);

    //TODO hardcodeo algunos de estos datos, hay que ver la implementacion de la ubicacion y de las fotos
    UbicacionDeDominio ubiEncuentro = new UbicacionDeDominio(55,55);
    DatosDeEncuentroDeMascota datosEncuentro = new DatosDeEncuentroDeMascota(descripcionEncuentro,ubiEncuentro,"asd");

    MascotaPerdidaSinChapita mascotaPerdidaSinChapita = new MascotaPerdidaSinChapita(rescatista,datosEncuentro,tamanioMascota,tipoMascota,fechaEncuentro);

    //TODO: Obtener la asociacion del usuario, esta la hardcodeo
    UbicacionDeDominio ubiAnimalitos = new UbicacionDeDominio(99, 99);
    Asociacion animalitos = new Asociacion(ubiAnimalitos,"animalitos");
    animalitos.crearPublicacion(mascotaPerdidaSinChapita,rescatista);

    //TODO: Solo funciona porque estoy creando la asociacion de 0, se supone que la asociacion ya tiene que estar cargada de antes y esto tiene que ser un UPDATE
    transaction.begin();
    entityManager.persist(animalitos);
    transaction.commit();

    return new ModelAndView(duenio,"homeLogueado.hbs");
  }

}
