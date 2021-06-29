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
  String link; // Se genera con interfaz web, de momento lo dejo aca para hacer la lógica del envio de mail

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

  boolean seAdaptaA(PublicacionAdoptante unAdoptante){
   // return unAdoptante.getPreferencias().containsAll(mascota.getCaracteristicas()) && unAdoptante.getComodidades().containsAll(mascota.getNecesidades());
    //Rompe por la diferencia de tipos entre String y Hasmap
    return true;
  }

  public String getLink() {
    return link;
  }

  public Mascota getMascota() {
    return mascota;
  }

  public Contacto getContactoDuenio() {
    return contactoDuenio;
  }

}
