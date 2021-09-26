package domain.Mascotas;

import domain.Roles.Rescatista;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class MascotaPerdidaSinChapita {
  @OneToOne
  private DatosDeEncuentroDeMascota datosDeEncuentroDeMascota;
  private LocalDate fechaEncuentro;
  @OneToOne
  private Rescatista rescatista;
  private Tamanio tamanio;
  private TipoMascota tipoMascota;

  @Id
  @GeneratedValue
  private long id;

  public MascotaPerdidaSinChapita(Rescatista rescatista, DatosDeEncuentroDeMascota datos,
      Tamanio tamanio, TipoMascota tipo) {
    this.datosDeEncuentroDeMascota = datos;
    this.fechaEncuentro = LocalDate.now();
    this.rescatista = rescatista;
    this.tamanio = tamanio;
    this.tipoMascota = tipo;
  }

  public MascotaPerdidaSinChapita(){}

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
