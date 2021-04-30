package domain;

import java.util.ArrayList;
import java.util.List;
import java.sql.Blob;

public class Mascota {
  TipoMascota tipoMascota;
  String nombreMascota;
  String apodoMascota;
  int edadAproximada;
  Sexo sexo;
  String descripcionFisica;
  List<Blob> fotos;
  List<String> caracteristicas;

  public Mascota(TipoMascota tipoMascota, String nombreMascota, String apodoMascota, int edadAproximada, Sexo sexo, String descripcionFisica) {
    this.tipoMascota = tipoMascota;
    this.nombreMascota = nombreMascota;
    this.apodoMascota = apodoMascota;
    this.edadAproximada = edadAproximada;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = new ArrayList<>();
    this.caracteristicas = new ArrayList<>();
  }


}
