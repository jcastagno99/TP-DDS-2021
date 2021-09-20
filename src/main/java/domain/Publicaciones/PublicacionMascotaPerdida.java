package domain.Publicaciones;

import domain.Asociacion.Asociacion;
import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Publicaciones.EstadoPublicacion;
import domain.Roles.Rescatista;

public class PublicacionMascotaPerdida { // Deberia ser publicacionMascotaPerdidaSinChapita

  private MascotaPerdidaSinChapita mascota;
  private Rescatista rescatista;
  private Asociacion asociacion;
  private EstadoPublicacion estado;
  String link;

  public PublicacionMascotaPerdida(MascotaPerdidaSinChapita mascota, Rescatista rescatista, Asociacion asociacion) {
    this.mascota = mascota;
    this.rescatista = rescatista;
    this.asociacion = asociacion;
    this.estado = EstadoPublicacion.PENDIENTE;
    this.link = "unLinkGenerado...";
  }

  public MascotaPerdidaSinChapita getMascota() {
    return mascota;
  }

  public Rescatista getRescatista() {
    return this.rescatista;
  }

  public String getLink() {
    return link;
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
