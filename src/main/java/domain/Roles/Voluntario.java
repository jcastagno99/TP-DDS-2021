package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Mascotas.MascotaPerdidaConChapita;

/* Si bien por ahora esta clase queda como un data class, no hay responsabilidades determiandas en
* el voluntario; sólo parueba o rechaza publicaciones de mascotas perdidas sin chapita y habilita
* la comunicación con el dueño para el caso de mascotas con chapita. Es preferible tener un data
* class a cuatro misplaced methods
* */

public class Voluntario extends Usuario {

  Asociacion asociacion;

  public Voluntario(String usuario, String contrasenia, Asociacion asociacion) {
    super(usuario, contrasenia);
    this.asociacion = asociacion;
  }

}