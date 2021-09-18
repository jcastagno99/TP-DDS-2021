package domain.services.RefugiosDDS.entities;

import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Mascotas.Tamanio;
import domain.Mascotas.TipoMascota;
import domain.Mascotas.UbicacionDeDominio;

import java.util.List;

// Clase molde
public class Hogar {

  public String id;
  public String nombre;
  public Ubicacion ubicacion;
  public String telefon;
  public Admisiones admisiones;
  public int capacidad;
  public int lugaresDisponibles;
  public boolean patio;
  public List<String> caracteristicas;

  public boolean puedeAdmitirMascota(MascotaPerdidaSinChapita mascotaPerdida) {
    //especie tamanio y disponibilidad
    return this.hayCapacidad() && this.aceptaPorTamanio(mascotaPerdida) && this.aceptaPorTipo(mascotaPerdida);
  }

  private boolean hayCapacidad() {
    return this.capacidad >= 1;
  }

  private boolean aceptaPorTamanio(MascotaPerdidaSinChapita mascotaPerdida) {
    if (this.patio && this.tamanioParaPatio(mascotaPerdida)) {
      return true;
    } else {
      if (!this.patio && mascotaPerdida.esDeTamanio(Tamanio.PEQUENIO)) {
        return true;
      } else {
        return false; // No tiene patio y la mascota es grande o mediana
        // tiene patio pero la mascota es pequeña
      }
    }
  }

  private boolean tamanioParaPatio(MascotaPerdidaSinChapita mascotaPerdida) {
    return mascotaPerdida.esDeTamanio(Tamanio.MEDIANO) || mascotaPerdida.esDeTamanio(Tamanio.GRANDE);
  }

  private boolean aceptaPorTipo(MascotaPerdidaSinChapita mascotaPerdida) {
    return mascotaPerdida.esDeTipo(TipoMascota.PERRO) && this.admisiones.perros() || mascotaPerdida.esDeTipo(TipoMascota.GATO) && this.admisiones.gatos();
  }

  public boolean tieneRequerimientosExtras() {
    return !this.caracteristicas.isEmpty();
  }

  public boolean tiene(List<String> caracteristicasMascota) {
    return caracteristicasMascota.containsAll(this.caracteristicas);
  }

  public boolean estaDentroDelRadio(UbicacionDeDominio unaUbicacion, int radioDeCercania) {
    UbicacionDeDominio auxiliar = new UbicacionDeDominio(ubicacion.getLat(), ubicacion.getLongitud());
    return radioDeCercania >= unaUbicacion.calcularDistanciaA(auxiliar);
  }
}
