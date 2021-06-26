package domain.Asociacion;

import domain.Mascotas.MascotaPerdida;
import domain.Roles.Contacto;

public class Publicacion {

  public MascotaPerdida mascota;
  public Contacto contactoRescatista;
  public Asociacion asociacion;
  public EstadoPublicacion estado;

  public Publicacion(MascotaPerdida mascota, Contacto contactoRescatista, Asociacion asociacion) {
    this.mascota = mascota;
    this.contactoRescatista = contactoRescatista;
    this.asociacion = asociacion;
    this.estado = EstadoPublicacion.PENDIENTE;
  }

  public MascotaPerdida getMascota() {
    return mascota;
  }

  public EstadoPublicacion getEstado() {
    return estado;
  }

  public void aprobar() {
    this.estado = EstadoPublicacion.APROBADA;
  }

  public void rechazar() {
    this.estado = EstadoPublicacion.RECHAZADA;
  }
}
