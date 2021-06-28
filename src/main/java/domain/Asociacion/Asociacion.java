package domain.Asociacion;

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
  public HashMap<String, String> preguntasAdopcion;

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

  public Map<String, String> getCaracteristicasPedidas() {
    return caracteristicasPedidas;
  }

  public void agregarNuevoDuenio(Duenio unDuenio) {
    this.dueniosRegistrados.add(unDuenio);
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
