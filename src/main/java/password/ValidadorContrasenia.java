package password;

import exception.ContraseniaInvalidaException;

public abstract class ValidadorContrasenia {
  private String mensaje;

  public ValidadorContrasenia(String mensaje) {
    this.mensaje = mensaje;
  }

  void validar(String usuario, String contrasenia) {
    if (this.condicionInvalidez(usuario, contrasenia)) {
      throw new ContraseniaInvalidaException(this.mensaje);
    }
  }

  abstract boolean condicionInvalidez(String usuario, String contrasenia);

}
