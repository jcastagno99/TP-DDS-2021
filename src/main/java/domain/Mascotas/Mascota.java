package domain.Mascotas;

import domain.Asociacion.Asociacion;
import domain.Roles.Duenio;
import exception.CaracteristicaNoEncontradaException;
import java.util.HashMap;
import java.util.Map;

public class Mascota {
  private TipoMascota tipoMascota;
  private String nombre;
  private String apodo;
  private int edadAproximada;
  private Sexo sexo;
  private String descripcionFisica;
  private String fotos;
  private Duenio miDuenio;
  private Map<String, String> caracteristicas;

  public Mascota(TipoMascota tipoMascota, String nombre, String apodo, int edadAproximada, Sexo sexo, String descripcionFisica, String fotos) {
    this.tipoMascota = tipoMascota;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edadAproximada = edadAproximada;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.caracteristicas = new HashMap<String,String>();
  }

/*  public void agregarCaracteristica(String tipo) {
    this.caracteristicas.put(tipo, ""); // El map, pisa una característica existente?
  }

  public void quitarCaracteristica(String tipo) {
    try {
      this.caracteristicas.remove(tipo);
    }
    catch( ) {
      //TODO No sabemos bien cómo catchear o manejar esta excepción, por lo que optamos hacerlo con una validación común
    }
    this.validarCaracteristicaExistente(tipo);
    this.caracteristicas.remove(tipo);

  }

  public void validarCaracteristicaExistente(String tipo) {
    if (!this.caracteristicas.containsKey((tipo))) {
      throw new CaracteristicaNoEncontradaException("La característica a quitar no existe. Por favor ingrese otra característica");
    }
  }*/

  public void agregarCaracteristicas(Asociacion unaAsoc){
    this.caracteristicas = unaAsoc.getCaracteristicasPedidas();
  }

  public void setDuenio(Duenio duenio) {
    this.miDuenio = duenio;
  }

  public boolean tieneDuenio(Duenio duenio) {
    return miDuenio.equals(duenio);
  }

}
