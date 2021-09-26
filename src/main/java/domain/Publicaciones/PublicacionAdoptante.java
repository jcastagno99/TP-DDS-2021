package domain.Publicaciones;

import domain.Asociacion.Caracteristica;
import domain.Roles.Contacto;
import domain.Roles.Duenio;

import javax.persistence.*;
import java.util.List;

@Entity
public class PublicacionAdoptante {

  @OneToMany
  List<Caracteristica> preferencias;

  @ElementCollection
  List<String> comodidades;

  @ManyToOne
  Duenio publicante;

  @Id
  @GeneratedValue
  private long id;

  public PublicacionAdoptante(List<Caracteristica> preferencias, List<String> comodidades) {
    this.preferencias = preferencias;
    this.comodidades = comodidades;
  }

  public PublicacionAdoptante(){}


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
