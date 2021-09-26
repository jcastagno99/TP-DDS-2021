package domain.Asociacion;

import domain.Mascotas.MascotaRegistrada;
import domain.Publicaciones.PublicacionMascotaPerdida;
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
    return this.asociaciones.stream().min(Comparator.comparing(asociacion -> asociacion
        .getUbicacion().calcularDistanciaA(unaUbicacion))).get();
  }

  public Asociacion obtenerAsociacionA_LaQuePertenece(Duenio unDuenio) {
    return this.asociaciones.stream().filter(asociacion -> asociacion.getDueniosRegistrados()
        .contains(unDuenio)).collect(Collectors.toList()).get(0);
  }


  public List<PublicacionMascotaPerdida> getPublicaciones() {
    //TODO aplanar lista de lista de publicaciones de asociaciones
    // Sólo deberian mostrarse los datos no sensibles del rescatista. Luego, cuando alguien
    // decida entrar en contacto con el,ahí sí enviarle los datos por privado
    return null;
  }

  public List<Asociacion> getAsociaciones() {
    return asociaciones;
  }

  public List<Pregunta> getPreguntasObligatorias() {
    return preguntasObligatorias;
  }

  public Asociacion obtenerAsociacionA_LaQuePerteneceMascota(MascotaRegistrada mascota) {
    return this.asociaciones.stream().filter(asociacion -> asociacion.getMascotasRegistradas()
        .contains(mascota)).collect(Collectors.toList()).get(0);
  }
}
