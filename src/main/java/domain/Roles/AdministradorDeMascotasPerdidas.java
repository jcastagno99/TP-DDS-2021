package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Asociacion.RepositorioMascotasPerdidas;
import domain.Mascotas.*;
import domain.Notificadores.Mail.Mail;

public class AdministradorDeMascotasPerdidas {

  private static final AdministradorDeMascotasPerdidas INSTANCE = new AdministradorDeMascotasPerdidas();
  public static AdministradorDeMascotasPerdidas instance() {
    return INSTANCE;
  }
  //es una mascota porque al leer el QR va a dirigirme a algo que ya existe en mi sistema

  public void informarMascotaPerdidaConChapita(Rescatista rescatista) {
    // TODO Ver tema de testeo. Si poner el MailSender como un atributo del duenio y hacer ID
    //MailSender.instance().sendMail(unMail, mascota.getMiDuenio().getContacto().getEmail());
  }

  public void informarMascotaPerdidaSinChapita(String fotos, String descripcion, UbicacionDeDominio ubicacion, TipoMascota tipoMascota, Tamanio tamanio, Rescatista rescatista) {
    MascotaPerdidaSinChapita mascota = new MascotaPerdidaSinChapita(fotos, descripcion, ubicacion, rescatista, tipoMascota, tamanio);
    RepositorioMascotasPerdidas.instance().agregarMascotaPerdida(mascota);
    Asociacion asociacionCercana = RepositorioAsociaciones.instance().obtenerAsociacionMasCercaA(ubicacion);
    asociacionCercana.crearPublicacion(mascota,contacto);
  }

}
