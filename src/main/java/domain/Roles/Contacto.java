package domain.Roles;

public class Contacto {
  // TODO ver si en esta clase sól odejar teléfono y email, ay qeu son redundantes con los datos que tienen las clases
  // Rescatista y Duenio, que son las que lo referencian, si bein en el enunciado dice que un dato de contacto está
  // conformado por estos campos
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
