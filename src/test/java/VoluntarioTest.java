import domain.Asociacion.Asociacion;
import domain.Asociacion.EstadoPublicacion;
import domain.Asociacion.PublicacionMascotaPerdida;
import domain.Mascotas.*;
import domain.Roles.Contacto;
import domain.Roles.Rescatista;
import domain.Roles.Voluntario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VoluntarioTest {
  private UbicacionDeDominio ubiCallejeritos = new UbicacionDeDominio(-35, -45);
  private UbicacionDeDominio ubicacionEncuentro = new UbicacionDeDominio(56, 189);
  private Asociacion callejeritos =  new Asociacion(ubiCallejeritos);
  private Contacto pepeContacto = new Contacto("Pepe Oscar",  "Mezar",540111587,"pepeMezar@pimientaNegra.com");
  private Rescatista shrek = new Rescatista("Shrek","Ogro", LocalDate.now(),"DNI",3, pepeContacto,"MuyMuyLejano");
  //instanciar datos de encuentro
  // private MascotaPerdidaSinChapita unaMascota = new MascotaPerdidaSinChapita(shrek,"alto lindo",ubiCallejeritos,shrek, TipoMascota.PERRO, Tamanio.MEDIANO);
  private DatosDeEncuentroDeMascota datosDeEncuentro = new DatosDeEncuentroDeMascota("Bueno", ubicacionEncuentro, "foto.png");
  private Voluntario elPimientas = new Voluntario("MeGustaLaPimienta","SiEsRecienMolidaMejor987",callejeritos);
  private PublicacionMascotaPerdida publicacion1 = new PublicacionMascotaPerdida(unaMascota, pepeContacto, callejeritos);
  private PublicacionMascotaPerdida publicacion2 = new PublicacionMascotaPerdida(unaMascota, pepeContacto, callejeritos);

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
