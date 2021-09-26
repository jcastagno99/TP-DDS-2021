package password;

public class ValidadorLongitudContrasenia extends ValidadorContrasenia {

  public ValidadorLongitudContrasenia(String mensaje) {
    super(mensaje);
  }

  boolean condicionInvalidez(String usuario, String contrasenia) {
    return contrasenia.length() < 8;
  }
  
}
