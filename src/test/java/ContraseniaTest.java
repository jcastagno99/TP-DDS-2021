import domain.Asociacion.Asociacion;
import domain.Mascotas.Ubicacion;
import domain.Roles.Usuario;
import exception.ContraseniaInvalidaException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ContraseniaTest {
  private Ubicacion ubiCallejeritos = new Ubicacion (-35, -45);
  private Asociacion callejeritos =  new Asociacion(ubiCallejeritos);
  private Usuario pepito = new Usuario("Pepe","753951pepe",callejeritos);


  @Test
  public void unaContraseniaEsDemasiadoComun(){
    Throwable exception = assertThrows(ContraseniaInvalidaException.class,()->new Usuario("Juan","password",callejeritos));
    assertEquals("La contrasenia pertenece a las 10k mas usadas",exception.getMessage());
  }

  @Test
  public void unaContraseniaEsDemasiadoCorta(){
    Throwable exception = assertThrows(ContraseniaInvalidaException.class,()->new Usuario("Pablo","1",callejeritos));
    assertEquals("La contrasenia es demasiado corta",exception.getMessage());
  }

  @Test
  public void unaContraseniaEsValida(){
    assertEquals("753951pepe",pepito.getContrasenia());
  }

  @Test
  public void unaContraseniaContieneAlUsuario(){
    Throwable exception = assertThrows(ContraseniaInvalidaException.class,()->new Usuario("Juan","1233123123Juan",callejeritos));
    assertEquals("La contrasenia contiene al usuario",exception.getMessage());
  }
}
