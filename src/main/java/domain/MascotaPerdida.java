package domain;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

public class MascotaPerdida {
  private List<Blob> fotos;
  private String descripcion;
  private Ubicacion ubicacion;
  private LocalDate fechaEncuentro;

  public MascotaPerdida(List<Blob> fotos, String descripcion, Ubicacion ubicacion) {
    this.fotos = fotos;
    this.descripcion = descripcion;
    this.ubicacion = ubicacion;
    this.fechaEncuentro = LocalDate.now();
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }
}
