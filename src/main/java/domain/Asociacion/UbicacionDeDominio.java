package domain.Asociacion;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class UbicacionDeDominio {
  double latitud;
  double longitud;

  @Id
  @GeneratedValue
  private long id;


  public UbicacionDeDominio(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public UbicacionDeDominio(){}

  public double getLatitud() {
    return latitud;
  }

  public double getLongitud() {
    return longitud;
  }

  public double calcularDistanciaA(UbicacionDeDominio otraUbicacion) {
    return Math.hypot((latitud - otraUbicacion.getLatitud()), longitud
        - otraUbicacion.getLongitud());
  }

}
