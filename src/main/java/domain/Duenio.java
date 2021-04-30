package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Duenio {
  List<Mascota> mascotas;
  String nombreCompleto;
  LocalDate fechaNacimiento;
  String documento;
  int numeroDocumento;
  Contacto contacto;

  public Duenio(String nombreCompleto, LocalDate fechaNacimiento, String documento, int numeroDocumento, Contacto contacto) {
    this.mascotas = new ArrayList<>();
    this.nombreCompleto = nombreCompleto;
    this.fechaNacimiento = fechaNacimiento;
    this.documento = documento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;
  }

  void registrarMascota(Mascota mascota) {
    this.mascotas.add(mascota);
  }
}
