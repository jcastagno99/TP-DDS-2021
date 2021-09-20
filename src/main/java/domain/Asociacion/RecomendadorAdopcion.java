package domain.Asociacion;

import java.util.List;

public class RecomendadorAdopcion {

  public static void main(String[] args) {
    List<Asociacion> asociaciones = RepositorioAsociaciones.instance().getAsociaciones();
    asociaciones.forEach(Asociacion::recomendacionesSemanales);
  }

}
