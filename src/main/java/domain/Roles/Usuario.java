package domain.Roles;

import password.ValidadorContraseniaComun;
import password.ValidarTodasLasMetricas;

public class Usuario {

  String usuario;
  String contrasenia;
  public ValidarTodasLasMetricas miValidador = new ValidarTodasLasMetricas();

  public Usuario(String usuario, String contrasenia) {
    miValidador.validar(contrasenia);
    this.contrasenia = contrasenia;
    this.usuario = usuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }
}