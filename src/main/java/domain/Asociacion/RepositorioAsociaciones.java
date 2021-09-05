package domain.Asociacion;

import domain.Mascotas.UbicacionDeDominio;
import domain.Roles.Duenio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioAsociaciones {

  private static final RepositorioAsociaciones INSTANCE = new RepositorioAsociaciones();

  private List<Asociacion> asociaciones = new ArrayList<>();
  private List<Pregunta> preguntasObligatorias; // Mover a un repo propio

  // Esto después va a tener que pasarse a un repositorio general donde estén las asociaciones y las mascotas perdidas

  private List<PublicacionMascotaPerdida> publicaciones = new ArrayList<>();

  public static RepositorioAsociaciones instance() {
    return INSTANCE;
  }

  public void agregarAsociacion(Asociacion unaAsociacion) {
    asociaciones.add(unaAsociacion);
  }

  public Asociacion obtenerAsociacionMasCercaA(UbicacionDeDominio unaUbicacion) {
    return this.asociaciones.stream().min(Comparator.comparing(asociacion -> asociacion.getUbicacion().calcularDistanciaA(unaUbicacion))).get();
  }

  public Asociacion obtenerAsociacionALaQuePertenece(Duenio unDuenio) {
    return this.asociaciones.stream().filter(asociacion -> asociacion.getDueniosRegistrados().contains(unDuenio)).collect(Collectors.toList()).get(0);
  }

  public void agregarPublicacion(PublicacionMascotaPerdida unaPublicacion){
    publicaciones.add(unaPublicacion);
  }

  public List<PublicacionMascotaPerdida> getPublicaciones(){
    return publicaciones;
  }

  public List<Asociacion> getAsociaciones() {
    return asociaciones;
  }

  public List<Pregunta> getPreguntasObligatorias() {
    return preguntasObligatorias;
  }
}
