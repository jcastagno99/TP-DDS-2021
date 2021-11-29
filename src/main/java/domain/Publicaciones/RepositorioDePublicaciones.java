package domain.Publicaciones;

import domain.Roles.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;

public class RepositorioDePublicaciones implements WithGlobalEntityManager {

  private static final RepositorioDePublicaciones INSTANCE = new RepositorioDePublicaciones();

  public static RepositorioDePublicaciones instance() {
    return INSTANCE;
  }

  public List<PublicacionMascotaPerdida> obtenerPublicacionesDeMascotasPerdidas() {
   return this.entityManager().createQuery("from PublicacionMascotaPerdida").getResultList();
  }
}
