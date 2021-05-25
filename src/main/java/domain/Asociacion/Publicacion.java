package domain.Asociacion;

import domain.Mascotas.MascotaPerdida;
import domain.Roles.Contacto;

public class Publicacion {

  MascotaPerdida mascota;
  Contacto contactoRescatista;

  public Publicacion(MascotaPerdida mascota, Contacto contactoRescatista) {
    this.mascota = mascota;
    this.contactoRescatista = contactoRescatista;
  }
}
