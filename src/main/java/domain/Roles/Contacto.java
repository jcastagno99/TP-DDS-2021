package domain.Roles;

public class Contacto {

  private Integer telefono;
  private String email;

  public Contacto(Integer telefono, String email) {
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
