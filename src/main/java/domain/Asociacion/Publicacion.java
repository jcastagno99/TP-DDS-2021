package domain.Asociacion;

import domain.Mascotas.MascotaPerdida;
import domain.Roles.Contacto;

public class Publicacion {

  MascotaPerdida mascota;
  Contacto contactoRescatista;
  Asociacion asociacion;

  public Publicacion(MascotaPerdida mascota, Contacto contactoRescatista, Asociacion asociacion) {
    this.mascota = mascota;
    this.contactoRescatista = contactoRescatista;
    this.asociacion = asociacion;
  }
}
