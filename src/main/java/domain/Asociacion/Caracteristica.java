package domain.Asociacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Caracteristica {
  String caracteristica;
  String tipo;

  @Id
  @GeneratedValue
  private long id;

  //Solo es llamado por el admin, por eso usa solo el tipo
  public Caracteristica(String tipo) {
    this.tipo = tipo;
  }

  public Caracteristica(){}

  public boolean esTipo(String unTipo) {
    return unTipo.equals(tipo);
  }

  public String getTipo() {
    return tipo;
  }

  public void setCaracteristica(String caracteristica) {
    this.caracteristica = caracteristica;
  }
}
