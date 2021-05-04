package domain.Roles;

import password.ValidadorContraseniaComun;
import password.ValidarTodasLasMetricas;

public class Usuario {

  private String usuario;
  private String contrasenia;
  public ValidarTodasLasMetricas miValidador = new ValidarTodasLasMetricas();

  public Usuario(String usuario, String contrasenia) {
    miValidador.validar(this);
    this.contrasenia = contrasenia;
    this.usuario = usuario;
  }

  public String getUsuario() {
    return usuario;
  }
  public String getContrasenia() {
    return contrasenia;
  }

  public boolean contraseniaContieneUsuario() {
    return contrasenia.contains(usuario);
  }

  public boolean longitudContraseñaEsMenorA(int i) {
    return contrasenia.length() < i;
  }
}