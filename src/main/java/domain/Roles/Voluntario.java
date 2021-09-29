package domain.Roles;

import domain.Asociacion.Asociacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/* Si bien por ahora esta clase queda como un data class, no hay responsabilidades determiandas en
* el voluntario; sólo aprueba o rechaza publicaciones de mascotas perdidas sin chapita y habilita
* la comunicación con el dueño para el caso de mascotas con chapita. Es preferible tener un data
* class a cuatro misplaced methods
* */

@Entity
public class Voluntario extends Usuario {

  // TODO este campo se pone para poder persistir el Administrador, pero sin mapear la herencia por el error que aparece
  @Id
  @GeneratedValue
  private long id;

  @OneToOne
  Asociacion asociacion;

  public Voluntario(String usuario, String contrasenia, Asociacion asociacion,
                    String nombre, String apellido) {
    super(usuario, contrasenia, nombre, apellido);
    this.asociacion = asociacion;
  }

  public Voluntario(){}
}