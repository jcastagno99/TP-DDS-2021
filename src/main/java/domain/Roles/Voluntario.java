package domain.Roles;

import domain.Asociacion.Asociacion;

public class Voluntario extends Usuario {

  public Voluntario(String usuario, String contrasenia, Asociacion asociacion) {
    super(usuario, contrasenia, asociacion);
  }
}
