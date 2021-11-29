package domain.MascotasPerdidasManagement;

import domain.Mascotas.MascotaPerdidaConChapita;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class RepositorioMascotasPerdidas implements WithGlobalEntityManager {

  private static final RepositorioMascotasPerdidas INSTANCE = new RepositorioMascotasPerdidas();

  //private List<MascotaPerdidaConChapita> mascotasPerdidas = new ArrayList<>();

  public static RepositorioMascotasPerdidas instance() {
    return INSTANCE;
  }

  public void agregarMascotaPerdida(MascotaPerdidaConChapita mascota) {
    this.entityManager().persist(mascota);
  }

  public List<MascotaPerdidaConChapita> mascotasEncontradasEnLosUltimos10Dias() {
    LocalDate fechaActual = LocalDate.now();
    LocalDate fechaDeHace10Dias = LocalDate.now().minusDays(10);
    // TODO debería hacerse una conversión
    //return this.mascotasPerdidas.stream().filter(mascota -> this.encontradaEnUltimos10Dias(mascota.getFechaDeEncuentro())).collect(Collectors.toList());
    return this.entityManager().createQuery("from MascotaPerdidaConChapita m where m.fechaEncuentro between :fechaDeHace10Dias and :fechaActual")
        .setParameter("fechaDeHace10Dias", fechaDeHace10Dias)
        .setParameter("fechaActual", fechaActual).getResultList();
  }

  /*public boolean encontradaEnUltimos10Dias(LocalDate fecha) {
    long dias = DAYS.between(fecha, LocalDate.now());
    return dias <= 10;
  }

  public List<MascotaPerdidaConChapita> getMascotasPerdidas() {
    return mascotasPerdidas;
  }*/
}