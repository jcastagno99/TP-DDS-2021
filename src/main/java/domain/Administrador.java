package domain;

public class Administrador extends Usuario {

  public Administrador(String nombreAdm, String contrasenia) {
    super(nombreAdm, contrasenia);
  }

  public void agregarNuevaCaracteristica(String caracteristicaNueva) {
    Refugio.instance().agregarCaracteristicasAMascotas(caracteristicaNueva);
  }
  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {
    Refugio.instance().eliminarCaracteristicaExistente(caracteristicaExistente);
  }




}
