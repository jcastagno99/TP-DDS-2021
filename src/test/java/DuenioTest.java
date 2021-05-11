import domain.Asociacion.Asociacion;
import domain.Mascotas.Mascota;
import domain.Mascotas.Sexo;
import domain.Mascotas.TipoMascota;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class DuenioTest {

  Mascota bernardo = new Mascota(TipoMascota.PERRO,"Bernardo","Pitoto",5, Sexo.MASCULINO,"Gordo, bajito y tricolor","URL");
  Mascota miguelito = new Mascota(TipoMascota.GATO,"Miguelito","Gato",1,Sexo.MASCULINO,"Gordo y naranja","URL");

  Contacto pepeContacto = new Contacto("Pepe Oscar",  "Mezar",540111587,"pepeMezar@pimientaNegra.com");
  Duenio pepe = new Duenio("PepeOscar",  "Mezar1234567","Pepe","Chavez",LocalDate.now(),"DNI",19875698,pepeContacto);

  Asociacion callejeritos = new Asociacion();

  @Test
  public void unDuenioRegistraCorrectamenteASuMascota(){
    pepe.registrarMascota(miguelito, callejeritos);
    assertTrue(miguelito.tieneDuenio(pepe));
  }

}

