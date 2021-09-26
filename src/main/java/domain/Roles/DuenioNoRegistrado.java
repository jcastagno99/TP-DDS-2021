package domain.Roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DuenioNoRegistrado {

  @OneToOne
  DatosFormulario datos;

  @Id
  @GeneratedValue
  private long id;

  public DuenioNoRegistrado(DatosFormulario datos) {
    this.datos = datos;
  }

  public DuenioNoRegistrado(){}

  public DatosFormulario getDatos() {
    return datos;
  }

}
