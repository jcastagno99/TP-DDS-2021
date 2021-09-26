package domain.services.HogaresDeTransitoDDS.CriteriosDeAdmision;

import domain.Mascotas.MascotaPerdidaSinChapita;
import java.util.List;

public class CriterioCaracteristicaAdicionales implements CriterioAdmision {
  public List<String> caracteristicasHogar;

  public CriterioCaracteristicaAdicionales(List<String> caracteristicasHogar) {
    this.caracteristicasHogar = caracteristicasHogar;
  }

  @Override
  public boolean admite(MascotaPerdidaSinChapita mascotaPerdida, List<String>
      caracteristicasMascota) {
    return caracteristicasMascota.containsAll(this.caracteristicasHogar);
  }
}
