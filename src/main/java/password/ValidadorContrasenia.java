package password;

import exception.ContraseniaInvalidaException;

public abstract class ValidadorContrasenia {
  String mensaje;

  public ValidadorContrasenia(String mensaje) {
    this.mensaje = mensaje;
  }

  void validar(String usuario, String contrasenia) {
    if(this.condicion(usuario, contrasenia)) {
      throw new ContraseniaInvalidaException(mensaje);
    }
  }

  abstract boolean condicion(String usuario, String contrasenia);

}
