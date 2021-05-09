package domain.Roles;

import domain.Refugio.Refugio;
import domain.Mascotas.TipoCaracteristica;

public class Administrador extends Usuario {

  Refugio refugioACargo;

  public Administrador(String nombreAdm, String contrasenia, Refugio refugioACargo) {
    super(nombreAdm, contrasenia);
    this.refugioACargo = refugioACargo;
  }

  public void agregarNuevaCaracteristica(TipoCaracteristica caracteristicaNueva) {
    refugioACargo.agregarCaracteristicasAMascotas(caracteristicaNueva);
  }
  public void eliminarCaracteristicaExistente(TipoCaracteristica caracteristicaExistente) {
    refugioACargo.eliminarCaracteristicaExistente(caracteristicaExistente);
  }




}
