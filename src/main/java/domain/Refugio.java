package domain;

import java.time.LocalDate;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.stream.Collectors;


public class Refugio {
  private static final Refugio INSTANCE = new Refugio();

  private List<Mascota> mascotasRegistradas;
  private List<MascotaPerdida> mascotasPerdidas;

  public static Refugio instance() {
    return INSTANCE;
  }

  void agregarMascotaPerdida(MascotaPerdida mascota) {
    this.mascotasPerdidas.add(mascota);
  }

  List<MascotaPerdida> mascotasEncontradasEnLosUltimos10Dias() {
    return this.mascotasPerdidas.stream().filter(mascota -> this.encontradaEnUltimos10Dias(mascota.getFechaEncuentro())).collect(Collectors.toList());
  }

  boolean encontradaEnUltimos10Dias(LocalDate fecha) {
    long dias = DAYS.between(fecha, LocalDate.now());
    return dias <= 10;
  }

  void agregarCaracteristicasAMascotas(TipoCaracteristica caracteristicaNueva) {
    this.mascotasRegistradas.stream().forEach(mascota -> mascota.agregarCaracteristica(caracteristicaNueva));
  }
  public void eliminarCaracteristicaExistente(TipoCaracteristica caracteristicaExistente) {
    this.mascotasRegistradas.stream().forEach(mascota -> mascota.quitarCaracteristica(caracteristicaExistente));
  }

}
