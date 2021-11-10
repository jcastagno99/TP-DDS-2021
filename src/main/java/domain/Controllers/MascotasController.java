package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Mascotas.MascotaRegistrada;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MascotasController {

  public ModelAndView mostrarFormDeRegistro(Request request, Response response){
    return new ModelAndView(null, "pantallaRegistrarMascota.hbs");
  }

  public ModelAndView registrarMascota(Request request, Response response) {
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    String nombreMascota = request.queryParams("nombreMascota");
    String tipoMascota = request.queryParams("tipo");
    String apodoMascota = request.queryParams("apodoMascota");
    int edadAproximada = Integer.parseInt(request.queryParams("edadAproximada"));
    String sexoMascota = request.queryParams("sexoMascota");
    String descripcion = request.queryParams("descripcionFisica");
    String foto = request.queryParams("foto");
    String usuario = request.cookie("nombreDeUsuario");

    Duenio duenioMascota = RepositorioUsuarios.instance().buscarDuenioMedianteUsuario(usuario);
    Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionA_LaQuePertenece(duenioMascota);
    MascotaRegistrada mascota = new MascotaRegistrada(nombreMascota, tipoMascota, apodoMascota, edadAproximada, sexoMascota,);

    transaction.begin();
    asociacion.agregarMascota(mascota);
    transaction.commit();

    return new ModelAndView(null, "mascotaBienGuardada.hbs"); // redirigir a segundo hbs imprimiento mensaje de registro corecto
  }
}
