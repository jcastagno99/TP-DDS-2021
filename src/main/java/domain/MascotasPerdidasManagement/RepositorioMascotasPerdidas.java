package domain.MascotasPerdidasManagement;

import domain.Mascotas.MascotaPerdidaConChapita;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class RepositorioMascotasPerdidas {

  private static final RepositorioMascotasPerdidas INSTANCE = new RepositorioMascotasPerdidas();

  private List<MascotaPerdidaConChapita> mascotasPerdidas = new ArrayList<>();

  public static RepositorioMascotasPerdidas instance() {
    return INSTANCE;
  }

  public void agregarMascotaPerdida(MascotaPerdidaConChapita mascota) {
    this.mascotasPerdidas.add(mascota);
  }

  public List<MascotaPerdidaConChapita> mascotasEncontradasEnLosUltimos10Dias() {
    return this.mascotasPerdidas.stream().filter(mascota -> this.encontradaEnUltimos10Dias(mascota.getFechaDeEncuentro())).collect(Collectors.toList());
  }

  public boolean encontradaEnUltimos10Dias(LocalDate fecha) {
    long dias = DAYS.between(fecha, LocalDate.now());
    return dias <= 10;
  }

  public List<MascotaPerdidaConChapita> getMascotasPerdidas() {
    return mascotasPerdidas;
  }
}