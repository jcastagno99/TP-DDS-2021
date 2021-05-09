package domain.Mascotas;

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
  private Map<TipoCaracteristica, String> caracteristicas;

  public Mascota(TipoMascota tipoMascota, String nombre, String apodo, int edadAproximada, Sexo sexo, String descripcionFisica, String fotos) {
    this.tipoMascota = tipoMascota;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edadAproximada = edadAproximada;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.caracteristicas = new HashMap<TipoCaracteristica, String>();
  }

  public void agregarCaracteristica(TipoCaracteristica tipo) {
    this.caracteristicas.put(tipo, ""); // El map, pisa una característica existente?
  }

  public void quitarCaracteristica(TipoCaracteristica tipo) {
    /*try {
      this.caracteristicas.remove(tipo);
    }
    catch( ) {
      //TODO No sabemos bien cómo catchear o manejar esta excepción, por lo que optamos hacerlo con una validación común
    }*/
    this.validarCaracteristicaExistente(tipo);
    this.caracteristicas.remove(tipo);

  }

  public void validarCaracteristicaExistente(TipoCaracteristica tipo) {
    if (!this.caracteristicas.containsKey((tipo))) {
      throw new CaracteristicaNoEncontradaException("La característica a quitar no existe. Por favor ingrese otra característica");
    }
  }


}
