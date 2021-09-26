package domain.Mascotas;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Roles.Rescatista;
import java.time.LocalDate;

public class MascotaPerdidaConChapita {
  private Rescatista rescatista;
  private MascotaRegistrada mascotaPerdidaExistente;
  private DatosDeEncuentroDeMascota encuentro;
  private LocalDate fechaEncuentro;


  // MascotaPerdidaExistente es una mascota registrada en una asociación
  public MascotaPerdidaConChapita(Rescatista rescatista, MascotaRegistrada mascotaPerdidaExistente,
      DatosDeEncuentroDeMascota encuentro) {
    this.rescatista = rescatista;
    this.mascotaPerdidaExistente = mascotaPerdidaExistente;
    this.encuentro = encuentro;
    fechaEncuentro = LocalDate.now();
    Asociacion asociacion = RepositorioAsociaciones.instance()
        .obtenerAsociacionA_LaQuePerteneceMascota(mascotaPerdidaExistente);
    asociacion.agregarNuevaMascotaPerdidaConChapita(this);
  }

  public void informarA_Duenio() {
    this.mascotaPerdidaExistente.getMiDuenio().mascotaFueEncontrada(rescatista, encuentro);
  }

  public LocalDate getFechaDeEncuentro() {
    return this.fechaEncuentro;
  }
}