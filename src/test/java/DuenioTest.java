import domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class DuenioTest {

  Mascota bernardo = new Mascota(TipoMascota.PERRO,"Bernardo","Pitoto",5, Sexo.MASCULINO,"Gordo, bajito y tricolor");
  Mascota miguelito = new Mascota(TipoMascota.GATO,"Miguelito","Gato",1,Sexo.MASCULINO,"Gordo y naranja");

  Contacto pepeContacto = new Contacto("Pepe Oscar Mezar",540111587,"pepeMezar@pimientaNegra.com");
  Duenio unDuenio = new Duenio("Pepe",LocalDate.now(),"DNI",19875698,pepeContacto);

  @Test
  public void unaMascotaSeRegistraCorrectamenteAUnDuenio(){
    unDuenio.registrarMascota(miguelito);
    assertEquals(true,unDuenio.getMascotas().contains(miguelito));
  }

  @Test
  public void unaMascotaNoRegistradaNoApareceEnLaListaDelDuenio(){
    unDuenio.registrarMascota(bernardo);
    assertEquals(false,unDuenio.getMascotas().contains(miguelito));
  }
}

