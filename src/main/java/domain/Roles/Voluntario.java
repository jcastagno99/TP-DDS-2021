package domain.Roles;

import domain.Asociacion.Asociacion;

public class Voluntario extends Usuario {

  Asociacion asociacion;

  public Voluntario(String usuario, String contrasenia, Asociacion asociacion) {
    super(usuario, contrasenia, asociacion);
    this.asociacion = asociacion;

  }
}
