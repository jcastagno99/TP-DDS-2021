package domain.Asociacion;

import domain.Mascotas.MascotaPerdidaSinChapita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class RepositorioMascotasPerdidas {

  private static final RepositorioMascotasPerdidas INSTANCE = new RepositorioMascotasPerdidas();

  private List<MascotaPerdidaSinChapita> mascotasPerdidas = new ArrayList<MascotaPerdidaSinChapita>();

  public static RepositorioMascotasPerdidas instance() {
    return INSTANCE;
  }

  public void agregarMascotaPerdida(MascotaPerdidaSinChapita mascota) {
    this.mascotasPerdidas.add(mascota);
  }

  public List<MascotaPerdidaSinChapita> mascotasEncontradasEnLosUltimos10Dias() {
    return this.mascotasPerdidas.stream().filter(mascota -> this.encontradaEnUltimos10Dias(mascota.getFechaEncuentro())).collect(Collectors.toList());
  }

  public boolean encontradaEnUltimos10Dias(LocalDate fecha) {
    long dias = DAYS.between(fecha, LocalDate.now());
    return dias <= 10;
  }

  public List<MascotaPerdidaSinChapita> getMascotasPerdidas() {
    return mascotasPerdidas;
  }
}