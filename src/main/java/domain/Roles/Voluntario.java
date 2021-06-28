package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Asociacion.PublicacionMascotaPerdida;

public class Voluntario extends Usuario {

  Asociacion asociacion;

  public Voluntario(String usuario, String contrasenia, Asociacion asociacion) {
    super(usuario, contrasenia);
    this.asociacion = asociacion;
  }

  public void aprobarPublicacion(PublicacionMascotaPerdida publicacion) {
    publicacion.aprobar();
  }

  public void rechazarPublicacion(PublicacionMascotaPerdida publicacion) {
    publicacion.rechazar();
  }
}