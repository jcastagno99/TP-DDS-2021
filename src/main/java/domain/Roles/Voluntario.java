package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Asociacion.Publicacion;

public class Voluntario extends Usuario {

  Asociacion asociacion;

  public Voluntario(String usuario, String contrasenia, Asociacion asociacion) {
    super(usuario, contrasenia);
    this.asociacion = asociacion;
  }

  public void aprobarPublicacion(Publicacion publicacion) {
    publicacion.aprobar();
  }

  public void rechazarPublicacion(Publicacion publicacion) {
    publicacion.rechazar();
  }
}