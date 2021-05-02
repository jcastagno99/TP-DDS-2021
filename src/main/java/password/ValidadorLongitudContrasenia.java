package password;

import exception.ContraseniaInvalidaException;

public class ValidadorLongitudContrasenia implements ValidadorContrasenia {

  @Override
  public void esValido(String contrasenia) {
    if (contrasenia.length() < 8) {
      throw new ContraseniaInvalidaException("La contrasenia es demasiado corta");
    }
  }
}
