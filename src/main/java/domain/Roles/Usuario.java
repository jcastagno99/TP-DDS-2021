package domain.Roles;

import password.ValidadorDeMetricas;

public class Usuario {

  private String usuario;
  private String contrasenia;
  private String nombre;
  private String apellido;
  public ValidadorDeMetricas miValidador = new ValidadorDeMetricas();

  public Usuario(String usuario, String contrasenia, String nombre, String apellido) {
    miValidador.validar(usuario, contrasenia);
    this.contrasenia = contrasenia;
    this.usuario = usuario;
    this.nombre = nombre;
    this.apellido = apellido;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public String getApellido() {
    return apellido;
  }

  public String getUsuario() {
    return this.usuario;
  }

}