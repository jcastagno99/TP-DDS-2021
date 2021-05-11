package domain.Asociacion;

import domain.Mascotas.Mascota;
import domain.Mascotas.TipoCaracteristica;
import domain.Roles.Duenio;
import exception.CaracteristicaExistenteException;
import exception.CaracteristicaNoEncontradaException;
import exception.ReadfileException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asociacion {

  private List<Mascota> mascotasRegistradas;
  //private List<Duenio> duenios;
  //private List<String> caracteristicasPosibles;
  private Map<String, String> caracteristicasPedidas;

  public Asociacion() {
    mascotasRegistradas = new ArrayList<>();
    caracteristicasPedidas = new HashMap<>();
  }


  /*public void agregarCaracteristicasAMascotas(String caracteristicaNueva) {
    this.mascotasRegistradas.stream().forEach(mascota -> mascota.agregarCaracteristica(caracteristicaNueva));
  }
  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {
    this.mascotasRegistradas.stream().forEach(mascota -> mascota.quitarCaracteristica(caracteristicaExistente));
  }*/

  public void agregarCaracteristicasAMascotas(String caracteristicaNueva) {
    if(this.caracteristicaExistente(caracteristicaNueva)) {
      throw new CaracteristicaExistenteException("La caracteristica que se quiere agregar ya existe");
    }
    //caracteristicasPosibles.add(caracteristicaNueva);
    caracteristicasPedidas.put(caracteristicaNueva,"");
  }

  public void eliminarCaracteristicaExistente(String caracteristicaExistente) {

    if(!this.caracteristicaExistente(caracteristicaExistente)){
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

  public Map<String, String> getCaracteristicasPedidas(){
    return caracteristicasPedidas;
  }
}
