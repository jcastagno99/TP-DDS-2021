package domain.Asociacion;

import domain.Mascotas.MascotaRegistrada;
import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Roles.Duenio;
import exception.BusquedaEnBaseDeDatosException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioAsociaciones implements WithGlobalEntityManager {

  private static final RepositorioAsociaciones INSTANCE = new RepositorioAsociaciones();

  //private final EntityManager entityManager = this.entityManager(); // TODO Esto puede tirar error

  private List<Pregunta> preguntasObligatorias;

  public static RepositorioAsociaciones instance() {
    return INSTANCE;
  }

  public List<Asociacion> obtenerAsociaciones(){
    EntityManager entityManager = this.entityManager();
    try{
      return entityManager.createQuery("from Asociacion").getResultList();
    } catch (NoResultException e) {
      throw new BusquedaEnBaseDeDatosException("No se encontraron asociaciones cargadas en la base de datos");
    }
  }

  public void agregarAsociacion(Asociacion unaAsociacion) {
    EntityManager entityManager = this.entityManager();
    entityManager.persist(unaAsociacion);
  }

  public Asociacion obtenerAsociacionMasCercaA(UbicacionDeDominio unaUbicacion) {
    return this.obtenerAsociaciones().stream().min(Comparator.comparing(asociacion -> asociacion
        .getUbicacion().calcularDistanciaA(unaUbicacion))).get();
  }

  public Asociacion obtenerAsociacionA_LaQuePerteneceDuenio(Duenio unDuenio) {
    /*return this.obtenerAsociaciones().stream().filter(asociacion -> asociacion.getDueniosRegistrados()
        .contains(unDuenio)).collect(Collectors.toList()).get(0);*/
    EntityManager entityManager = this.entityManager();
    long idAsociacion = unDuenio.getAsociacionId();
    return (Asociacion) entityManager.createQuery("from Asociacion a where a.id = :idAsociacion").setParameter("idAsociacion", idAsociacion).getSingleResult();
    //return unDuenio.getAsociacion();
  }

  public Asociacion obtenerAsociacionPorNombre(String nombre){
    /*return this.obtenerAsociaciones().stream().filter(asociacion
        -> asociacion.getNombreAsociacion().equals(nombre)).collect(Collectors.toList()).get(0);*/
    return (Asociacion) this.entityManager().createQuery("from Asociacion a where a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
  }


  public List<PublicacionMascotaPerdida> getPublicaciones() {
    //TODO aplanar lista de lista de publicaciones de asociaciones
    // Sólo deberian mostrarse los datos no sensibles del rescatista. Luego, cuando alguien
    // decida entrar en contacto con el,ahí sí enviarle los datos por privado
    return null;
  }

  public List<Pregunta> getPreguntasObligatorias() {
    return preguntasObligatorias;
  }

  public Asociacion obtenerAsociacionA_LaQuePerteneceMascota(MascotaRegistrada mascota) {
    /*return this.obtenerAsociaciones().stream().filter(asociacion -> asociacion.getMascotasRegistradas()
        .contains(mascota)).collect(Collectors.toList()).get(0);*/
    EntityManager entityManager = this.entityManager();
    long idAsociacion = mascota.getAsociacionId();
    return (Asociacion) entityManager.createQuery("from Asociacion a where a.id = :idAsociacion").setParameter("idAsociacion", idAsociacion).getSingleResult();
  }
}
