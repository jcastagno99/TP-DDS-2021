package domain.Roles;

import java.time.LocalDate;

// Si bien esta clase queda como un data class, es una de las formas posibles para unificar los
// datos del formulario del rescatista y del duenio NO REGISTRADO en el sistema. Otra opción sería
// crear una abstracción DatosFormulario

public class PersonaNoRegistrada {
  protected String nombre;
  protected String apellido;
  protected LocalDate fechaNacimiento;
  protected String tipoDocumento;
  protected int numeroDocumento;
  protected Contacto contacto;
  protected String direccion;

  public PersonaNoRegistrada(String nombre, String apellido, LocalDate fechaNacimiento, String tipoDocumento, int numeroDocumento, Contacto contacto, String direccion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;
    this.direccion = direccion;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public Contacto getContacto() {
    return this.contacto;
  }

  public String obtenerMail() {
    return this.contacto.getEmail();
  }

  public Integer obtenerTelefono() {
    return this.contacto.getTelefono();
  }
}