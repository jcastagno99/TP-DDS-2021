package domain.Mascotas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatosDeEncuentroDeMascota {

  private String descripcionEstadoEncuentro;
  private UbicacionDeDominio ubicacion;
  private List<String> fotos;

  public DatosDeEncuentroDeMascota(String descripcionEstadoEncuentro, UbicacionDeDominio ubicacion, String foto, String ... fotos) {
    this.descripcionEstadoEncuentro = descripcionEstadoEncuentro;
    this.ubicacion = ubicacion;
    this.fotos = new ArrayList<>();
   // TODO valdiacion de no nulo
    this.fotos.add(foto);
    Collections.addAll(this.fotos, fotos);
  }
}


