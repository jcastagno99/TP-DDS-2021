package domain.Notificadores.SMS;

import domain.Mascotas.DatosDeEncuentroDeMascota;
import domain.Notificadores.MedioDeNotificacion;
import domain.Roles.Duenio;
import domain.Roles.Rescatista;

public class SMSSender implements MedioDeNotificacion{
  public void notificar (Rescatista rescatista, DatosDeEncuentroDeMascota datos, Duenio duenio){
    Integer telefono = duenio.getContacto().getTelefono().intValue();
    String cuerpo = "¡Hola! Este mail es para informate que tu mascota fue encontrada. El rescatista que " +
        "la encontró nos dio la siguiente información acerca de su estado al momento de encontrarla: " +
        datos.getDescripcionEstadoEncuentro() + ". Su contacto es: " + rescatista.getContacto() + ". Nos aportó las siguientes fotos"
        + "datos.getFotos()" + datos.getUbicacion() + "el contacto de quien la encontro es: " ;
    this.enviarWpp(cuerpo, 1122334455);
  }

  public void enviarWpp(String text, int telefono){
    //TODO
  }
}
