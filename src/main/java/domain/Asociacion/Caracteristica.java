package domain.Asociacion;

public class Caracteristica {
  String caracteristica;
  String tipo;

  //Solo es llamado por el admin, por eso usa solo el tipo
  public Caracteristica(String tipo) {
    this.tipo = tipo;
  }

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
