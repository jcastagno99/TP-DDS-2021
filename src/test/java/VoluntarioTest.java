import domain.Asociacion.Asociacion;
import domain.Asociacion.EstadoPublicacion;
import domain.Asociacion.Publicacion;
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
  private Publicacion publicacion1 = new Publicacion(unaMascota, pepeContacto, callejeritos);
  private Publicacion publicacion2 = new Publicacion(unaMascota, pepeContacto, callejeritos);

  @Test
  public void unVoluntarioPuedeAprobarUnaPublicacion() {
    elPimientas.aprobarPublicacion(publicacion1);
    assertEquals(EstadoPublicacion.APROBADA, publicacion1.getEstado());
  }

  @Test
  public void unVoluntarioPuedeRechazarUnaPublicacion() {
    elPimientas.rechazarPublicacion(publicacion2);
    assertEquals(EstadoPublicacion.RECHAZADA, publicacion2.getEstado());
  }
}
