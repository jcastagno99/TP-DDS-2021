package password;

import domain.Roles.Usuario;
import exception.ContraseniaInvalidaException;

public class ValidadorNoContieneUsuario extends ValidadorContrasenia {

  public ValidadorNoContieneUsuario(String mensaje) {
    super(mensaje);
  }

  boolean condicion(String usuario, String contrasenia) {
    return contrasenia.contains(usuario);
  }

}