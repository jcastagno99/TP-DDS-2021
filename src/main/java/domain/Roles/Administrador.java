package domain.Roles;

import domain.Asociacion.Asociacion;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Administrador extends Usuario {

  @OneToOne
  Asociacion asociacionA_Cargo;

  public Administrador(String nombreUsuario, String contrasenia, Asociacion asociacionACargo, String nombre, String apellido) {
    super(nombreUsuario, contrasenia, nombre, apellido);
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
