package domain.Asociacion;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Caracteristica {
  String caracteristica;
  String tipo;


  //Solo es llamado por el admin, por eso usa solo el tipo
  public Caracteristica(String tipo, String caracteristica) {
    this.tipo = tipo;
    this.caracteristica = caracteristica;
  }

  public Caracteristica(){}

  public boolean esTipo(String unTipo) {
    return unTipo.equals(tipo);
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public void setCaracteristica(String caracteristica) {
    this.caracteristica = caracteristica;
  }
}
