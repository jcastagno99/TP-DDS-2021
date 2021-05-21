package domain.Asociacion;

import domain.Mascotas.Mascota;
import domain.Roles.Duenio;
import exception.CaracteristicaExistenteException;
import exception.CaracteristicaNoEncontradaException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asociacion {

  private List<Mascota> mascotasRegistradas;
  private List<Duenio> dueniosRegistrados;
  private Map<String, String> caracteristicasPedidas;

  public Asociacion() {
    mascotasRegistradas = new ArrayList<>();
    dueniosRegistrados = new ArrayList<>();
    caracteristicasPedidas = new HashMap<>();
  }

  public void agregarCaracteristicasAMascotas(String caracteristicaNueva) {
    if (this.caracteristicaExistente(caracteristicaNueva)) {
      throw new CaracteristicaExistenteException("La caracteristica que se quiere agregar ya existe");
    }
    caracteristicasPedidas.put(caracteristicaNueva, "");
  }

  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {

    if (!this.caracteristicaExistente(caracteristicaExistente)) {
      throw new CaracteristicaNoEncontradaException("La característica solicitada no se puede eliminar porque no existe");
    }
    caracteristicasPedidas.remove(caracteristicaExistente);
  }

  public boolean caracteristicaExistente(String caracteristica) {
    return caracteristicasPedidas.containsKey(caracteristica);
  }

  public void agregarMascota(Mascota mascota) {
    mascotasRegistradas.add(mascota);
  }

  public Map<String, String> getCaracteristicasPedidas() {
    return caracteristicasPedidas;
  }

  public void agregarNuevoDuenio(Duenio unDuenio) {
    this.dueniosRegistrados.add(unDuenio);
  }

  public List<Duenio> getDueniosRegistrados() {
    return dueniosRegistrados;
  }

}
