package domain.Roles;

import domain.Asociacion.Asociacion;
import domain.Asociacion.Publicacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Mascotas.MascotaPerdida;
import domain.Mascotas.Ubicacion;
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
  }

  public void informarMascotaPerdida(String fotos, String descripcion, Ubicacion ubicacion) {
    MascotaPerdida mascota = new MascotaPerdida(fotos, descripcion, ubicacion, this);
    RepositorioMascotasPerdidas.instance().agregarMascotaPerdida(mascota);
    Asociacion asociacionCercana = RepositorioAsociaciones.instance().obtenerAsociacionMasCercaA(ubicacion);
    asociacionCercana.crearPublicacion(mascota,contacto);

  }

  public List<Hogar> solicitarHogares() throws  IOException{
    return ServicioRefugioDDS.instance().listarHogares();
  }
}
