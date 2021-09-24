package domain.services.HogaresDeTransitoDDS.CriteriosDeAdmision;

import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Mascotas.Tamanio;

import java.util.List;

public class CriterioTamanio implements  CriterioAdmision{
  public boolean patio;

  public CriterioTamanio(boolean patio) {
    this.patio = patio;
  }

  @Override
  public boolean admite(MascotaPerdidaSinChapita mascotaPerdida, List<String> caracteristicas) {
    if (this.patio && this.tamanioParaPatio(mascotaPerdida)) {
      return true;
    } else {
      if (!this.patio && mascotaPerdida.esDeTamanio(Tamanio.PEQUENIO)) {
        return true;
      } else {
        return false; // No tiene patio y la mascota es grande o mediana
        // tiene patio pero la mascota es pequeña
      }
    }
  }

  private boolean tamanioParaPatio(MascotaPerdidaSinChapita mascotaPerdida) {
    return mascotaPerdida.esDeTamanio(Tamanio.MEDIANO) || mascotaPerdida.esDeTamanio(Tamanio.GRANDE);
  }

}
