package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Asociacion.UbicacionDeDominio;
import domain.Mascotas.DatosDeEncuentroDeMascota;
import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Mascotas.Tamanio;
import domain.Mascotas.TipoMascota;
import domain.MascotasPerdidasManagement.RepositorioMascotasPerdidas;
import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Roles.Contacto;
import domain.Roles.DatosFormulario;
import domain.Roles.RepositorioUsuarios;
import domain.Roles.Rescatista;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class EncontreMascotaController {
  public ModelAndView encontreUnaMascota(Request request, Response response) {
    return new ModelAndView(null,"encontreMascota.hbs");
  }

  public ModelAndView registrarMascotaSinChapita(Request request, Response response) {
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
    Asociacion animalitos = new Asociacion(ubiAnimalitos);
    animalitos.crearPublicacion(mascotaPerdidaSinChapita,rescatista);

    //TODO: Solo funciona porque estoy creando la asociacion de 0, se supone que la asociacion ya tiene que estar cargada de antes y esto tiene que ser un UPDATE
    transaction.begin();
    entityManager.persist(animalitos);
    transaction.commit();

    return new ModelAndView(null,"home.hbs");

  }
}
