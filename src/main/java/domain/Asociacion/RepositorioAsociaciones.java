package domain.Asociacion;

import com.sun.org.apache.xpath.internal.functions.Function2Args;
import domain.Mascotas.UbicacionDeDominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RepositorioAsociaciones {

  private static final RepositorioAsociaciones INSTANCE = new RepositorioAsociaciones();

  private List<Asociacion> asociaciones = new ArrayList<>();

  // Esto después va a tener que pasarse a un repositorio general donde estén las asociaciones y las mascotas perdidas

  private List<Publicacion> publicaciones = new ArrayList<>();

  public static RepositorioAsociaciones instance() {
    return INSTANCE;
  }

  public void agregarAsociacion(Asociacion unaAsociacion) {
    asociaciones.add(unaAsociacion);
  }

  public Asociacion obtenerAsociacionMasCercaA(UbicacionDeDominio unaUbicacion) {

    Asociacion asociacionMasCercana = this.asociaciones.stream().collect(Collectors.minBy(Comparator.comparing(asociacion -> asociacion.getUbicacion().calcularDistanciaA(unaUbicacion)))).get();
/*
    List<Double> mapeoAuxiliar = asociaciones.stream().map(unaAsocion -> unaAsocion.getUbicacion().calcularDistanciaA(unaUbicacion)).collect(Collectors.toList());
    Collections.sort(mapeoAuxiliar);
    Double ubicacionMasCercana = mapeoAuxiliar.get(0);

    
    Asociacion asociacionMasCercana1 = this.asociaciones.stream().collect(Collectors.
        minBy((unaAsociacion, otraAsociacion) -> (int) unaAsociacion.getUbicacion().calcularDistanciaA(unaUbicacion) - (int) otraAsociacion.getUbicacion().calcularDistanciaA(unaUbicacion)).get());
    */

    return asociacionMasCercana;
  }

  public void agregarPublicacion(Publicacion unaPublicacion){
    publicaciones.add(unaPublicacion);
  }

  public List<Publicacion> getPublicaciones(){
    return publicaciones;
  }

}
