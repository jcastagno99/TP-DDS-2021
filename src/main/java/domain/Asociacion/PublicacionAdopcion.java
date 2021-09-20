package domain.Asociacion;

import domain.Notificadores.Mail.Mail;
import domain.Mascotas.MascotaRegistrada;
import domain.Roles.Contacto;
import domain.Roles.Duenio;

import java.util.List;

public class PublicacionAdopcion {

  MascotaRegistrada mascota;
  Contacto contactoDuenio;
  private List<Pregunta> preguntas = RepositorioAsociaciones.instance().getPreguntasObligatorias();
  String link; // Se genera con interfaz web, de momento lo dejo aca para hacer la lógica del envio de mail

  public PublicacionAdopcion(MascotaRegistrada mascota, Contacto contactoDuenio) {
    this.mascota = mascota;
    this.contactoDuenio = contactoDuenio;
  }

  void agregarPreguntas(List<Pregunta> preguntasNuevas) {
    preguntas.addAll(preguntasNuevas);
  }

  void solicitudAdopcion(Duenio interesado) {
    String telefono = interesado.getContacto().getTelefono().toString();
    String email = interesado.getContacto().getEmail();
    Mail unMail = new Mail("Quiero adoptar a su mascota " + mascota.getNombre(), "Comuniquese con el siguiente numero: " + telefono, email);
    //MailSender.instance().sendMail(unMail, contactoDuenio.getEmail());
  } // tal vez un buildear/factory

  boolean seAdaptaA(PublicacionAdoptante unAdoptante) {
    return mascota.getCaracteristicas().containsAll(unAdoptante.getPreferencias()) &&
        unAdoptante.getComodidades().containsAll(mascota.getNecesidades());
  }

  public String getLink() {
    return link;
  }

  public MascotaRegistrada getMascota() {
    return mascota;
  }

  public Contacto getContactoDuenio() {
    return contactoDuenio;
  }

}
