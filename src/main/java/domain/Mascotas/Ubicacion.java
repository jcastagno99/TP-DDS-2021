package domain.Mascotas;

public class Ubicacion {
  double latitud;
  double longitud;

  public Ubicacion(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public double getLatitud() {
    return latitud;
  }

  public double getLongitud() {
    return longitud;
  }

  public double calcularDistanciaA(Ubicacion otraUbicacion) {
    return Math.hypot((latitud - otraUbicacion.getLatitud()),longitud - otraUbicacion.getLongitud());
  }
}
