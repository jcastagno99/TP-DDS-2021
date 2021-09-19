package domain.Mascotas;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Roles.Rescatista;

import java.time.LocalDate;
import java.util.List;

public class MascotaPerdidaConChapita {
  private Rescatista rescatista;
  private Mascota mascotaPerdidaExistente;
  private DatosDeEncuentroDeMascota encuentro;
  private List<String> fotos;
  private LocalDate fechaEncuentro;


  // MascotaPerdidaExistente es una mascota registrada en una asociación
  public MascotaPerdidaConChapita(Rescatista rescatista, Mascota mascotaPerdidaExistente, DatosDeEncuentroDeMascota encuentro) {
    this.rescatista = rescatista;
    this.mascotaPerdidaExistente = mascotaPerdidaExistente;
    this.encuentro = encuentro;
    fechaEncuentro = LocalDate.now();
    Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionALaQuePerteneceMascota(mascotaPerdidaExistente);
    asociacion.agregarNuevaMascotaPerdidaConChapita(this);
  }

  public void informarADuenio() {
    this.mascotaPerdidaExistente.getMiDuenio().mascotaFueEncontrada(rescatista, encuentro);
  }

  public LocalDate getFechaDeEncuentro() {
    return this.fechaEncuentro;
  }

}