package domain.Asociacion;

import domain.Mascotas.MascotaPerdida;
import domain.Roles.Contacto;

public class Publicacion {

  public MascotaPerdida mascota;
  public Contacto contactoRescatista;
  public Asociacion asociacion;

  public Publicacion(MascotaPerdida mascota, Contacto contactoRescatista, Asociacion asociacion) {
    this.mascota = mascota;
    this.contactoRescatista = contactoRescatista;
    this.asociacion = asociacion;
  }

  public MascotaPerdida getMascota() {
    return mascota;
  }
}
