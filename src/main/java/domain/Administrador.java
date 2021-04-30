package domain;

public class Administrador {

  String nombreAdm;
  //TODO
  String contrasenia;

  public void agregarNuevaCaracteristica(String caracteristicaNueva) {
    Refugio.instance().agregarCaracteristicasAMascotas(caracteristicaNueva);
  }
  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {
    Refugio.instance().eliminarCaracteristicaExistente(caracteristicaExistente);
  }




}
