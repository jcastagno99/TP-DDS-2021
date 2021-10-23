import domain.Roles.Administrador;
import domain.Roles.RepositorioUsuarios;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  EntityManager entityManager = this.entityManager();
  private Administrador administrador = new Administrador("mabelp", "afsfa656", null, "Mabel", "Perez");
  private Administrador administrador2 = new Administrador("pedroAbelardo", "pepeabel89522", null, "Pedro", "Fernandez");

  @BeforeEach
  public void antes() {
    this.beginTransaction();
  }

  @AfterEach
  public void despues() {
    this.rollbackTransaction();
  }

  @Test
  public void unAdministradorSePersisteBien() {
    RepositorioUsuarios.instance().guardarAdministrador(administrador);

    assertEquals("Perez", RepositorioUsuarios.instance().buscarAdministrador("mabelp", "afsfa656").getApellido());
    assertEquals("Mabel", RepositorioUsuarios.instance().buscarAdministrador("mabelp", "afsfa656").getNombre());
  }
}
