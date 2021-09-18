package domain.Asociacion;

import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Roles.Contacto;

public class PublicacionMascotaPerdida {

  public MascotaPerdidaSinChapita mascota;
  public Contacto contactoRescatista;
  public Asociacion asociacion;
  public EstadoPublicacion estado;

  public PublicacionMascotaPerdida(MascotaPerdidaSinChapita mascota, Contacto contactoRescatista, Asociacion asociacion) {
    this.mascota = mascota;
    this.contactoRescatista = contactoRescatista;
    this.asociacion = asociacion;
    this.estado = EstadoPublicacion.PENDIENTE;
  }

  public MascotaPerdidaSinChapita getMascota() {
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
