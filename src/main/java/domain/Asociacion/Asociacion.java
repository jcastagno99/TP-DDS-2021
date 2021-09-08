package domain.Asociacion;

import domain.Mail.Mail;
import domain.Mail.MailSender;
import domain.Mascotas.Mascota;
import domain.Mascotas.MascotaPerdida;
import domain.Mascotas.UbicacionDeDominio;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import exception.CaracteristicaExistenteException;
import exception.CaracteristicaNoEncontradaException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {

  private List<Mascota> mascotasRegistradas;
  private List<Duenio> dueniosRegistrados;
  private List<Caracteristica> caracteristicasPedidas;
  public UbicacionDeDominio ubicacion;
  public ArrayList<PublicacionMascotaPerdida> publicaciones;
  public ArrayList<PublicacionAdopcion> publicacionesAdopcion;
  public ArrayList<PublicacionAdoptante> publicacionesAdoptante;
  public List<Pregunta> preguntasAdopcion;

  public Asociacion(UbicacionDeDominio ubicacion) {
    this.mascotasRegistradas = new ArrayList<>();
    this.dueniosRegistrados = new ArrayList<>();
    this.caracteristicasPedidas = new ArrayList<>();
    this.publicaciones = new ArrayList<>();
    this.ubicacion = ubicacion;
    this.preguntasAdopcion = new ArrayList<>();
    RepositorioAsociaciones.instance().agregarAsociacion(this);
  }

  public void agregarCaracteristicasAMascotas(String caracteristicaNueva) {
    if (this.caracteristicaExistente(caracteristicaNueva)) {
      throw new CaracteristicaExistenteException("La caracteristica que se quiere agregar ya existe");
    }
    caracteristicasPedidas.add(new Caracteristica(caracteristicaNueva));
  }

  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {

    if (!this.caracteristicaExistente(caracteristicaExistente)) {
      throw new CaracteristicaNoEncontradaException("La característica solicitada no se puede eliminar porque no existe");
    }
    this.removerCaracteristica(caracteristicaExistente);
  }

  public void removerCaracteristica(String tipoCaracteristica){
    Caracteristica temporal = caracteristicasPedidas.stream().filter(caracteristica -> caracteristica.esTipo(tipoCaracteristica)).collect(Collectors.toList()).get(0);
    caracteristicasPedidas.remove(temporal);
  }

  public boolean caracteristicaExistente(String tipoCaracteristica) {
    return caracteristicasPedidas.stream().anyMatch(caracteristica -> caracteristica.getTipo().equals(tipoCaracteristica));
  }

  public void agregarMascota(Mascota mascota) {
    mascotasRegistradas.add(mascota);
  }

  public void crearPublicacion(MascotaPerdida mascota, Contacto contacto) {
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(mascota, contacto, this);
    publicaciones.add(publicacion);
    RepositorioAsociaciones.instance().agregarPublicacion(publicacion);
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

  List<PublicacionAdopcion> matchearPublicaciones(PublicacionAdoptante persona, List<PublicacionAdopcion> lista) {
    return lista.stream().filter(publicacionAdopcion -> publicacionAdopcion.seAdaptaA(persona)).collect(Collectors.toList());
  }

  void enviarMailRecomendacion(PublicacionAdoptante adoptante, List<PublicacionAdopcion> publicacionesAdopcion) {
    String cuerpoMail = "Links a las publicaciones: ";
    List<String> links = new ArrayList<>();
    publicacionesAdopcion.forEach(publicacion -> {links.add(publicacion.getLink() + ", ");});
    //TODO usar map y join, repreguntar
    Mail unMail = new Mail("Recomendaciones semanales", cuerpoMail.concat(links.toString()), "noreplay@Asociacion");
    MailSender.instance().sendMail(unMail, adoptante.getContacto().getEmail());
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

  public ArrayList<PublicacionMascotaPerdida> getPublicaciones() {
    return publicaciones;
  }


}
