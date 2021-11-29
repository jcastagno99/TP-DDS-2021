import domain.Asociacion.Asociacion;
import domain.Asociacion.UbicacionDeDominio;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import exception.ContraseniaInvalidaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ContraseniaTest {

  private UbicacionDeDominio ubiCallejeritos = new UbicacionDeDominio(-35, -45);
  private Asociacion callejeritos =  new Asociacion(ubiCallejeritos,"callejeritos");
  private Contacto pepeContacto = new Contacto(540111587,"pepeMezar@pimientaNegra.com");
  private Duenio pepe = new Duenio("PepeOscar",  "Mezar1234567", "Pepe","Chavez", LocalDate.now(),"DNI",19875698, pepeContacto);

  @Test
  public void unaContraseniaEsDemasiadoComun(){
    Throwable exception = assertThrows(ContraseniaInvalidaException.class,() -> new Duenio("PepeOscar",  "password", "Pepe","Chavez", LocalDate.now(),"DNI",19875698, pepeContacto));
    assertEquals("La contrasenia pertenece a las 10k mas usadas", exception.getMessage());
  }

  @Test
  public void unaContraseniaEsDemasiadoCorta(){
    Throwable exception = assertThrows(ContraseniaInvalidaException.class,() -> new Duenio("Pablo","1",  "Pablo", "Fernandez", LocalDate.now(),
        "DNI", 3423423, pepeContacto));
    assertEquals("La contrasenia es demasiado corta", exception.getMessage());
  }

  @Test
  public void unaContraseniaEsValida(){
    assertEquals("Mezar1234567",pepe.getContrasenia());
  }

  @Test
  public void unaContraseniaContieneAlUsuario(){
    Throwable exception = assertThrows(ContraseniaInvalidaException.class, () -> new Duenio("Mezar",  "Mezar1234567", "Pepe","Chavez", LocalDate.now(),"DNI",19875698, pepeContacto));
    assertEquals("La contrasenia contiene al usuario", exception.getMessage());
  }
}
