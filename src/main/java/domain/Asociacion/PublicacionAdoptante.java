package domain.Asociacion;

import domain.Roles.Contacto;
import domain.Roles.Duenio;

import java.util.List;

public class PublicacionAdoptante {
  List<Caracteristica> preferencias;
  List<String> comodidades;
  Duenio publicante;

  public PublicacionAdoptante(List<Caracteristica> preferencias, List<String> comodidades) {
    this.preferencias = preferencias;
    this.comodidades = comodidades;
  }


  public Contacto getContacto() {
    return publicante.getContacto();
  }

  public List<Caracteristica> getPreferencias() {
    return preferencias;
  }

  public List<String> getComodidades() {
    return comodidades;
  }

}
