package password;


import domain.Roles.Usuario;
import exception.ContraseniaInvalidaException;
import exception.ReadfileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class ValidadorContraseniaComun implements ValidadorContrasenia {

  public ValidadorContraseniaComun() {
  }

  public void validar(Usuario unUsuario) {
    Path path = Paths.get("src/main/resources/10k-most-common.txt");
    Stream<String> stream;
    try {
      stream = Files.lines(path);
      if (stream.anyMatch(palabraComun -> palabraComun.equals(unUsuario.getContrasenia()))) {
        throw new ContraseniaInvalidaException("La contrasenia pertenece a las 10k mas usadas");
      }
    } catch (IOException e) {
      throw new ReadfileException();
    }
  }


}
