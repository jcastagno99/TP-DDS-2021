package domain.Asociacion;

import domain.Mascotas.Mascota;

import java.util.List;

public class RecomendadorAdopcion {

  public static void main(String[] args) {
    List<Asociacion> asociaciones = RepositorioAsociaciones.instance().getAsociaciones();
    asociaciones.forEach(Asociacion::recomendacionesSemanales);
  }

}
