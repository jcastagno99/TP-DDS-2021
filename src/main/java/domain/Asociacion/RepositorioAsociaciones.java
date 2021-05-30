package domain.Asociacion;

import domain.Mascotas.MascotaPerdida;
import domain.Mascotas.Ubicacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioAsociaciones {

  private static final RepositorioAsociaciones INSTANCE = new RepositorioAsociaciones();

  private List<Asociacion> asociaciones = new ArrayList<>();

  // Esto después va a tener que pasarse a un repositorio general donde estén las publicaciones, las asociaciones y las mascotas perdidas
  // TODO
  private List<Publicacion> publicaciones = new ArrayList<>();

  public static RepositorioAsociaciones instance() {
    return INSTANCE;
  }

  public void agregarAsociacion(Asociacion unaAsociacion) {
    asociaciones.add(unaAsociacion);
  }

  public Asociacion obtenerAsociacionMasCercaA(Ubicacion unaUbicacion) {
    List<Double> mapeoAuxiliar = asociaciones.stream().map(unaAsocion -> unaAsocion.getUbicacion().calcularDistanciaA(unaUbicacion)).collect(Collectors.toList());
    Collections.sort(mapeoAuxiliar);
    Double ubicacionMasCercana = mapeoAuxiliar.get(0);
    return asociaciones.stream().filter(unaAsociacion -> unaAsociacion.getUbicacion().calcularDistanciaA(unaUbicacion) == ubicacionMasCercana).collect(Collectors.toList()).get(0);
  }

  public void agregarPublicacion(Publicacion unaPublicacion){
    publicaciones.add(unaPublicacion);
  }

  public List<Publicacion> getPublicaciones(){
    return publicaciones;
  }
  //

}
