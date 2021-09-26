package domain.services.HogaresDeTransitoDDS.CriteriosDeAdmision;

import domain.Mascotas.MascotaPerdidaSinChapita;
import java.util.List;

public interface CriterioAdmision {
  boolean admite(MascotaPerdidaSinChapita mascotaPerdida, List<String> caracteristicas);
}
