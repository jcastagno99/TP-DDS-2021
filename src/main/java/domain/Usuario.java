package domain;

import password.ValidadorContraseniaComun;
import password.ValidarTodasLasMetricas;

public class Usuario {

  String nombre;
  String contrasenia;
  public ValidarTodasLasMetricas miValidador = new ValidarTodasLasMetricas();


  public Usuario(String nombre, String contrasenia) {
    miValidador.esValido(contrasenia);
    this.contrasenia = contrasenia;
    this.nombre = nombre;
  }

  public String getContrasenia() {
    return contrasenia;
  }
}