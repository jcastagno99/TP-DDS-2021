package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Asociacion.PublicacionMascotaPerdida;
import domain.Mascotas.MascotaPerdidaConChapita;

public class Voluntario extends Usuario {

  Asociacion asociacion;

  public Voluntario(String usuario, String contrasenia, Asociacion asociacion) {
    super(usuario, contrasenia);
    this.asociacion = asociacion;
  }

  public void notificarAlDuenio(MascotaPerdidaConChapita mascotaPerdida){mascotaPerdida.informarADuenio();}

  public void aprobarPublicacion(PublicacionMascotaPerdida publicacion) {
    publicacion.aprobar();
  }

  public void rechazarPublicacion(PublicacionMascotaPerdida publicacion) {
    publicacion.rechazar();
  }
}