package domain;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

public class Rescatista {
  String nombreCompleto;
  LocalDate fechaNacimiento;
  String documento;
  int numeroDocumento;
  Contacto contacto;

  void informarMascotaPerdida(List<Blob> fotos, String descripcion, Ubicacion ubicacion) {
    MascotaPerdida mascota = new MascotaPerdida(fotos, descripcion, ubicacion);
    Refugio.instance().agregarMascotaPerdida(mascota);
  }
}
