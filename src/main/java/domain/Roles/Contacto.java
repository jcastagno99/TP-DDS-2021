package domain.Roles;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

/* Como esta clase se utiliza en las clases Duenio, PublicacionAdopcion y DatosFormulario, si
* bien se podría embeber (tiene una relación uno a uno con Duenio, y otra relación uno a uno con
* DatosFormulario), se descarta el mapeo como @Embeddable, ya que tiene una relación de
* cardinalidad uno a muchos con PublicacionAdopcion, además de ser reutilizada en las tres clases
* mencionadas
* */

@Entity
public class Contacto {

  private Integer telefono;
  private String email;

  @Id
  @GeneratedValue
  private long id;

  public Contacto(Integer telefono, String email) {
    this.telefono = telefono;
    this.email = email;
  }

  public Contacto(){}

  public Integer getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }
}
