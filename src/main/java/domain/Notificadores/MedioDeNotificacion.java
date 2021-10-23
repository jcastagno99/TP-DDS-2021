package domain.Notificadores;

import domain.Mascotas.DatosDeEncuentroDeMascota;
import domain.Roles.Duenio;
import domain.Roles.Rescatista;
import javax.persistence.*;

//@Entity
@MappedSuperclass
public abstract class MedioDeNotificacion {

  @Id
  @GeneratedValue
  private int id;

  public abstract void notificarADuenio(Rescatista rescatista, DatosDeEncuentroDeMascota datos, Duenio duenio);

}
