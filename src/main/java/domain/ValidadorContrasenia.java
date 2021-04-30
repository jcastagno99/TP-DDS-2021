package domain;


import exception.ReadfileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class ValidadorContrasenia {

  public boolean contraseniaNoTanDebil(String contrasenia) {
    Path path = Paths.get("src/main/resources/10k-most-common.txt");
    Stream<String> stream;
    try {
      stream = Files.lines(path);
       return stream.noneMatch(palabraComun -> palabraComun.equals(contrasenia));
    } catch (IOException e) {
       throw new ReadfileException();

    }
  }

}
