package domain.Roles;

import domain.Mascotas.MascotaPerdida;
import domain.Refugio.Refugio;
import domain.Mascotas.Ubicacion;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

public class Rescatista {
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;
  private String documento;
  private int numeroDocumento;
  private Contacto contacto;
  private String direccion;

  public Rescatista(String nombre, String apellido, LocalDate fechaNacimiento, String documento, int numeroDocumento, Contacto contacto, String direccion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.documento = documento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;
    this.direccion = direccion;
  }

  void informarMascotaPerdida(List<Blob> fotos, String descripcion, Ubicacion ubicacion) {
    MascotaPerdida mascota = new MascotaPerdida(fotos, descripcion, ubicacion);
    Refugio.instance().agregarMascotaPerdida(mascota);
  }
}
