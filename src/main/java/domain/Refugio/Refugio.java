package domain.Refugio;

import domain.Mascotas.Mascota;
import domain.Mascotas.TipoCaracteristica;
import java.util.List;

public class Refugio {

  public List<Mascota> mascotasRegistradas;

  public void agregarCaracteristicasAMascotas(TipoCaracteristica caracteristicaNueva) {
    this.mascotasRegistradas.stream().forEach(mascota -> mascota.agregarCaracteristica(caracteristicaNueva));
  }
  public void eliminarCaracteristicaExistente(TipoCaracteristica caracteristicaExistente) {
    this.mascotasRegistradas.stream().forEach(mascota -> mascota.quitarCaracteristica(caracteristicaExistente));
  }

}
