package domain.Roles;

import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Notificadores.Mail.MailSender;
import domain.services.HogaresDeTransitoDDS.ServicioHogaresDeTransitoDDS;
import domain.services.HogaresDeTransitoDDS.entities.Hogar;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class Rescatista extends PersonaNoRegistrada {

  public Rescatista(String nombre, String apellido, LocalDate fechaNacimiento, String tipoDocumento, int numeroDocumento, Contacto contacto, String direccion) {
    super(nombre, apellido, fechaNacimiento, tipoDocumento, numeroDocumento, contacto, direccion);
  }

  public List<Hogar> solicitarHogares() throws  IOException {
    return ServicioHogaresDeTransitoDDS.instance().listarHogares();
  }

  // Cuando el duenio encuentre en una publicación a su mascota, se llamará a este método
  // ("bindeado" a un onClick en la pblicación)
  public void notificarQueDuenioEncontroMascota(DuenioNoRegistrado duenioNoRegistrado, PublicacionMascotaPerdida
      publicacionMascotaPerdida) {
    // Elegimos que se le envíe la notificación por email
    MailSender mailSender = new MailSender();
    mailSender.notificarARescatista(duenioNoRegistrado, publicacionMascotaPerdida);
  }
}
