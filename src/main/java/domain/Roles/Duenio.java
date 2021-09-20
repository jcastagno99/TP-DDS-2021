package domain.Roles;

import domain.Asociacion.*;
import domain.Notificadores.Mail.Mail;
import domain.Mascotas.DatosDeEncuentroDeMascota;
import domain.Mascotas.MascotaRegistrada;
import domain.Notificadores.MedioDeNotificacion;
import domain.Publicaciones.PublicacionMascotaPerdida;

import java.time.LocalDate;
import java.util.List;

public class Duenio extends Usuario {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String tipoDocumento;
    private int numeroDocumento;
    private Contacto contacto;
    private List<MedioDeNotificacion> mediosNotificacion; // observer

  public Duenio(String usuario, String contrasenia, Asociacion asociacion, String nombre, String apellido, LocalDate fechaNacimiento, String tipoDocumento, int numeroDocumento, Contacto contacto) {
    super(usuario, contrasenia);
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;

    asociacion.agregarNuevoDuenio(this);
  }

  public void registrarMascota(MascotaRegistrada mascota, Asociacion unaAsoc) {
    mascota.copiarCaracteristicas(unaAsoc);
    unaAsoc.agregarMascota(mascota);
    mascota.setDuenio(this);
  }

  public String getApellido() {
    return apellido;
  }

  public List<PublicacionMascotaPerdida> verPublicaciones() {
    return RepositorioAsociaciones.instance().getPublicaciones();
  }

  public void contactarRescatista(PublicacionMascotaPerdida unaPublicacion) {
    Rescatista rescatista = unaPublicacion.getMascota().getRescatista();

    String telefono = String.valueOf(contacto.getTelefono());
    Mail unMail = new Mail("Una de las mascotas que publico es mia", "Comuniquese con el siguiente numero: " + telefono, contacto.getEmail());
    //MailSender.instance().sendMail(unMail, rescatista.getContacto().getEmail());
    // TODO
  }


  //La asociacion llega cuando el usuario la selecciona por UI
  void darEnAdopcion(MascotaRegistrada unaMascota) {
    Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionALaQuePertenece(this);
    PublicacionAdopcion publicacion = new PublicacionAdopcion(unaMascota,contacto);
    asociacion.agregarPublicacionAdopcion(publicacion);
  }

  //Similar al metodo de arriba, si el Usuario puede elegir la asociacion esta deberia llegar por parametro, tal vez por UI
  void quieroAdoptar(List<Caracteristica> preferencias, List<String> comodidades, Asociacion asociacion) {
    PublicacionAdoptante publicacion = new PublicacionAdoptante(preferencias, comodidades);
    Mail unMail = new Mail("Su publicación fue creada, le enviamos el link para eliminarla","https://pelispedia.com","noreplay@Asociacion");
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

