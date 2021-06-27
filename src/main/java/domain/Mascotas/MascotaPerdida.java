package domain.Mascotas;

import domain.Roles.Rescatista;

import java.time.LocalDate;

public class MascotaPerdida {
  private String fotos;
  private String descripcion;
  private Ubicacion ubicacion;
  private LocalDate fechaEncuentro;
  private Rescatista rescatista;
  TipoMascota tipoMascota;
  Tamanio tamanio;

  public MascotaPerdida(String fotos, String descripcion, Ubicacion ubicacion, Rescatista rescatista, TipoMascota tipoMascota, Tamanio tamanio) {
    this.fotos = fotos;
    this.descripcion = descripcion;
    this.ubicacion = ubicacion;
    this.fechaEncuentro = LocalDate.now();
    this.rescatista = rescatista;
    this.tipoMascota = tipoMascota;
    this.tamanio = tamanio;
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

  public boolean esDeTamanio(Tamanio tamanio) {
    return this.tamanio.equals(tamanio);
  }

  public boolean esDeTipo(TipoMascota tipoMascota) {
    return this.tipoMascota.equals(tipoMascota);
  }
}
