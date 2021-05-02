package password;

import java.util.Arrays;
import java.util.List;

public class ValidarTodasLasMetricas {
  private final List<ValidadorContrasenia> validadores;

  public ValidarTodasLasMetricas() {
    this.validadores = Arrays.asList(new ValidadorContraseniaComun(), new ValidadorLongitudContrasenia());
  }

  public void esValido(String unaContrasenia) {
    validadores.forEach(validador -> validador.esValido(unaContrasenia));
  }

}
