package domain.MascotasPerdidasManagement;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Asociacion.UbicacionDeDominio;
import domain.Mascotas.*;
import domain.Roles.Rescatista;

public class AdministradorDeMascotasPerdidas {

  private static final AdministradorDeMascotasPerdidas INSTANCE = new AdministradorDeMascotasPerdidas();
  public static AdministradorDeMascotasPerdidas instance() {
    return INSTANCE;
  }
  //es una mascota porque al leer el QR va a dirigirme a algo que ya existe en mi sistema

  private MascotaRegistrada buscarMascota() {
    return null;
    // TODO
  }

  public void informarMascotaPerdidaConChapita(Rescatista rescatista, DatosDeEncuentroDeMascota datos) {
    // TODO Ver tema de testeo. Si poner el MailSender como un atributo del duenio y hacer ID
    MascotaRegistrada mascota = this.buscarMascota();
    MascotaPerdidaConChapita mascotaPerdidaConChapita = new MascotaPerdidaConChapita(rescatista, mascota, datos);
    RepositorioMascotasPerdidas.instance().agregarMascotaPerdida(mascotaPerdidaConChapita);
  }

  public void informarMascotaPerdidaSinChapita(Rescatista rescatista, DatosDeEncuentroDeMascota datosEncuentro, TipoMascota tipoMascota, Tamanio tamanio) {
    MascotaPerdidaSinChapita mascotaSinChapita = new MascotaPerdidaSinChapita(rescatista, datosEncuentro, tamanio, tipoMascota);
    UbicacionDeDominio ubicacionEncuentro = datosEncuentro.getUbicacion();
    Asociacion asociacionCercana = RepositorioAsociaciones.instance().obtenerAsociacionMasCercaA(ubicacionEncuentro);
    asociacionCercana.crearPublicacion(mascotaSinChapita,rescatista);
  }

}
