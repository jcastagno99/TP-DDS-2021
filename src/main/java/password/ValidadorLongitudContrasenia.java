package password;

import domain.Roles.Usuario;
import exception.ContraseniaInvalidaException;

public class ValidadorLongitudContrasenia implements ValidadorContrasenia {

  public void validar(String usuario, String contrasenia) {
    if (contrasenia.length() < 8) {
      throw new ContraseniaInvalidaException("La contrasenia es demasiado corta");
    }

  }
}
