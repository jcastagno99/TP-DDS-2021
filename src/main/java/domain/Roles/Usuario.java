package domain.Roles;

import password.ValidadorDeMetricas;

public class Usuario {

  private String usuario;
  private String contrasenia;
  public ValidadorDeMetricas miValidador = new ValidadorDeMetricas();

  public Usuario(String usuario, String contrasenia) {
    miValidador.validar(usuario, contrasenia);
    this.contrasenia = contrasenia;
    this.usuario = usuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public String getUsuario() {
    return this.usuario;
  }

}