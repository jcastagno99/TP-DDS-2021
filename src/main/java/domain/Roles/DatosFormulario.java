package domain.Roles;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DatosFormulario {
  protected String nombre;
  protected String apellido;
  protected LocalDate fechaNacimiento;
  protected String tipoDocumento;
  protected int numeroDocumento;

  @OneToOne
  protected Contacto contacto;
  protected String direccion;

  @Id
  @GeneratedValue
  private long id;

  public DatosFormulario(String nombre, String apellido, LocalDate fechaNacimiento,
      String tipoDocumento, int numeroDocumento, Contacto contacto, String direccion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;
    this.direccion = direccion;
  }

  public DatosFormulario(){}

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
