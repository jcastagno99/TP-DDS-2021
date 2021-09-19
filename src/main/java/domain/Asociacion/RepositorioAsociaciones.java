package domain.Asociacion;

import domain.Mascotas.Mascota;
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


  public List<PublicacionMascotaPerdida> getPublicaciones() {
    //TODO aplanar lista de lista de publicaciones de asociaciones
    // Sólo deberian msotrarse los datos no sensibles del rescatista. luego, cuando alguien decida entrar en contacto con el, ahi si enviarle los datos por privado
    return null;
  }

  public List<Asociacion> getAsociaciones() {
    return asociaciones;
  }

  public List<Pregunta> getPreguntasObligatorias() {
    return preguntasObligatorias;
  }

  public Asociacion obtenerAsociacionALaQuePerteneceMascota(Mascota mascota){
    return this.asociaciones.stream().filter(asociacion -> asociacion.getMascotasRegistradas().contains(mascota)).collect(Collectors.toList()).get(0);
  }
}
