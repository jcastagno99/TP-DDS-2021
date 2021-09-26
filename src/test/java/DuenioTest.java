import domain.Asociacion.Asociacion;
import domain.Mascotas.MascotaRegistrada;
import domain.Mascotas.Sexo;
import domain.Mascotas.TipoMascota;
import domain.Asociacion.UbicacionDeDominio;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DuenioTest {

  private MascotaRegistrada bernardo = new MascotaRegistrada(TipoMascota.PERRO,"Bernardo","Pitoto",5, Sexo.MASCULINO,"Gordo, bajito y tricolor","URL");
  private MascotaRegistrada miguelito = new MascotaRegistrada(TipoMascota.GATO,"Miguelito","Gato",1,Sexo.MASCULINO,"Gordo y naranja","URL");

  private UbicacionDeDominio ubicacionCallejeritos = new UbicacionDeDominio(34, 65);
  private Asociacion callejeritos = new Asociacion(ubicacionCallejeritos);

  private Contacto pepeContacto = new Contacto("Pepe Oscar",  "Mezar",540111587,"pepeMezar@pimientaNegra.com");
  private Duenio pepe = new Duenio("PepeOscar",  "Mezar1234567", callejeritos,"Pepe","Chavez",LocalDate.now(),"DNI",19875698,pepeContacto);


  @Test
  public void unDuenioRegistraCorrectamenteASuMascota(){
    pepe.registrarMascota(miguelito, callejeritos);
    assertTrue(miguelito.tieneDuenio(pepe));
  }

}

