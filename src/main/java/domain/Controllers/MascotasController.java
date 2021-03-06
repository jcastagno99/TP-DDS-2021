package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Asociacion.Caracteristica;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Mascotas.MascotaRegistrada;
import domain.Mascotas.RepositorioDeMascotasRegistradas;
import domain.Mascotas.Sexo;
import domain.Mascotas.TipoMascota;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MascotasController {

  AutenticadorController autenticadorController = new AutenticadorController();
  public ModelAndView mostrarFormDeRegistro(Request request, Response response){
    if(!autenticadorController.usuarioAutenticado(request)) {
      response.redirect("/");
    }
    Map<String,Object> model = new HashMap<>();
    Duenio duenio = RepositorioUsuarios.instance().buscarDuenioPorId(request.session().attribute("idUsuario"));
    Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionA_LaQuePerteneceDuenio(duenio);
    List<String> caracteristicas = asociacion.getCaracteristicasPedidas();
    model.put("tiposCaracteristicas",caracteristicas);
    return new ModelAndView(model, "pantallaRegistrarMascota.hbs");
  }

  /* Sólo podrán registrar mascotas los usuarios logueados como dueños. En el caso de que un voluntario
  * o administrador quisiera hacerlo, deberían crearse otro usuario. Igualmente, el modelo podría ser extensible,
  * dependiendo de las pantallas ofrecidas para cada rol, y específicamente las barras de navegación para cada una*/

  public ModelAndView registrarMascota(Request request, Response response) {

    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    String nombreMascota = request.queryParams("nombreMascota");
    TipoMascota tipoMascota = TipoMascota.valueOf(request.queryParams("tipoMascota"));
    String apodoMascota = request.queryParams("apodoMascota");
    int edadAproximada = Integer.parseInt(request.queryParams("edadAproximada"));
    Sexo sexoMascota = Sexo.valueOf(request.queryParams("sexoMascota"));
    String descripcion = request.queryParams("descripcionFisica");
    String foto = request.queryParams("foto");
    long idDuenio = request.session().attribute("idUsuario");
    Duenio duenioMascota = RepositorioUsuarios.instance().buscarDuenioPorId(idDuenio);

    //indexbound exception
    Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionA_LaQuePerteneceDuenio(duenioMascota);
    MascotaRegistrada mascota = new MascotaRegistrada(tipoMascota, nombreMascota, apodoMascota, edadAproximada, sexoMascota, descripcion, duenioMascota, foto);

    asociacion.getCaracteristicasPedidas().forEach(tipoCaracteristica -> mascota.agregarCaracteristica(new Caracteristica(tipoCaracteristica, request.queryParams(tipoCaracteristica))));

    mascota.setDuenio(duenioMascota);
    mascota.registrarEn(asociacion);

    transaction.begin();
    RepositorioDeMascotasRegistradas.instance().guardarMascota(mascota);
    transaction.commit();

    entityManager.clear();
    return null;
  }

  public ModelAndView crearQRParaMascota(Request request, Response response) {
    Duenio juan = RepositorioUsuarios.instance().buscarDuenio("juan", "matias1234");
    MascotaRegistrada mascotaRegistrada = new MascotaRegistrada(TipoMascota.PERRO,"asdasd","asdasd",5,Sexo.MASCULINO,"asdasd",juan,"asdasd");
    return new ModelAndView(mascotaRegistrada, "qr.hbs");
  }

  public ModelAndView mostrarMascotasRegistradas(Request request, Response response) {
    long idUsuario = request.session().attribute("idUsuario");
    Duenio duenio = RepositorioUsuarios.instance().buscarDuenioPorId(idUsuario);
    List<MascotaRegistrada> mascotaRegistradas = duenio.getMascotaRegistradas();
    Map<String, Object> model = new HashMap<>();
    model.put("mascotasRegistradas", mascotaRegistradas);
    return new ModelAndView(model, "mascotasRegistradas.hbs");
  }
}
