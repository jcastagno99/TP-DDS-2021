package domain.services.HogaresDeTransitoDDS.CriteriosDeAdmision;

import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Mascotas.TipoMascota;
import domain.services.HogaresDeTransitoDDS.entities.Admisiones;
import java.util.List;

public class CriterioTipo implements CriterioAdmision {
  public Admisiones admisiones;

  public CriterioTipo(Admisiones admisiones) {
    this.admisiones = admisiones;
  }

  @Override
  public boolean admite(MascotaPerdidaSinChapita mascotaPerdida, List<String> caracteristicas) {
    return mascotaPerdida.esDeTipo(TipoMascota.PERRO) && this.admisiones.perros()
        || mascotaPerdida.esDeTipo(TipoMascota.GATO) && this.admisiones.gatos();
  }
}
