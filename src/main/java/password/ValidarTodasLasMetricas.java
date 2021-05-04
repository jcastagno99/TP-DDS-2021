package password;

import domain.Roles.Usuario;

import java.util.Arrays;
import java.util.List;

public class ValidarTodasLasMetricas {
  private final List<ValidadorContrasenia> validadores;

  public ValidarTodasLasMetricas() {
    this.validadores = Arrays.asList(new ValidadorContraseniaComun(), new ValidadorLongitudContrasenia(), new ValidadorNoContieneUsuario());
  }

  public void validar(String usuario, String contrasenia) {
    validadores.forEach(validador -> validador.validar(usuario,contrasenia));
  }

}
