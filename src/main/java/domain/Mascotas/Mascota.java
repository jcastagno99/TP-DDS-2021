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

  public void copiarCaracteristicas(Asociacion unaAsoc) {
    this.caracteristicas = unaAsoc.getCaracteristicasPedidas();
  }

  public void setearCaracteristica(String caracteristica, String descripcion) {

    if (!this.caracteristicaExistente(caracteristica)) {
      throw new CaracteristicaNoEncontradaException("La descripcion solicitada no se puede agregar porque la caracteristica no existe");
    }
    this.caracteristicas.put(caracteristica, descripcion);
  }

  public void setDuenio(Duenio duenio) {
    this.miDuenio = duenio;
  }

  public boolean tieneDuenio(Duenio duenio) {
    return miDuenio.equals(duenio);
  }

  public Duenio getMiDuenio() {
    return miDuenio;
  }

  public String getNombre() {
    return nombre;
  }

  public boolean caracteristicaExistente(String caracteristica) {
    return this.caracteristicas.containsKey(caracteristica);
  }


}
