package domain.Roles;

import domain.services.HogaresDeTransitoDDS.ServicioHogaresDeTransitoDds;
import domain.services.HogaresDeTransitoDDS.entities.Hogar;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

@Entity
public class Rescatista {

  @OneToOne(cascade = {CascadeType.ALL})
  DatosFormulario datosFormulario;

  @Id
  @GeneratedValue
  private long id;

  public Rescatista(DatosFormulario datosFormulario) {
    this.datosFormulario = datosFormulario;
  }

  public Rescatista(){}

  public List<Hogar> solicitarHogares() throws  IOException {
    return ServicioHogaresDeTransitoDds.instance().listarHogares();
  }

  public DatosFormulario getDatosFormulario() {
    return datosFormulario;
  }
}
