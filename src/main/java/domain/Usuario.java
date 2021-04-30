package domain;

public class Usuario {

  String nombreAdm;
  String contrasenia;
  ValidadorContrasenia miValidador;


  public Usuario(String nombreAdm, String contrasenia) {
    if (miValidador.contraseniaNoTanDebil(contrasenia)) {
      this.contrasenia = contrasenia;
    }
    this.nombreAdm = nombreAdm;
  }
}