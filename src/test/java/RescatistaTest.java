import domain.Mascotas.Tamanio;
import domain.Mascotas.TipoMascota;
import domain.Mascotas.Ubicacion;
import domain.Asociacion.RepositorioMascotasPerdidas;
import domain.Roles.Contacto;
import domain.Roles.Rescatista;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RescatistaTest {

  private RepositorioMascotasPerdidas repo = RepositorioMascotasPerdidas.instance();
  private Ubicacion ubicacion = new Ubicacion(2474,21334);
  private Contacto burroContacto = new Contacto("Burro","Rodriguez",540111587,"burroMezar@pimientaNegra.com");
  private Rescatista shrek = new Rescatista("Shrek","Ogro", LocalDate.now(),"DNI",3,burroContacto,"MuyMuyLejano");

  @Test
  public void unaMascotaPerdidaSeInformaCorrectamente(){
    shrek.informarMascotaPerdidaSinChapita("URL","Es un munieco muy guapo y de carton", ubicacion, TipoMascota.PERRO, Tamanio.GRANDE);
    assertEquals("Es un munieco muy guapo y de carton", repo.getMascotasPerdidas().get(0).getDescripcion());
  }
}
