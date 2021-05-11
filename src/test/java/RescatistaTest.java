import domain.Mascotas.Ubicacion;
import domain.Asociacion.RepositorioMascotasPerdidas;
import domain.Roles.Contacto;
import domain.Roles.Rescatista;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RescatistaTest {

  RepositorioMascotasPerdidas repo = RepositorioMascotasPerdidas.instance();
  Ubicacion ubicacion = new Ubicacion(2474,21334);
  Contacto burroContacto = new Contacto("Burro","Rodriguez",540111587,"burroMezar@pimientaNegra.com");
  Rescatista shrek = new Rescatista("Shrek","Ogro", LocalDate.now(),"DNI",3,burroContacto,"MuyMuyLejano");

  @Test
  public void unaMascotaPerdidaSeInformaCorrectamente(){
    shrek.informarMascotaPerdida("URL","Es un munieco muy guapo y de carton", ubicacion);
    assertEquals("Es un munieco muy guapo y de carton", repo.getMascotasPerdidas().get(0).getDescripcion());
  }


}
