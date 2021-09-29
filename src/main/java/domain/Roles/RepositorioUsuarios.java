package domain.Roles;

import exception.BusquedaEnBaseDeDatosException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class RepositorioUsuarios implements WithGlobalEntityManager {

  private static final RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

  public static RepositorioUsuarios instance() {
    return INSTANCE;
  }

  public Administrador buscarAdministrador(String nombreUsuario, String contrasenia) {
    EntityManager entityManager = this.entityManager();
    try {
      Administrador admin = (Administrador) entityManager.createQuery("from Administrador a where a.usuario = :nombreDeUsuario and a.contrasenia = :contrasenia")
          .setParameter("nombreDeUsuario", nombreUsuario)
          .setParameter("contrasenia", contrasenia)
          .getSingleResult();
      return admin;
    }  catch(NoResultException e) {
      throw new BusquedaEnBaseDeDatosException("No se ha encontrado un usuario registrado con los datos de inicio de sesión aportados");
    }
  }

  public Duenio buscarDuenio(String nombreUsuario, String contrasenia) {
    EntityManager entityManager = this.entityManager();
    try {
      Duenio duenio = (Duenio) entityManager.createQuery("from Duenio a where a.usuario = :nombreDeUsuario and a.contrasenia = :contrasenia")
          .setParameter("nombreDeUsuario", nombreUsuario)
          .setParameter("contrasenia", contrasenia)
          .getSingleResult();
      return duenio;
    }  catch(NoResultException e) {
      throw new BusquedaEnBaseDeDatosException("No se ha encontrado un usuario registrado con los datos de inicio de sesión aportados");
    }
  }

  public Voluntario buscarVoluntario(String nombreUsuario, String contrasenia) {
    EntityManager entityManager = this.entityManager();
    try {
      Voluntario voluntario = (Voluntario) entityManager.createQuery("from Voluntario a where a.usuario = :nombreDeUsuario and a.contrasenia = :contrasenia")
          .setParameter("nombreDeUsuario", nombreUsuario)
          .setParameter("contrasenia", contrasenia)
          .getSingleResult();
      return voluntario;
    }  catch(NoResultException e) {
      throw new BusquedaEnBaseDeDatosException("No se ha encontrado un usuario registrado con los datos de inicio de sesión aportados");
    }
  }

}
