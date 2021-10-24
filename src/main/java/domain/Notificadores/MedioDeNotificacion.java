package domain.Notificadores;

import domain.Mascotas.DatosDeEncuentroDeMascota;
import domain.Roles.Duenio;
import domain.Roles.Rescatista;

public interface MedioDeNotificacion {

  void notificarADuenio(Rescatista rescatista, DatosDeEncuentroDeMascota datos, Duenio duenio);

}
