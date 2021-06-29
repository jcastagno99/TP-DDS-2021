package domain.Roles;

import domain.Asociacion.*;
import domain.Mail.Mail;
import domain.Mail.MailSender;
import domain.Mascotas.Mascota;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Duenio extends Usuario {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String documento;
    private int numeroDocumento;
    private Contacto contacto;

  public Duenio(String usuario, String contrasenia, Asociacion asociacion, String nombre, String apellido, LocalDate fechaNacimiento, String documento, int numeroDocumento, Contacto contacto) {
    super(usuario, contrasenia);
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.documento = documento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;

    asociacion.agregarNuevoDuenio(this);
  }

  public void registrarMascota(Mascota mascota, Asociacion unaAsoc) {
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
    MailSender.instance().sendMail(unMail, rescatista.getContacto().getEmail());
  }

  //Asumo que el sistema solo me permite poner en adopcion mascotas de las que yo soy dueño
  //La asociacion llega cuando el usuario la selecciona por UI
  void darEnAdopcion(Mascota unaMascota, Asociacion asociacion){
    //Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionALaQuePertenece(this);
    PublicacionAdopcion publicacion = new PublicacionAdopcion(unaMascota,contacto);
    asociacion.agregarPublicacionAdopcion(publicacion);
  }

  void quieroAdoptar(List<String> preferencias, List<String> comodidades){
    Asociacion asociacion = RepositorioAsociaciones.instance().obtenerAsociacionALaQuePertenece(this);
    PublicacionAdoptante publicacion = new PublicacionAdoptante(preferencias,comodidades);
    Mail unMail = new Mail("Su publicación fue creada, le enviamos el link para eliminarla","https://pelispedia.com","noreplay@Asociacion"); //Debería agregar el atributo?
    MailSender.instance().sendMail(unMail,contacto.getEmail());
    asociacion.agregarPublicacionAdoptante(publicacion);
  }


  public Contacto getContacto() {
    return contacto;
  }
}

