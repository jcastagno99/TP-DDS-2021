package password;

import exception.ContraseniaInvalidaException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class ValidadorContrasenia {
  private String mensaje;

  @Id
  @GeneratedValue
  private long id;

  public ValidadorContrasenia(String mensaje) {
    this.mensaje = mensaje;
  }

  public ValidadorContrasenia() {}

  void validar(String usuario, String contrasenia) {
    if (this.condicionInvalidez(usuario, contrasenia)) {
      throw new ContraseniaInvalidaException(this.mensaje);
    }
  }

  abstract boolean condicionInvalidez(String usuario, String contrasenia);

}
