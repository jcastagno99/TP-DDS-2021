package domain.Roles;

import domain.services.HogaresDeTransitoDDS.ServicioHogaresDeTransitoDds;
import domain.services.HogaresDeTransitoDDS.entities.Hogar;
import java.io.IOException;
import java.util.List;


public class Rescatista {

  DatosFormulario datosFormulario;

  public Rescatista(DatosFormulario datosFormulario) {
    this.datosFormulario = datosFormulario;
  }

  public List<Hogar> solicitarHogares() throws  IOException {
    return ServicioHogaresDeTransitoDds.instance().listarHogares();
  }

  public DatosFormulario getDatosFormulario() {
    return datosFormulario;
  }
}
