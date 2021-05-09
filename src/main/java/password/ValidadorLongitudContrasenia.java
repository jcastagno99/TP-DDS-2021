package password;

import domain.Roles.Usuario;
import exception.ContraseniaInvalidaException;

public class ValidadorLongitudContrasenia extends ValidadorContrasenia {

  public ValidadorLongitudContrasenia(String mensaje) {
    super(mensaje);
  }

  boolean condicion(String usuario, String contrasenia) {
    return contrasenia.length() < 8;
  }
  
}
