package domain.Notificadores.Mail;

//Esta clase implementaria la interfaz javamail

import domain.Mascotas.DatosDeEncuentroDeMascota;
import domain.Notificadores.MedioDeNotificacion;
import domain.Roles.Duenio;
import domain.Roles.Rescatista;

public class MailSender implements MedioDeNotificacion {


  public void notificar (Rescatista rescatista, DatosDeEncuentroDeMascota datos, Duenio duenio) {
    String direccionEmail = duenio.getContacto().getEmail();
    String cuerpo = "¡Hola! Este mail es para informate que tu mascota fue encontrada. El rescatista que " +
        "la encontró nos dio la siguiente información acerca de su estado al momento de encontrarla: " +
        datos.getDescripcionEstadoEncuentro() + ". Su contacto es: " + rescatista.getContacto() + ". Nos aportó las siguientes fotos"
        + "datos.getFotos()" + ". La ubicación en la que se encontró es: " + datos.getUbicacion();
    Mail mail = new Mail("Encuentro de mascota", cuerpo, "sistema@gmail.com");
    this.enviarMail(mail, direccionEmail);
  }

  public void enviarMail(Mail mail, String direccionMail){
    //TODO
    /* Esto dependerá del servicio que se use
    * */
  }
}



