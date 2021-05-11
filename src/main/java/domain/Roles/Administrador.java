package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Mascotas.TipoCaracteristica;

public class Administrador extends Usuario {

  Asociacion asociacionACargo;

  public Administrador(String nombreAdm, String contrasenia, Asociacion asociacionACargo) {
    super(nombreAdm, contrasenia);
    this.asociacionACargo = asociacionACargo;
  }

  public void agregarNuevaCaracteristica(TipoCaracteristica caracteristicaNueva) {
    asociacionACargo.agregarCaracteristicasAMascotas(caracteristicaNueva);
  }
  public void eliminarCaracteristicaExistente(TipoCaracteristica caracteristicaExistente) {
    asociacionACargo.eliminarCaracteristicaExistente(caracteristicaExistente);
  }




}
