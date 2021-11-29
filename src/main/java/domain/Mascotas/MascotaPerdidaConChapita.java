package domain.Mascotas;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Roles.Rescatista;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class MascotaPerdidaConChapita {

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private Rescatista rescatista;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private MascotaRegistrada mascotaPerdidaExistente;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private DatosDeEncuentroDeMascota encuentro;

  private LocalDate fechaEncuentro;


  public MascotaPerdidaConChapita(){
  }

  @Id
  @GeneratedValue
  private long id;

  // MascotaPerdidaExistente es una mascota registrada en una asociación
  public MascotaPerdidaConChapita(Rescatista rescatista, MascotaRegistrada mascotaPerdidaExistente,
      DatosDeEncuentroDeMascota encuentro) {
    this.rescatista = rescatista;
    this.mascotaPerdidaExistente = mascotaPerdidaExistente;
    this.encuentro = encuentro;
    fechaEncuentro = LocalDate.now();
    /*Asociacion asociacion = RepositorioAsociaciones.instance()
        .obtenerAsociacionA_LaQuePerteneceMascota(mascotaPerdidaExistente);
    asociacion.agregarNuevaMascotaPerdidaConChapita(this);*/
  }

  public void informarA_Duenio() {
    this.mascotaPerdidaExistente.getDuenio().mascotaFueEncontrada(rescatista, encuentro);
  }

  public LocalDate getFechaDeEncuentro() {
    return this.fechaEncuentro;
  }
}