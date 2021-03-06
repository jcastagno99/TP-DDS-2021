package domain.Mascotas;

import domain.Asociacion.UbicacionDeDominio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DatosDeEncuentroDeMascota {

  private String descripcionEstadoEncuentro;

  @Embedded
  private UbicacionDeDominio ubicacion;

  @ElementCollection
  private List<String> fotos;

  @Id
  @GeneratedValue
  private long id;

  public DatosDeEncuentroDeMascota(String descripcionEstadoEncuentro, UbicacionDeDominio ubicacion,
      String foto) {
    this.descripcionEstadoEncuentro = descripcionEstadoEncuentro;
    this.ubicacion = ubicacion;
    this.fotos = new ArrayList<>();
    this.fotos.add(foto);
  }

  public DatosDeEncuentroDeMascota(String descripcionEstadoEncuentro){
    this.descripcionEstadoEncuentro = descripcionEstadoEncuentro;
  }

  public DatosDeEncuentroDeMascota(){}

  public UbicacionDeDominio getUbicacion() {
    return this.ubicacion;
  }

  public List<String> getFotos() {
    return this.fotos;
  }

  public String getDescripcionEstadoEncuentro() {
    return this.descripcionEstadoEncuentro;
  }

}


