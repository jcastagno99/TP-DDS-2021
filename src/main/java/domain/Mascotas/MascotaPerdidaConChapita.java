package domain.Mascotas;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Roles.Rescatista;

public class MascotaPerdidaConChapita {
  Rescatista rescatista;
  Mascota mascotaPerdidaExistente;
  DatosDeEncuentroDeMascota encuentro;

  // MascotaPerdidaExistente es una mascota registrada en una asociación
  public MascotaPerdidaConChapita(Rescatista rescatista, Mascota mascotaPerdidaExistente, DatosDeEncuentroDeMascota encuentro) {
    this.rescatista = rescatista;
    this.mascotaPerdidaExistente = mascotaPerdidaExistente;
    this.encuentro = encuentro;
    Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionALaQuePerteneceMascota(mascotaPerdidaExistente);
    asociacion.agregarNuevaMascotaPerdidaConChapita(this);
  }

  public void informarADuenio(){
    this.mascotaPerdidaExistente.getMiDuenio().mascotaFueEncontrada(rescatista, encuentro);
  }

}