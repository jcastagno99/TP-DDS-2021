package domain.Mascotas;

import domain.Asociacion.UbicacionDeDominio;
import java.util.ArrayList;
import java.util.List;

public class DatosDeEncuentroDeMascota {

  private String descripcionEstadoEncuentro;
  private UbicacionDeDominio ubicacion;
  private List<String> fotos;

  public DatosDeEncuentroDeMascota(String descripcionEstadoEncuentro, UbicacionDeDominio ubicacion,
      String foto, String ... fotos) {
    this.descripcionEstadoEncuentro = descripcionEstadoEncuentro;
    this.ubicacion = ubicacion;
    this.fotos = new ArrayList<>();
    // TODO valdiacion de no nulo
    this.fotos.add(foto);

  }

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


