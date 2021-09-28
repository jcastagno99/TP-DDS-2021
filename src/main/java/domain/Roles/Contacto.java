package domain.Roles;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Embeddable
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
