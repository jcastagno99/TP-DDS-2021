package password;
import java.util.Arrays;
import java.util.List;

public class ValidarTodasLasMetricas {
  private final List<ValidadorContrasenia> validadores;

  public ValidarTodasLasMetricas() {
    this.validadores = Arrays.asList(new ValidadorContraseniaComun("La contrasenia pertenece a las 10k mas usadas"), new ValidadorLongitudContrasenia("La contrasenia es demasiado corta"), new ValidadorNoContieneUsuario("La contrasenia contiene al usuario"));
  }

  public void validar(String usuario, String contrasenia) {
    validadores.forEach(validador -> validador.validar(usuario, contrasenia));
  }

}
