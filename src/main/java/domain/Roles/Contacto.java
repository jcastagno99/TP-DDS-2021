package domain.Roles;

public class Contacto {
  private String nombre;
  private String apellido;
  private Integer telefono;
  private String email;

  public Contacto(String nombre, String apellido, Integer telefono, String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.email = email;
  }

  public Integer getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }
}
