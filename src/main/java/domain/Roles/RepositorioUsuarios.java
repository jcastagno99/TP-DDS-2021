package domain.Roles;

import domain.Asociacion.RepositorioAsociaciones;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios implements WithGlobalEntityManager {

  private static final RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

  public static RepositorioUsuarios instance() {
    return INSTANCE;
  }

  /*public Administrador buscarAdministrador(String usuario, String contrasenia) {
      EntityManager entityManager = this.entityManager();
      return (Administrador) entityManager.createQuery("from Administrador").getSingleResult();
  }*/

  private List<Administrador> administradores = new ArrayList<>();
  private List<Duenio> duenios = new ArrayList<>();
  private List<Voluntario> voluntarios = new ArrayList<>();

  public List<Administrador> getAdministradores() {
    return administradores;
  }

  public void setAdministradores(List<Administrador> administradores) {
    this.administradores = administradores;
  }

  public List<Duenio> getDuenios() {
    return duenios;
  }

  public void setDuenios(List<Duenio> duenios) {
    this.duenios = duenios;
  }

  public List<Voluntario> getVoluntarios() {
    return voluntarios;
  }

  public void setVoluntarios(List<Voluntario> voluntarios) {
    this.voluntarios = voluntarios;
  }
}
