package domain.Roles;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import exception.BusquedaEnBaseDeDatosException;
import exception.UsuarioYaRegistradoException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/* Para el tercer argumento de las búsquedas,se obtiene el nombre de la tabla
 * correspondiente a la clase por la que se busque, para así hacerlo más flexible, y si cambiara
 * el nombre de la clase, no tener problemas
 */

public class RepositorioUsuarios implements WithGlobalEntityManager {

  private static final RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

  public static RepositorioUsuarios instance() {
    return INSTANCE;
  }

  //private final EntityManager entityManager = this.entityManager();

  private Usuario buscar(String nombreUsuario, String contrasenia, String nombreTabla) {
    EntityManager entityManager = this.entityManager();
    try {
      return (Usuario) entityManager.createQuery("from " + nombreTabla + " t where t.usuario = "
          + ":nombreDeUsuario and t.contrasenia = :contrasenia")
          .setParameter("nombreDeUsuario", nombreUsuario)
          .setParameter("contrasenia", contrasenia)
          .getSingleResult();
    }  catch (NoResultException e) {
      throw new BusquedaEnBaseDeDatosException("No se ha encontrado un usuario registrado con los "
          + "datos de inicio de sesión aportados. Por favor, ingrese un usuario y contraseña válidos");
      //Por cuestiones de seguridad, no se especifica qué dato (usuario o contraseña) fue incorrecto
    }
  }

  public Administrador buscarAdministrador(String nombreUsuario, String contrasenia) {
    return (Administrador) this.buscar(nombreUsuario, contrasenia, Administrador.class
        .getTypeName());
  }

  public Duenio buscarDuenio(String nombreUsuario, String contrasenia) {
    return (Duenio) this.buscar(nombreUsuario, contrasenia, Duenio.class.getTypeName());
  }

  public Voluntario buscarVoluntario(String nombreUsuario, String contrasenia) {
    return (Voluntario) this.buscar(nombreUsuario, contrasenia, Voluntario.class.getTypeName());
  }

  private <T> Usuario buscarPorId(long id, Class clase) {
    EntityManager entityManager = this.entityManager();
      return (Usuario) entityManager.find(clase, id);
  }

  public Administrador buscarAdministradorPorId(long id) {
    return (Administrador) this.buscarPorId(id, Administrador.class);
  }

  public Duenio buscarDuenioPorId(long id) {
    return (Duenio) this.buscarPorId(id, Duenio.class);
  }

  public Voluntario buscarVoluntarioPorId(long id) {
    return (Voluntario) this.buscarPorId(id, Voluntario.class);
  }

  public void guardarUsuario(Usuario usuario) {
    if (!this.nombreDeUsuarioExistente(usuario.getUsuario(), usuario.getClass().getTypeName())) {
      EntityManager entityManager = this.entityManager();
      entityManager.persist(usuario);
    } else {
      throw new UsuarioYaRegistradoException("Ya existe una persona registrada con el mismo nombre "
          + "de usuario. Por favor, ingrese otro nombre de usuario distinto.");
      /* No se permitirán nombres de usuarios idénticos dentro de un mismo rol. Entre disintos roles podría
      * haber nombres de usuario repetidos.
      * */
    }
  }

  private boolean nombreDeUsuarioExistente(String nombreUsuario, String nombreTabla) {
    EntityManager entityManager = this.entityManager();
    try {
      entityManager.createQuery("from " + nombreTabla
          + " t where t.usuario = :nombreUsuario")
          .setParameter("nombreUsuario", nombreUsuario)
          .getSingleResult();
      return true;
    } catch (NoResultException e) {
      return false;
    }
  }
 
  public Duenio buscarDuenioMedianteUsuario(String nombreUsuario){
    return (Duenio) this.buscarSoloUsuario(nombreUsuario, Duenio.class.getTypeName());
  }

  private Usuario buscarSoloUsuario(String nombreUsuario, String nombreTabla) {
    EntityManager entityManager = this.entityManager();
    try {
      return (Usuario) entityManager.createQuery("from " + nombreTabla + " t where t.usuario = "
          + ":nombreDeUsuario")
          .setParameter("nombreDeUsuario", nombreUsuario)
          .getSingleResult();
    }  catch (NoResultException e) {
      throw new BusquedaEnBaseDeDatosException("No se ha encontrado un usuario registrado con los "
          + "datos de inicio de sesión aportados. Por favor, ingrese un usuario y contraseña válidos");
      //Por cuestiones de seguridad, no se especifica qué dato (usuario o contraseña) fue incorrecto
    }
  }

  public void actualizarUsuario(Usuario usuario) {
    this.entityManager().persist(usuario);
  }

}
