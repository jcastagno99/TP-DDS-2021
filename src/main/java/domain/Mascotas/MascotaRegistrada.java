package domain.Mascotas;

import domain.Asociacion.Asociacion;
import domain.Asociacion.Caracteristica;
import domain.Roles.Duenio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class MascotaRegistrada {
  private TipoMascota tipoMascota;
  private String nombre;
  private String apodo;
  private int edadAproximada;
  private Sexo sexo;
  private String descripcionFisica;
  private String fotos;

  // TODO quizás este atributo no haga falta más que para los tests
  @ManyToOne(cascade = CascadeType.MERGE)
  private Duenio duenio;

  @ElementCollection
  private List<Caracteristica> caracteristicas;
  @ElementCollection
  private List<String> necesidades;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "asociacion_id", referencedColumnName = "id")
  private Asociacion asociacion;

  @Id
  @GeneratedValue
  private long id;

  public MascotaRegistrada(TipoMascota tipoMascota, String nombre, String apodo, int
      edadAproximada, Sexo sexo, String descripcionFisica, Duenio duenio, String fotos) {
    this.tipoMascota = tipoMascota;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edadAproximada = edadAproximada;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.caracteristicas = new ArrayList<>();
    this.duenio = duenio;
    this.asociacion = null;
  }

  public MascotaRegistrada(){}
/*
  public void copiarCaracteristicas(Asociacion unaAsoc) {
    this.caracteristicas = unaAsoc.getCaracteristicasPedidas();
  }*/

  public void setearCaracteristica(String caracteristica, String respuesta) {
    Caracteristica caracteristicaBuscada = this.buscarCaracteristica(caracteristica);
    caracteristicaBuscada.setCaracteristica(respuesta);
  }
  //TODO esto funciona solamente si no se agregan varias caracteristicas con un misto tipo EJ: 2 alturas
  // para caracteristicas con sentido como el color, se pueden usar respuestas compuestas o 2 tipos diferentes, EJ:
  // COLORPRIMARIO, COLORSECUNDARIO

  public Caracteristica buscarCaracteristica(String tipoCaracteristica) {
    return caracteristicas.stream().filter(caracteristica -> caracteristica
    .esTipo(tipoCaracteristica)).collect(Collectors.toList()).get(0);
  }

  public void agregarCaracteristica(Caracteristica caracteristica) {
    this.caracteristicas.add(caracteristica);
  }

  public boolean tieneDuenio(Duenio duenio) {
    return this.duenio.equals(duenio);
  }

  public Duenio getDuenio() {
    return duenio;
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

  public long getId(){
    return this.id;
  }

  public void registrarEn(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

  public long getAsociacionId() {
    return asociacion.getId();
  }
}
