package domain.Servidor;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Asociacion.UbicacionDeDominio;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;


public class Bootstrap implements WithGlobalEntityManager {

  public static final Bootstrap INSTANCE = new Bootstrap();

  public void init(){

    List<Asociacion> asociaciones = asociaciones();
    EntityManager entityManager = this.entityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();
    asociaciones.forEach((asociacion) -> RepositorioAsociaciones.instance().agregarAsociacion(asociacion));
    transaction.commit();

    System.out.println("Asociaciones persistidas: ");
    RepositorioAsociaciones.instance().obtenerAsociaciones().forEach(unaAsociacion -> System.out.println(unaAsociacion.toString()));

  }

  private static List<Asociacion> asociaciones() {
    UbicacionDeDominio ubiElCampito = new UbicacionDeDominio(55, 60);
    UbicacionDeDominio ubiPatitas = new UbicacionDeDominio(30, 30);
    UbicacionDeDominio ubiVidaAnimal = new UbicacionDeDominio(200, 210);
    Asociacion elCampito =  new Asociacion(ubiElCampito);
    Asociacion patitas =  new Asociacion(ubiPatitas);
    Asociacion vidaAnimal = new Asociacion(ubiVidaAnimal);
    return Arrays.asList(elCampito,patitas,vidaAnimal);
  }

}
