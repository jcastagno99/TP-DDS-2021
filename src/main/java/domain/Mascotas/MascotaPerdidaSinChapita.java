package domain.Mascotas;

import domain.Roles.Rescatista;

import java.time.LocalDate;

public class MascotaPerdidaSinChapita {
  private DatosDeEncuentroDeMascota datosDeEncuentroDeMascota;
  private LocalDate fechaEncuentro;
  private Rescatista rescatista;
  private Tamanio tamanio;
  private TipoMascota tipoMascota;


  public MascotaPerdidaSinChapita(Rescatista rescatista, DatosDeEncuentroDeMascota datos,
                                  Tamanio tamanio, TipoMascota tipo) {
    this.datosDeEncuentroDeMascota = datos;
    this.fechaEncuentro = LocalDate.now();
    this.rescatista = rescatista;
    this.tamanio = tamanio;
    this.tipoMascota = tipo;
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }

  public String getDescripcion() {
    return this.datosDeEncuentroDeMascota.getDescripcionEstadoEncuentro();
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
