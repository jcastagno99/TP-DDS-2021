package domain.Publicaciones;

import domain.Asociacion.Asociacion;
import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Notificadores.Mail.MailSender;
import domain.Roles.DuenioNoRegistrado;
import domain.Roles.Rescatista;

import javax.persistence.*;

@Entity
public class PublicacionMascotaPerdida { // Deberia ser publicacionMascotaPerdidaSinChapita

  @OneToOne
  private MascotaPerdidaSinChapita mascota;
  @OneToOne
  private Rescatista rescatista;
  @ManyToOne
  private Asociacion asociacion;
  private EstadoPublicacion estado;
  String link;

  @Id
  @GeneratedValue
  private long id;

  public PublicacionMascotaPerdida(MascotaPerdidaSinChapita mascota, Rescatista rescatista,
       Asociacion asociacion) {
    this.mascota = mascota;
    this.rescatista = rescatista;
    this.asociacion = asociacion;
    this.estado = EstadoPublicacion.PENDIENTE;
    this.link = "unLinkGenerado...";
  }

  public PublicacionMascotaPerdida(){}

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

  // Cuando el duenio encuentre en una publicación a su mascota, se llamará a este método
  // ("bindeado" a un onClick en la pblicación)

  public void notificarQueDuenioEncontroMascota(DuenioNoRegistrado duenioNoRegistrado) {
    // Elegimos que se le envíe la notificación por email
    MailSender mailSender = new MailSender();
    mailSender.notificarARescatista(duenioNoRegistrado, this);
  }

}
