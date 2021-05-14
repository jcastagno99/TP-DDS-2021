package domain.Roles;

import domain.Mascotas.MascotaPerdida;
import domain.Mascotas.Ubicacion;
import domain.Asociacion.RepositorioMascotasPerdidas;

import java.time.LocalDate;


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

  public void informarMascotaPerdida(String fotos, String descripcion, Ubicacion ubicacion) {
    MascotaPerdida mascota = new MascotaPerdida(fotos, descripcion, ubicacion, this);
    RepositorioMascotasPerdidas.instance().agregarMascotaPerdida(mascota);
  }
}
