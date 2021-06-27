package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Mail.Mail;
import domain.Mail.MailSender;
import domain.Mascotas.*;
import domain.Asociacion.RepositorioMascotasPerdidas;
import domain.services.RefugiosDDS.ServicioRefugioDDS;
import domain.services.RefugiosDDS.entities.Hogar;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class Rescatista {
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;
  private String documento;
  private int numeroDocumento;
  private Contacto contacto;
  private String direccion;

  public Rescatista(String nombre, String apellido, LocalDate fechaNacimiento, String documento, int numeroDocumento, Contacto contacto, String direccion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.documento = documento;
    this.numeroDocumento = numeroDocumento;
    this.contacto = contacto;
    this.direccion = direccion;
    //this.dependenciaRepoAsoc = RepositorioAsociaciones;
    //this.dependenciaRepoMascotasPerdidas = RepositorioMascotasPerdidas;
  }

  public Contacto getContacto() {
    return contacto;
  }

  public void informarMascotaPerdidaSinChapita(String fotos, String descripcion, UbicacionDeDominio ubicacion, TipoMascota tipoMascota, Tamanio tamanio) {
    MascotaPerdida mascota = new MascotaPerdida(fotos, descripcion, ubicacion, this, tipoMascota, tamanio);
    RepositorioMascotasPerdidas.instance().agregarMascotaPerdida(mascota);
    Asociacion asociacionCercana = RepositorioAsociaciones.instance().obtenerAsociacionMasCercaA(ubicacion);
    asociacionCercana.crearPublicacion(mascota,contacto);
  }

  public void informarMascotaPerdidaConChapita(Mascota mascota) {
    String telefono = String.valueOf(contacto.getTelefono());
    Mail unMail = new Mail("Fue encontrada su mascota: " + mascota.getNombre(), "Comuniquese con el rescatista: " + telefono, contacto.getEmail());
    // TODO Ver tema de testeo. Si poner el MailSender como un atributo del duenio y hacer ID
    MailSender.instance().sendMail(unMail, mascota.getMiDuenio().getContacto().getEmail());
  }

  public List<Hogar> solicitarHogares() throws  IOException {
    return ServicioRefugioDDS.instance().listarHogares();
  }
}
