package domain.Asociacion;

import domain.Mascotas.MascotaRegistrada;
import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Mascotas.MascotaPerdidaConChapita;
import domain.Publicaciones.PublicacionAdopcion;
import domain.Publicaciones.PublicacionAdoptante;
import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Roles.Duenio;
import domain.Roles.Rescatista;
import exception.CaracteristicaExistenteException;
import exception.CaracteristicaNoEncontradaException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Asociaciones")
public class Asociacion {

  @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
  public List<MascotaRegistrada> mascotasRegistradas;
  @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
  public List<Duenio> dueniosRegistrados;
  @ManyToMany(cascade = CascadeType.PERSIST)
  public List<Caracteristica> caracteristicasPedidas;
  @Embedded
  public UbicacionDeDominio ubicacion;
  @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
  private List<PublicacionMascotaPerdida> publicaciones;
  @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
  private List<PublicacionAdopcion> publicacionesAdopcion;
  @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
  private List<PublicacionAdoptante> publicacionesAdoptante;
  @ManyToMany(cascade = CascadeType.PERSIST)
  private List<Pregunta> preguntasAdopcion;
  @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
  private List<MascotaPerdidaConChapita> mascotasPerdidasConChapita;

  @Id
  @GeneratedValue
  private long id;


  public Asociacion(UbicacionDeDominio ubicacion) {
    this.mascotasRegistradas = new ArrayList<>();
    this.dueniosRegistrados = new ArrayList<>();
    this.caracteristicasPedidas = new ArrayList<>();
    this.publicaciones = new ArrayList<>();
    this.ubicacion = ubicacion;
    this.preguntasAdopcion = new ArrayList<>();
    RepositorioAsociaciones.instance().agregarAsociacion(this);
    this.mascotasPerdidasConChapita = new ArrayList<>();
  }

  public Asociacion(){}

  public void agregarCaracteristicasA_Mascotas(String caracteristicaNueva) {
    if (this.caracteristicaExistente(caracteristicaNueva)) {
      throw new CaracteristicaExistenteException("La caracteristica que se quiere agregar "
          + "ya existe");
    }
    caracteristicasPedidas.add(new Caracteristica(caracteristicaNueva));
  }

  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {

    if (!this.caracteristicaExistente(caracteristicaExistente)) {
      throw new CaracteristicaNoEncontradaException("La característica solicitada no se puede "
          + "eliminar porque no existe");
    }
    this.removerCaracteristica(caracteristicaExistente);
  }

  public void removerCaracteristica(String tipoCaracteristica) {
    Caracteristica temporal = caracteristicasPedidas.stream().filter(caracteristica ->
        caracteristica.esTipo(tipoCaracteristica)).collect(Collectors.toList()).get(0);
    caracteristicasPedidas.remove(temporal);
  }

  public boolean caracteristicaExistente(String tipoCaracteristica) {
    return caracteristicasPedidas.stream().anyMatch(caracteristica ->
        caracteristica.getTipo().equals(tipoCaracteristica));
  }

  public void agregarMascota(MascotaRegistrada mascota) {
    mascotasRegistradas.add(mascota);
  }

  public void crearPublicacion(MascotaPerdidaSinChapita mascota, Rescatista rescatista) {
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(mascota,
        rescatista, this);
    publicaciones.add(publicacion);
  }

  public void agregarPreguntaAdopcion(Pregunta pregunta) {
    preguntasAdopcion.add(pregunta);
  }

  public void agregarPublicacionAdopcion(PublicacionAdopcion publicacion) {
    publicacion.agregarPreguntas(preguntasAdopcion);
    publicacionesAdopcion.add(publicacion);
  }

  public void agregarPublicacionAdoptante(PublicacionAdoptante publicacion) {
    publicacionesAdoptante.add(publicacion);
  }

  void recomendacionesSemanales() {
    publicacionesAdoptante.forEach(publicacionAdoptante ->
        this.enviarMailRecomendacion(publicacionAdoptante,
            this.matchearPublicaciones(publicacionAdoptante, publicacionesAdopcion)));
  }

  List<PublicacionAdopcion> matchearPublicaciones(PublicacionAdoptante persona,
      List<PublicacionAdopcion> lista) {
    return lista.stream().filter(publicacionAdopcion ->
        publicacionAdopcion.seAdaptaA(persona)).collect(Collectors.toList());
  }

  void enviarMailRecomendacion(PublicacionAdoptante adoptante, List<PublicacionAdopcion>
      publicacionesAdopcion) {
    String cuerpoMail = "Links a las publicaciones: ";
    List<String> links = new ArrayList<>();
    publicacionesAdopcion.forEach(publicacion -> {
      links.add(publicacion.getLink() + ", "); }
    );
    //TODO usar map y join, repreguntar
    //Mail unMail = new Mail("Recomendaciones semanales", cuerpoMail.concat(links.toString()), "noreplay@Asociacion");
    //MailSender.instance().sendMail(unMail, adoptante.getContacto().getEmail());
  }

  public void agregarNuevoDuenio(Duenio unDuenio) {
    this.dueniosRegistrados.add(unDuenio);
  }

  public List<Caracteristica> getCaracteristicasPedidas() {
    return caracteristicasPedidas;
  }

  public List<Duenio> getDueniosRegistrados() {
    return dueniosRegistrados;
  }

  public UbicacionDeDominio getUbicacion() {
    return ubicacion;
  }

  public List<MascotaRegistrada> getMascotasRegistradas() {
    return mascotasRegistradas;
  }

  public List<PublicacionMascotaPerdida> getPublicaciones() {
    return publicaciones;
  }

  public void agregarNuevaMascotaPerdidaConChapita(MascotaPerdidaConChapita
      mascotaPerdidaConChapita) {
    this.mascotasPerdidasConChapita.add(mascotaPerdidaConChapita);
  }
}
