package domain.Roles;

import domain.Asociacion.Asociacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Administrador extends Usuario {

  // TODO este campo se pone para poder persistir el Administrador, pero sin mapear la herencia por el error que aparece
  @Id
  @GeneratedValue
  private long id;

  @OneToOne
  Asociacion asociacionA_Cargo;

  public Administrador(String nombreAdm, String contrasenia, Asociacion asociacionACargo, String nombre, String apellido) {
    super(nombreAdm, contrasenia, nombre, apellido);
    this.asociacionA_Cargo = asociacionACargo;
  }

  public Administrador(){}


  public void agregarNuevaCaracteristica(String caracteristicaNueva) {
    asociacionA_Cargo.agregarCaracteristicasA_Mascotas(caracteristicaNueva);
  }

  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {
    asociacionA_Cargo.eliminarCaracteristicaExistente(caracteristicaExistente);
  }
}
