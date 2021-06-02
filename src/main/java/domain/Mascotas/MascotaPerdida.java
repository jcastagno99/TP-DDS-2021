package domain.Mascotas;

import domain.Roles.Rescatista;

import java.time.LocalDate;

public class MascotaPerdida {
  private String fotos;
  private String descripcion;
  private Ubicacion ubicacion;
  private LocalDate fechaEncuentro;
  private Rescatista rescatista;

  public MascotaPerdida(String fotos, String descripcion, Ubicacion ubicacion, Rescatista rescatista) {
    this.fotos = fotos;
    this.descripcion = descripcion;
    this.ubicacion = ubicacion;
    this.fechaEncuentro = LocalDate.now();
    this.rescatista = rescatista;
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Rescatista getRescatista() {
    return rescatista;
  }
}
