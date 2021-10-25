package domain.Asociacion;

import java.util.List;

public class RecomendadorAdopcion {

  public static void main(String[] args) {
    List<Asociacion> asociaciones = RepositorioAsociaciones.instance().obtenerAsociaciones();
    asociaciones.forEach(Asociacion::recomendacionesSemanales);
  }

}
