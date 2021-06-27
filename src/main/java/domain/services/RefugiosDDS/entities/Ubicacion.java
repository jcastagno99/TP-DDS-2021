package domain.services.RefugiosDDS.entities;

public class Ubicacion {
  public String direccion;
  public int lat;
  public int longitud;

  public String getDireccion() {
    return this.direccion;
  }

  public int getLat() {
    return this.lat;
  }

  public int getLongitud() {
    return longitud;
  }
}
