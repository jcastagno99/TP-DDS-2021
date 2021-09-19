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

  private Mascota buscarMascota() {
    return null;
    // TODO
  }

  public void informarMascotaPerdidaConChapita(Rescatista rescatista, DatosDeEncuentroDeMascota datos) {
    // TODO Ver tema de testeo. Si poner el MailSender como un atributo del duenio y hacer ID
    Mascota mascota = this.buscarMascota();
    MascotaPerdidaConChapita mascotaPerdidaConChapita = new MascotaPerdidaConChapita(rescatista, mascota, datos);
    RepositorioMascotasPerdidas.instance().agregarMascotaPerdida(mascotaPerdidaConChapita);
  }

  public void informarMascotaPerdidaSinChapita(Rescatista rescatista, DatosDeEncuentroDeMascota datosEncuentro, TipoMascota tipoMascota, Tamanio tamanio) {
    MascotaPerdidaSinChapita mascotaSinChapita = new MascotaPerdidaSinChapita(rescatista, datosEncuentro, tamanio, tipoMascota);
    UbicacionDeDominio ubicacionEncuentro = datosEncuentro.getUbicacion();
    Asociacion asociacionCercana = RepositorioAsociaciones.instance().obtenerAsociacionMasCercaA(ubicacionEncuentro);
    Contacto contactoRescatista = rescatista.getContacto();

    asociacionCercana.crearPublicacion(mascotaSinChapita,contactoRescatista);
  }

}
