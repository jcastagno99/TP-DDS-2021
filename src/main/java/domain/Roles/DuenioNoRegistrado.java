package domain.Roles;

import java.time.LocalDate;

public class DuenioNoRegistrado extends PersonaNoRegistrada {

  public DuenioNoRegistrado(String nombre, String apellido, LocalDate fechaNacimiento, String tipoDocumento, int numeroDocumento, Contacto contacto, String direccion) {
    super(nombre, apellido, fechaNacimiento, tipoDocumento, numeroDocumento, contacto, direccion);
  }
}
