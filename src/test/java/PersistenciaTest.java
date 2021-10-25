import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Asociacion.UbicacionDeDominio;
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

  private UbicacionDeDominio ubiCallejeritos = new UbicacionDeDominio(-35, -45);
  private UbicacionDeDominio ubiCallejeritosCerca = new UbicacionDeDominio(-39, -49);
  private UbicacionDeDominio ubiAsociacion = new UbicacionDeDominio(-60, -70);
  private Asociacion callejeritos =  new Asociacion(ubiCallejeritos);
  private Asociacion callejeritosCerca =  new Asociacion(ubiCallejeritosCerca);
  private Asociacion asociacion = new Asociacion(ubiAsociacion);
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

  @Test void lasAsociacionesSePersistenYPuedoObtenerlas(){
    RepositorioAsociaciones.instance().agregarAsociacion(callejeritos);
    RepositorioAsociaciones.instance().agregarAsociacion(asociacion);

    assertEquals(2,RepositorioAsociaciones.instance().obtenerAsociaciones().size());
  }

  @Test void obtengoLaAsociacionMasCercana(){
    RepositorioAsociaciones.instance().agregarAsociacion(callejeritos);
    RepositorioAsociaciones.instance().agregarAsociacion(asociacion);
    RepositorioAsociaciones.instance().agregarAsociacion(callejeritosCerca);

    assertEquals(callejeritosCerca,RepositorioAsociaciones.instance().obtenerAsociacionMasCercaA(ubiCallejeritosCerca));

  }


 }
