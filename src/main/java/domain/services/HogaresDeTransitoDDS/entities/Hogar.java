package domain.services.HogaresDeTransitoDDS.entities;

import domain.Asociacion.UbicacionDeDominio;
import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.services.HogaresDeTransitoDDS.CriteriosDeAdmision.CriterioAdmision;
import domain.services.HogaresDeTransitoDDS.CriteriosDeAdmision.CriterioCaracteristicaAdicionales;
import domain.services.HogaresDeTransitoDDS.CriteriosDeAdmision.CriterioTamanio;
import domain.services.HogaresDeTransitoDDS.CriteriosDeAdmision.CriterioTipo;
import java.util.Arrays;
import java.util.List;

// Clase molde

public class Hogar {

  public String id;
  public String nombre;
  public Ubicacion ubicacion;
  public String telefon;
  public Admisiones admisiones;
  public int capacidad;
  public int lugaresDisponibles;
  public boolean patio;
  public List<String> caracteristicas;
  private List<CriterioAdmision> criteriosAdmision = Arrays.asList(new CriterioTamanio(patio),
    new CriterioTipo(admisiones), new CriterioCaracteristicaAdicionales(caracteristicas));

  public boolean admitePorCriterios(MascotaPerdidaSinChapita mascota,
      List<String> caracteristicas) {
    return criteriosAdmision.stream().allMatch(
        criterio -> criterio.admite(mascota, caracteristicas));
  }

  public boolean hayCapacidad() {
    return this.capacidad >= 1;
  }


  public boolean tiene(List<String> caracteristicasMascota) {
    return caracteristicasMascota.containsAll(this.caracteristicas);
  }

  public boolean estaDentroDelRadio(UbicacionDeDominio unaUbicacion, int radioDeCercania) {
    UbicacionDeDominio auxiliar = new UbicacionDeDominio(ubicacion.getLat(),
        ubicacion.getLongitud());
    return radioDeCercania >= unaUbicacion.calcularDistanciaA(auxiliar);
  }
}
