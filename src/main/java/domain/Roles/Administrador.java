package domain.Roles;

import domain.Asociacion.Asociacion;

public class Administrador extends Usuario {

  Asociacion asociacionACargo;

  public Administrador(String nombreAdm, String contrasenia, Asociacion asociacionACargo) {
    super(nombreAdm, contrasenia, asociacionACargo);
    this.asociacionACargo = asociacionACargo;
  }

  public void agregarNuevaCaracteristica(String caracteristicaNueva) {
    asociacionACargo.agregarCaracteristicasAMascotas(caracteristicaNueva);
  }
  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {
    asociacionACargo.eliminarCaracteristicaExistente(caracteristicaExistente);
  }




}
