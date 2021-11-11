package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Mascotas.MascotaRegistrada;
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

public class MascotasController {

  public ModelAndView mostrarFormDeRegistro(Request request, Response response){
    return new ModelAndView(null, "pantallaRegistrarMascota.hbs");
  }

  public ModelAndView registrarMascota(Request request, Response response) {

    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    String nombreMascota = request.queryParams("nombreMascota");
    TipoMascota tipoMascota = TipoMascota.valueOf(request.queryParams("tipo"));
    String apodoMascota = request.queryParams("apodoMascota");
    int edadAproximada = Integer.parseInt(request.queryParams("edadAproximada"));
    Sexo sexoMascota = Sexo.valueOf(request.queryParams("sexoMascota"));
    String descripcion = request.queryParams("descripcionFisica");
    String foto = request.queryParams("foto");
    String usuario = request.cookie("nombreDeUsuario");

    Duenio duenioMascota = RepositorioUsuarios.instance().buscarDuenioMedianteUsuario(usuario);
    Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionA_LaQuePertenece(duenioMascota);
    MascotaRegistrada mascota = new MascotaRegistrada(tipoMascota, nombreMascota, apodoMascota, edadAproximada, sexoMascota, descripcion, foto);

    transaction.begin();
    asociacion.agregarMascota(mascota);
    entityManager.persist(mascota);
    transaction.commit();

    response.cookie("nombreUsuario", duenioMascota.getUsuario());
    response.redirect("/miPerfil");
    return null;
  }
}
