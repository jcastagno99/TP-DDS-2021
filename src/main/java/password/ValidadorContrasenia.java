package password;

import domain.Roles.Usuario;

public interface ValidadorContrasenia {

  void validar(Usuario unUsuario);
}
