package domain.Mascotas;

public class UbicacionDeDominio {
  double latitud;
  double longitud;

  public UbicacionDeDominio(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public double getLatitud() {
    return latitud;
  }

  public double getLongitud() {
    return longitud;
  }

  public double calcularDistanciaA(UbicacionDeDominio otraUbicacion) {
    return Math.hypot((latitud - otraUbicacion.getLatitud()),longitud - otraUbicacion.getLongitud());
  }

}
