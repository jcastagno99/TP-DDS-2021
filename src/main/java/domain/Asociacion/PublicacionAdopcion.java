package domain.Asociacion;

import domain.Mail.Mail;
import domain.Mail.MailSender;
import domain.Mascotas.Mascota;
import domain.Roles.Contacto;
import domain.Roles.Duenio;

import java.util.HashMap;

public class PublicacionAdopcion {

  Mascota mascota;
  Contacto contactoDuenio;
  private HashMap<String, String> preguntas = RepositorioAsociaciones.instance().getPreguntasObligatorias();

  public PublicacionAdopcion(Mascota mascota, Contacto contactoDuenio) {
    this.mascota = mascota;
    this.contactoDuenio = contactoDuenio;
  }

  void agregarPreguntas(HashMap<String, String> preguntasNuevas){
    preguntas.putAll(preguntasNuevas);
  }

  void responderPregunta(String pregunta, String respuesta){
    preguntas.put(pregunta,respuesta);
  }

  void solicitudAdopcion(Duenio interesado){
    String telefono = interesado.getContacto().getTelefono().toString();
    String email = interesado.getContacto().getEmail();
    Mail unMail = new Mail("Quiero adoptar a su mascota " + mascota.getNombre(), "Comuniquese con el siguiente numero: " + telefono, email);
    MailSender.instance().sendMail(unMail,contactoDuenio.getEmail());
  }

  public Mascota getMascota() {
    return mascota;
  }

  public Contacto getContactoDuenio() {
    return contactoDuenio;
  }

}
