import domain.Asociacion.UbicacionDeDominio;
import domain.MascotasPerdidasManagement.RepositorioMascotasPerdidas;
import domain.Roles.Contacto;
import domain.Roles.Rescatista;

import java.time.LocalDate;

public class RescatistaTest {

  private RepositorioMascotasPerdidas repo = RepositorioMascotasPerdidas.instance();
  private UbicacionDeDominio ubicacion = new UbicacionDeDominio(2474,21334);
  private Contacto burroContacto = new Contacto("Burro","Rodriguez",540111587,"burroMezar@pimientaNegra.com");
  private Rescatista shrek = new Rescatista("Shrek","Ogro", LocalDate.now(),"DNI",3,burroContacto,"MuyMuyLejano");

  // TODO Corregir este test según el refactor realizado
  /*@Test
  public void unaMascotaPerdidaSeInformaCorrectamente(){
    shrek.informarMascotaPerdidaSinChapita("URL","Es un munieco muy guapo y de carton", ubicacion, TipoMascota.PERRO, Tamanio.GRANDE);
    assertEquals("Es un munieco muy guapo y de carton", repo.getMascotasPerdidas().get(0).getDescripcion());
  }*/
}
