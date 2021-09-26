package domain.Roles;

import domain.Asociacion.Asociacion;

public class Administrador extends Usuario {

  Asociacion asociacionA_Cargo;

  public Administrador(String nombreAdm, String contrasenia, Asociacion asociacionACargo, String nombre, String apellido) {
    super(nombreAdm, contrasenia, nombre, apellido);
    this.asociacionA_Cargo = asociacionACargo;
  }

  public void agregarNuevaCaracteristica(String caracteristicaNueva) {
    asociacionA_Cargo.agregarCaracteristicasA_Mascotas(caracteristicaNueva);
  }

  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {
    asociacionA_Cargo.eliminarCaracteristicaExistente(caracteristicaExistente);
  }
}
