package domain.Notificadores.Mail;

//Esta clase tendrá una relación (de asociación, por ejemplo) con la API

/* Decidimos que queden message chains como por ejemplo:
* rescatista.getDatosFormulario().getContacto()
* rescatista.getDatosFormulario().obtenerMail()
* para evitar repetición de lógica en las clases Rescatista y DuenioNoRegistrado con los getters
* Por otra parte, este code smell sólo se produce dentro de esta clase
* */

import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Mascotas.DatosDeEncuentroDeMascota;
import domain.Notificadores.MedioDeNotificacion;
import domain.Roles.Duenio;
import domain.Roles.DuenioNoRegistrado;
import domain.Roles.Rescatista;

public class MailSender implements MedioDeNotificacion {

  public void notificarADuenio(Rescatista rescatista, DatosDeEncuentroDeMascota datos, Duenio
      duenio) {
    String direccionEmail = duenio.getContacto().getEmail();
    String cuerpo = "¡Hola! Este mail es para informate que tu mascota fue "
        + "encontrada. El rescatista que la encontró nos dio la siguiente información "
        + "acerca de su estado al momento de encontrarla: " + datos.getDescripcionEstadoEncuentro()
        + ". Su contacto es: " + rescatista.getDatosFormulario().getContacto() + ". Nos aportó las siguientes fotos"
        + "datos.getFotos()" + ". La ubicación en la que se encontró es: " + datos.getUbicacion();
    this.crearYEnviarMail("Encuentro de mascota", cuerpo, direccionEmail);
  }

  public void notificarARescatista(DuenioNoRegistrado duenioNoRegistrado, PublicacionMascotaPerdida
      publicacionMascotaPerdida) {
    Rescatista rescatista = publicacionMascotaPerdida.getRescatista();
    String direccionEmail = rescatista.getDatosFormulario().obtenerMail();
    String linkPublicacion = publicacionMascotaPerdida.getLink();
    String cuerpo = "¡Hola! Este mail es para informarte que la mascota que reportaste como "
        + "encontrada está siendo buscada por su dueño. Podés encontrar la publicación "
        + "correspondiente en: " + linkPublicacion + ". Te dejamos a continuación sus datos "
        + "en contacto para que te comuniques con él y puedan acordar un punto de encuentro: "
        + "Nombre del dueño: " + duenioNoRegistrado.getDatos().getNombre() + duenioNoRegistrado.getDatos().getApellido()
        + "Teléfono: " + duenioNoRegistrado.getDatos().obtenerTelefono()
        + "Email: " + duenioNoRegistrado.getDatos().obtenerMail();
    this.crearYEnviarMail("Encuentro de mascota a través de publicación", cuerpo, direccionEmail);
  }

  private void crearYEnviarMail(String asunto, String cuerpo, String direccionEmailDestino) {
    Mail mail = new Mail(asunto, cuerpo, "sistema@gmail.com");
    this.enviarMail(mail, direccionEmailDestino);
  }

  public void enviarMail(Mail mail, String direccionMail){
    // TODO
    // Esto dependerá del servicio que se use
  }
}



