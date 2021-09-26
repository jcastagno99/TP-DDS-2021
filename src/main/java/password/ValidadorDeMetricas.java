package password;

import java.util.Arrays;
import java.util.List;

public class ValidadorDeMetricas{

  private final List<ValidadorContrasenia> validadores;

  public ValidadorDeMetricas() {
    this.validadores = Arrays.asList(new ValidadorContraseniaComun("La contrasenia "
        + "pertenece a las 10k mas usadas"), new ValidadorLongitudContrasenia("La "
        + "contrasenia es demasiado corta"), new ValidadorContraseniaNoContieneUsuario(
 "La contrasenia contiene al usuario"));
  }


  public void validar(String usuario, String contrasenia) {
    validadores.forEach(validador -> validador.validar(usuario, contrasenia));
  }

}
