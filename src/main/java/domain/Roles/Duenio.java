package domain.Roles;

import domain.Mascotas.Mascota;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

  public class Duenio extends Usuario {
    private List<Mascota> mascotas;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String documento;
    private int numeroDocumento;
    private Contacto contacto;

  public Duenio(String usuario, String contrasenia, String nombre, String apellido, LocalDate fechaNacimiento, String documento, int numeroDocumento, Contacto contacto) {
    super(usuario, contrasenia);
    this.mascotas = new ArrayList<>();
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.documento = documento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;
  }

  public void registrarMascota(Mascota mascota) {
    this.mascotas.add(mascota);
  }

  public List<Mascota> getMascotas() {
    return mascotas;
  }
}
