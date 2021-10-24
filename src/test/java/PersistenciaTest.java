import domain.Asociacion.Asociacion;
import domain.Roles.Administrador;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  private Asociacion asociacion = new Asociacion(null);
  private Administrador administrador = new Administrador("mabelp", "afsfa656", null, "Mabel", "Perez");
  private Administrador administrador2 = new Administrador("pedroAbelardo", "pepeabel89522", null, "Pedro", "Fernandez");
  private Duenio duenio = new Duenio("josefern", "jose123599", asociacion, "José", "Fernández", null, null, 3212346, null);

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
    RepositorioUsuarios.instance().guardarUsuario(administrador);

    Administrador mabel = RepositorioUsuarios.instance().buscarAdministrador("mabelp", "afsfa656");

    assertEquals("Perez", mabel.getApellido());
    assertEquals("Mabel", mabel.getNombre());
  }

  @Test void unDuenioSePersisteBien() {
    RepositorioUsuarios.instance().guardarUsuario(duenio);

    Duenio jose = RepositorioUsuarios.instance().buscarDuenio("josefern", "jose123599");

    assertEquals("José", jose.getNombre());
    assertEquals("Fernández", jose.getApellido());
  }
 }
