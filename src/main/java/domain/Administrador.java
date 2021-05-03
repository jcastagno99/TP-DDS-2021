package domain;

public class Administrador extends Usuario {

  public Administrador(String nombreAdm, String contrasenia) {
    super(nombreAdm, contrasenia);
  }

  public void agregarNuevaCaracteristica(TipoCaracteristica caracteristicaNueva) {
    Refugio.instance().agregarCaracteristicasAMascotas(caracteristicaNueva);
  }
  public void eliminarCaracteristicaExistente(TipoCaracteristica caracteristicaExistente) {
    Refugio.instance().eliminarCaracteristicaExistente(caracteristicaExistente);
  }




}
