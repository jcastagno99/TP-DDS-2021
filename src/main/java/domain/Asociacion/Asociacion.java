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

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "asociacion")
  public List<MascotaRegistrada> mascotasRegistradas;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "asociacion")
  public List<Duenio> dueniosRegistrados;
  @ElementCollection
  public List<String> caracteristicasPedidas;
  @Embedded
  public UbicacionDeDominio ubicacion;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  @JoinColumn(name = "id_asociacion", referencedColumnName = "id")
  private List<PublicacionMascotaPerdida> publicacionesMascotaPerdidaSinChapita;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<PublicacionAdopcion> publicacionesAdopcion;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private List<PublicacionAdoptante> publicacionesAdoptante;
  @ManyToMany(cascade = CascadeType.PERSIST) // TODO ver
  private List<Pregunta> preguntasAdopcion;
  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE} , orphanRemoval = true)
  private List<MascotaPerdidaConChapita> mascotasPerdidasConChapita;
  String nombreAsociacion;

  @Id
  @GeneratedValue
  private long id;


  public Asociacion(UbicacionDeDominio ubicacion,String nombreAsociacion) {
    this.mascotasRegistradas = new ArrayList<>();
    this.dueniosRegistrados = new ArrayList<>();
    this.caracteristicasPedidas = new ArrayList<>();
    this.publicacionesMascotaPerdidaSinChapita = new ArrayList<>();
    this.ubicacion = ubicacion;
    this.preguntasAdopcion = new ArrayList<>();
    //RepositorioAsociaciones.instance().agregarAsociacion(this);
    this.mascotasPerdidasConChapita = new ArrayList<>();
    this.nombreAsociacion=nombreAsociacion;
  }

  public Asociacion(){}

  public void agregarCaracteristicasA_Mascotas(String tipoCaracteristicaNueva) {
    if (this.caracteristicaExistente(tipoCaracteristicaNueva)) {
      throw new CaracteristicaExistenteException("La caracteristica que se quiere agregar "
          + "ya existe");
    }
    caracteristicasPedidas.add(tipoCaracteristicaNueva);
  }

  public void eliminarCaracteristica(String caracteristica) {

    if (!this.caracteristicaExistente(caracteristica)) {
      throw new CaracteristicaNoEncontradaException("La característica solicitada no se puede "
          + "eliminar porque no existe");
    }
    this.caracteristicasPedidas.remove(caracteristica);
  }

  //public void removerCaracteristica(String tipoCaracteristica) {
    /*Caracteristica temporal = caracteristicasPedidas.stream().filter(caracteristica ->
        caracteristica.esTipo(tipoCaracteristica)).collect(Collectors.toList()).get(0);
    caracteristicasPedidas.remove(temporal);*/
    /*caracteristicasPedidas.remove(tipoCaracteristica);
  }*/

  public boolean caracteristicaExistente(String tipoCaracteristica) {
   /* return caracteristicasPedidas.stream().anyMatch(caracteristica ->
        caracteristica.getTipo().equalsIgnoreCase(tipoCaracteristica));*/
    return this.caracteristicasPedidas.contains(tipoCaracteristica);
  }

  public void agregarMascota(MascotaRegistrada mascota) {
    mascotasRegistradas.add(mascota);
  }

  public void crearPublicacion(MascotaPerdidaSinChapita mascota, Rescatista rescatista) {
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(mascota,
        rescatista, this);
    publicacionesMascotaPerdidaSinChapita.add(publicacion);
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

  public List<String> getCaracteristicasPedidas() {
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
    return publicacionesMascotaPerdidaSinChapita;
  }

  public String getNombreAsociacion() {
    return nombreAsociacion;
  }

  public void agregarNuevaMascotaPerdidaConChapita(MascotaPerdidaConChapita
      mascotaPerdidaConChapita) {
    this.mascotasPerdidasConChapita.add(mascotaPerdidaConChapita);
  }

  /* La relación Asociacion-Duenio es UNO a MUCHOS, con cardinalidad obligatoria y opcional, respectivamente. Si bien deberia verificarse que un usuario
  * no se regsitro previamente a una ascoaición, esa validación está hecha a nivel vista porque no peude haber dos duenios idénticos registrados
  * */
  public void registrarDuenio(Duenio duenio) {
    this.dueniosRegistrados.add(duenio);
  }

  public long getId(){
    return this.id;
  }
}
