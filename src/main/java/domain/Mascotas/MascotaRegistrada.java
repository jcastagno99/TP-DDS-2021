package domain.Mascotas;

import domain.Asociacion.Asociacion;
import domain.Asociacion.Caracteristica;
import domain.Roles.Duenio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MascotaRegistrada {
  private TipoMascota tipoMascota;
  private String nombre;
  private String apodo;
  private int edadAproximada;
  private Sexo sexo;
  private String descripcionFisica;
  private String fotos;
  private Duenio miDuenio;
  private List<Caracteristica> caracteristicas;
  private List<String> necesidades;

  public MascotaRegistrada(TipoMascota tipoMascota, String nombre, String apodo, int edadAproximada, Sexo sexo, String descripcionFisica, String fotos) {
    this.tipoMascota = tipoMascota;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edadAproximada = edadAproximada;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.caracteristicas = new ArrayList<>();
  }

  public void copiarCaracteristicas(Asociacion unaAsoc) {
    this.caracteristicas = unaAsoc.getCaracteristicasPedidas();
  }

  public void setearCaracteristica(String caracteristica, String respuesta) {
    Caracteristica caracteristicaBuscada = this.buscarCaracteristica(caracteristica);
    caracteristicaBuscada.setCaracteristica(respuesta);
  }
  //TODO esto funciona solamente si no se agregan varias caracteristicas con un misto tipo EJ: 2 alturas
  // para caracteristicas con sentido como el color, se pueden usar respuestas compuestas o 2 tipos diferentes, EJ:
  // COLORPRIMARIO, COLORSECUNDARIO
  public Caracteristica buscarCaracteristica(String tipoCaracteristica) {
    return caracteristicas.stream().filter(caracteristica -> caracteristica.esTipo(tipoCaracteristica)).collect(Collectors.toList()).get(0);
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

  public List<String> getNecesidades() {
    return necesidades;
  }

  public List<Caracteristica> getCaracteristicas() {
    return caracteristicas;
  }
}