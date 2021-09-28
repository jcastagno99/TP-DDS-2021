package domain.Roles;

import org.hibernate.annotations.GenericGenerator;
import password.ValidadorDeMetricas;

import javax.persistence.*;


@Entity
public abstract class Usuario {

  private String usuario;
  private String contrasenia;
  private String nombre;
  private String apellido;

  @Transient
  public ValidadorDeMetricas miValidador = new ValidadorDeMetricas();

  public Usuario(String usuario, String contrasenia, String nombre, String apellido) {
    miValidador.validar(usuario, contrasenia);
    this.contrasenia = contrasenia;
    this.usuario = usuario;
    this.nombre = nombre;
    this.apellido = apellido;
  }

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private long id;

  public Usuario() {}

  public String getContrasenia() {
    return contrasenia;
  }

  public String getApellido() {
    return apellido;
  }

  public String getUsuario() {
    return this.usuario;
  }

}