import domain.Asociacion.Asociacion;
import domain.Publicaciones.EstadoPublicacion;
import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Asociacion.UbicacionDeDominio;
import domain.Mascotas.*;
import domain.Roles.Contacto;
import domain.Roles.DatosFormulario;
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
  private DatosFormulario datosFormulario = new DatosFormulario ("Shrek","Ogro", LocalDate.now(),"DNI",3, pepeContacto,"MuyMuyLejano");
  private Rescatista shrek = new Rescatista(datosFormulario);
  private DatosDeEncuentroDeMascota datos = new DatosDeEncuentroDeMascota("bBueno", ubicacionEncuentro,"foto.png");
  private MascotaPerdidaSinChapita unaMascota = new MascotaPerdidaSinChapita(shrek, datos, Tamanio.MEDIANO, TipoMascota.PERRO);
  private DatosDeEncuentroDeMascota datosDeEncuentro = new DatosDeEncuentroDeMascota("Bueno", ubicacionEncuentro, "foto.png");
  private Voluntario elPimientas = new Voluntario("MeGustaLaPimienta","SiEsRecienMolidaMejor987",callejeritos);
  private PublicacionMascotaPerdida publicacion1 = new PublicacionMascotaPerdida(unaMascota, shrek, callejeritos);
  private PublicacionMascotaPerdida publicacion2 = new PublicacionMascotaPerdida(unaMascota, shrek, callejeritos);

  @Test
  public void unVoluntarioPuedeAprobarUnaPublicacion() {
    //elPimientas.aprobarPublicacion(publicacion1);
    publicacion1.aprobar();
    assertEquals(EstadoPublicacion.APROBADA, publicacion1.getEstado());
  }

  @Test
  public void unVoluntarioPuedeRechazarUnaPublicacion() {
    //elPimientas.rechazarPublicacion(publicacion2);
    publicacion2.rechazar();
    assertEquals(EstadoPublicacion.RECHAZADA, publicacion2.getEstado());
  }
}
