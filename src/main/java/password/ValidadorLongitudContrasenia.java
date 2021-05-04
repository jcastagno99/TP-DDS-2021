package password;

import domain.Roles.Usuario;
import exception.ContraseniaInvalidaException;

public class ValidadorLongitudContrasenia implements ValidadorContrasenia {

  public void validar(Usuario unUsuario) {
    if (unUsuario.longitudContraseñaEsMenorA(8)) {
      throw new ContraseniaInvalidaException("La contrasenia es demasiado corta");
    }

  }
}
