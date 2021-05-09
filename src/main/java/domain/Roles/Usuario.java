package domain.Roles;

import password.ValidarTodasLasMetricas;

public class Usuario {

  private String usuario;
  private String contrasenia;
  public ValidarTodasLasMetricas miValidador = new ValidarTodasLasMetricas();

  public Usuario(String usuario, String contrasenia) {
    miValidador.validar(usuario, contrasenia);
    this.contrasenia = contrasenia;
    this.usuario = usuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

}