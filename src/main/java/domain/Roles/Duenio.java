package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Mascotas.Mascota;

import java.time.LocalDate;

public class Duenio extends Usuario {
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String documento;
    private int numeroDocumento;
    private Contacto contacto;

  public Duenio(String usuario, String contrasenia, Asociacion asociacion, String nombre, String apellido, LocalDate fechaNacimiento, String documento, int numeroDocumento, Contacto contacto) {
    super(usuario, contrasenia, asociacion);
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.documento = documento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;

    asociacion.agregarNuevoDuenio(this);
  }

  public void registrarMascota(Mascota mascota, Asociacion unaAsoc) {
    mascota.copiarCaracteristicas(unaAsoc);
    unaAsoc.agregarMascota(mascota);
    mascota.setDuenio(this);
  }

  public String getApellido() {
    return apellido;
  }
}
