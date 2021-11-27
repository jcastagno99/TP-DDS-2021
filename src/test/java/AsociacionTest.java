import domain.Asociacion.Asociacion;
import domain.Asociacion.UbicacionDeDominio;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class AsociacionTest {

  private UbicacionDeDominio ubiCallejeritos = new UbicacionDeDominio(-35, -45);
  private Asociacion callejeritos =  new Asociacion(ubiCallejeritos,"callejeritos");
  private Contacto pepeContacto = new Contacto(540111587,"pepeMezar@pimientaNegra.com");
  private Duenio pepe = new Duenio("PepeOscar",  "Mezar1234567", callejeritos,"Pepe","Chavez", LocalDate.now(),"DNI",19875698, pepeContacto);

  /*@Test
  public void seAgregaUnDuenioAUnaAsociacionCorrectamente() {
    assertTrue(callejeritos.getDueniosRegistrados().contains(pepe));
  }

  @Test
  public void unDuenioEstaSeteadoCorrectamente() {
    assertEquals(callejeritos.getDueniosRegistrados().get(0).getApellido(), pepe.getApellido());
  }*/
}
