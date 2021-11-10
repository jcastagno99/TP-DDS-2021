package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Asociacion.Caracteristica;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Mascotas.DatosDeEncuentroDeMascota;
import domain.Mascotas.MascotaRegistrada;
import domain.Notificadores.Mail.Mail;
import domain.Notificadores.MedioDeNotificacion;
import domain.Publicaciones.PublicacionAdopcion;
import domain.Publicaciones.PublicacionAdoptante;
import domain.Publicaciones.PublicacionMascotaPerdida;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Duenio extends Usuario {

  private LocalDate fechaNacimiento;
  private String tipoDocumento;
  private int numeroDocumento;

  @OneToOne(cascade = {CascadeType.ALL})
  private Contacto contacto;

  @Transient
  private List<MedioDeNotificacion> mediosNotificacion;

  public Duenio(String usuario, String contrasenia, Asociacion asociacion, String nombre,
       String apellido, LocalDate fechaNacimiento, String tipoDocumento, int numeroDocumento,
       Contacto contacto) {
    super(usuario, contrasenia, nombre, apellido);
    this.fechaNacimiento = fechaNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;
    this.mediosNotificacion = new ArrayList<>();

    asociacion.agregarNuevoDuenio(this);
  }

  public Duenio(){
    super();
  }

  public void agregarMedioDeNotificacion(MedioDeNotificacion medioDeNotificacion) {
    this.mediosNotificacion.add(medioDeNotificacion);
  }

  public void quitarMedioNotificacion(MedioDeNotificacion medioDeNotificacion) {
    this.mediosNotificacion.remove(medioDeNotificacion);
  }

  public void registrarMascota(MascotaRegistrada mascota, Asociacion unaAsoc) {
    mascota.copiarCaracteristicas(unaAsoc);
    unaAsoc.agregarMascota(mascota);
    mascota.setDuenio(this);
  }

  public List<PublicacionMascotaPerdida> verPublicaciones() {
    return RepositorioAsociaciones.instance().getPublicaciones();
  }

  //La asociacion llega cuando el usuario la selecciona por UI
  void darEnAdopcion(MascotaRegistrada unaMascota) {
    Asociacion asociacion = RepositorioAsociaciones.instance()
        .obtenerAsociacionA_LaQuePertenece(this);
    PublicacionAdopcion publicacion = new PublicacionAdopcion(unaMascota, contacto);
    asociacion.agregarPublicacionAdopcion(publicacion);
  }

  //Similar al metodo de arriba, si el Usuario puede elegir la asociacion esta deberia llegar
  // por parametro, tal vez por UI

  void quieroAdoptar(List<Caracteristica> preferencias, List<String> comodidades,
      Asociacion asociacion) {
    PublicacionAdoptante publicacion = new PublicacionAdoptante(preferencias, comodidades);
    Mail unMail = new Mail("Su publicación fue creada, le enviamos el link para eliminarla",
        "https://pelispedia.com","noreplay@Asociacion")
        ;
    //MailSender.instance().sendMail(unMail,contacto.getEmail());
    // TODO
    asociacion.agregarPublicacionAdoptante(publicacion);
  }

  public void mascotaFueEncontrada(Rescatista rescatista,  DatosDeEncuentroDeMascota datos) {
    this.mediosNotificacion.forEach(medio -> medio.notificarADuenio(rescatista, datos, this));
  }

  public Contacto getContacto() {
    return contacto;
  }

}

