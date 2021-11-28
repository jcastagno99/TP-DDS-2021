package password;

import java.util.Arrays;
import java.util.List;

public class ValidadorDeMetricas{

  private final List<ValidadorContrasenia> validadores;

  public ValidadorDeMetricas() {
    this.validadores = Arrays.asList(new ValidadorContraseniaComun("La contrasenia "
        + "pertenece a las 10000 más usadas. Por favor, ingrese otra contraseña"), new ValidadorLongitudContrasenia("La "
        + "contraseña es demasiado corta. Por favor, ingrese una contraseña de más de 8 caracteres."), new ValidadorContraseniaNoContieneUsuario(
 "La contraseñaa contiene al usuario. Por favor, ingrese otra contraseña."));
  }


  public void validar(String usuario, String contrasenia) {
    validadores.forEach(validador -> validador.validar(usuario, contrasenia));
  }

}
