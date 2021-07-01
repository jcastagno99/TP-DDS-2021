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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asociacion {

  private List<Mascota> mascotasRegistradas;
  private List<Duenio> dueniosRegistrados;
  private Map<String, String> caracteristicasPedidas;
  public UbicacionDeDominio ubicacion;
  public ArrayList<PublicacionMascotaPerdida> publicaciones;
  public ArrayList<PublicacionAdopcion> publicacionesAdopcion;
  public ArrayList<PublicacionAdoptante> publicacionesAdoptante;
  public HashMap<String, String> preguntasAdopcion; //TODO Deberia contener solo las preguntas, List<Pregunta>

  public Asociacion(UbicacionDeDominio ubicacion) {
    mascotasRegistradas = new ArrayList<>();
    dueniosRegistrados = new ArrayList<>();
    caracteristicasPedidas = new HashMap<>();
    publicaciones = new ArrayList<>();
    this.ubicacion = ubicacion;
    RepositorioAsociaciones.instance().agregarAsociacion(this);
  }

  public void agregarCaracteristicasAMascotas(String caracteristicaNueva) {
    if (this.caracteristicaExistente(caracteristicaNueva)) {
      throw new CaracteristicaExistenteException("La caracteristica que se quiere agregar ya existe");
    }
    caracteristicasPedidas.put(caracteristicaNueva, "");
  }

  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {

    if (!this.caracteristicaExistente(caracteristicaExistente)) {
      throw new CaracteristicaNoEncontradaException("La característica solicitada no se puede eliminar porque no existe");
    }
    caracteristicasPedidas.remove(caracteristicaExistente);
  }

  public boolean caracteristicaExistente(String caracteristica) {
    return caracteristicasPedidas.containsKey(caracteristica);
  }

  public void agregarMascota(Mascota mascota) {
    mascotasRegistradas.add(mascota);
  }

  public void crearPublicacion(MascotaPerdida mascota, Contacto contacto) {
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(mascota, contacto, this);
    publicaciones.add(publicacion);
    RepositorioAsociaciones.instance().agregarPublicacion(publicacion);
  }

  public void agregarPreguntaAdopcion(String pregunta){
    preguntasAdopcion.put(pregunta,"");
  }

  public void agregarPublicacionAdopcion(PublicacionAdopcion publicacion){
    publicacion.agregarPreguntas(preguntasAdopcion);
    publicacionesAdopcion.add(publicacion);
  }

  public void agregarPublicacionAdoptante(PublicacionAdoptante publicacion){
    publicacionesAdoptante.add(publicacion);
  }

  void recomendacionesSemanales(){
    List<PublicacionAdopcion> publicacionesMatcheadas = new ArrayList<>();
    publicacionesAdoptante.forEach(publicacionAdoptante ->
    {publicacionesAdopcion.forEach(publicacionMascota -> //TODO Cambiar por un filter
    {if(publicacionMascota.seAdaptaA(publicacionAdoptante)){
      publicacionesMatcheadas.add(publicacionMascota);}
    }
    );this.enviarMailRecomendacion(publicacionAdoptante,publicacionesMatcheadas);});
  }

  void enviarMailRecomendacion(PublicacionAdoptante adoptante, List<PublicacionAdopcion> publicacionesAdopcion){
    String cuerpoMail = "Links a las publicaciones: ";
    List<String> links = new ArrayList<>();
    publicacionesAdopcion.forEach(publicacion -> {links.add(publicacion.getLink());});
    //TODO usar map y join
    Mail unMail = new Mail("Recomendaciones semanales", cuerpoMail.concat(links.toString()),"noreplay@Asociacion");
    MailSender.instance().sendMail(unMail,adoptante.getContacto().getEmail());
  }

  public void agregarNuevoDuenio(Duenio unDuenio) {
    this.dueniosRegistrados.add(unDuenio);
  }

  public Map<String, String> getCaracteristicasPedidas() {
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
