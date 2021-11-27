package domain.Mascotas;

import domain.Roles.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;

public class RepositorioDeMascotasRegistradas implements WithGlobalEntityManager {

  private static final RepositorioDeMascotasRegistradas INSTANCE = new RepositorioDeMascotasRegistradas();

  public static RepositorioDeMascotasRegistradas instance() {
    return INSTANCE;
  }

  private final EntityManager entityManager = this.entityManager();

  public void guardarMascota(MascotaRegistrada mascotaRegistrada) {
    entityManager.persist(mascotaRegistrada);
  }
}
