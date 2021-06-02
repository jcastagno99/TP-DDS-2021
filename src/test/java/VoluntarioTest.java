import domain.Asociacion.Asociacion;
import domain.Mascotas.*;
import domain.Roles.Contacto;
import domain.Roles.Rescatista;
import domain.Roles.Voluntario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VoluntarioTest {
  private Ubicacion ubiCallejeritos = new Ubicacion (-35, -45);
  private Asociacion callejeritos =  new Asociacion(ubiCallejeritos);
  private Contacto pepeContacto = new Contacto("Pepe Oscar",  "Mezar",540111587,"pepeMezar@pimientaNegra.com");
  private Rescatista shrek = new Rescatista("Shrek","Ogro", LocalDate.now(),"DNI",3,pepeContacto,"MuyMuyLejano");
  private MascotaPerdida unaMascota = new MascotaPerdida("fotos","alto lindo",ubiCallejeritos,shrek);
  private Voluntario elPimientas = new Voluntario("MeGustaLaPimienta","SiEsRecienMolidaMejor987",callejeritos);


  @Test
  public void unVoluntarioPuedeAprobarUnaPublicacion() {
    callejeritos.crearPublicacion(unaMascota,pepeContacto);
    assertEquals(1,callejeritos.getPublicacionesPendientes().size());
    elPimientas.aprobarPublicacion();
    assertEquals(0,callejeritos.getPublicacionesPendientes().size());
    assertEquals(1,callejeritos.getPublicaciones().size());
  }

  @Test
  public void unVoluntarioPuedeRechazarUnaPublicacion() {
    callejeritos.crearPublicacion(unaMascota,pepeContacto);
    assertEquals(1,callejeritos.getPublicacionesPendientes().size());
    elPimientas.rechazarPublicacion();
    assertEquals(0,callejeritos.getPublicacionesPendientes().size());
    assertEquals(0,callejeritos.getPublicaciones().size());
  }
}
