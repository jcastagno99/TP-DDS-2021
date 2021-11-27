package domain.Servidor;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Asociacion.UbicacionDeDominio;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class Bootstrap implements WithGlobalEntityManager {

  public static void main(String args[]){
    new Bootstrap().init();
  }

  public void init(){

    List<Asociacion> asociaciones = this.asociaciones();

    EntityManager entityManager = this.entityManager();
    EntityTransaction transaction = entityManager.getTransaction();
/*
    transaction.begin();
    asociaciones.forEach((asociacion) -> RepositorioAsociaciones.instance().agregarAsociacion(asociacion));
    transaction.commit();
    List<Duenio> usuariosRegistrados = this.duenios();
    transaction.begin();
    usuariosRegistrados.forEach((duenio -> RepositorioUsuarios.instance().guardarUsuario(duenio)));
    transaction.commit();*/
/*
    Asociacion asociacion = (Asociacion) entityManager.createQuery("from Asociacion a where a.nombreAsociacion = 'elCampito'").getSingleResult();

    transaction.begin();
    entityManager.remove(asociacion);
    transaction.commit();

    transaction.begin();
    entityManager.persist(asociacion);
    entityManager.persist(pablo);
    transaction.commit();*/


  }

  private List<Asociacion> asociaciones() {
    UbicacionDeDominio ubiElCampito = new UbicacionDeDominio(55, 60);
    UbicacionDeDominio ubiPatitas = new UbicacionDeDominio(30, 30);
    UbicacionDeDominio ubiVidaAnimal = new UbicacionDeDominio(200, 210);
    UbicacionDeDominio ubiConexionAnimal = new UbicacionDeDominio(566,5454);
    Asociacion elCampito =  new Asociacion(ubiElCampito,"elCampito");
    Asociacion patitas =  new Asociacion(ubiPatitas,"patitas");
    Asociacion vidaAnimal = new Asociacion(ubiVidaAnimal,"vidaAnimal");
    Asociacion conexionAnimal = new Asociacion(ubiConexionAnimal, "Conexión animal");
    return Arrays.asList(elCampito,patitas,vidaAnimal, conexionAnimal);
  }

  private List<Duenio> duenios(){

    Asociacion elCampitoDeBD = (Asociacion) entityManager().createQuery("from Asociacion a where a.nombreAsociacion = 'elCampito'").getSingleResult();
    Asociacion patitasDeBD = (Asociacion) entityManager().createQuery("from Asociacion a where a.nombreAsociacion = 'patitas'").getSingleResult();
    Asociacion conexionAnimalDeBD = (Asociacion) entityManager().createQuery("from Asociacion a where a.nombreAsociacion = 'Conexión animal'").getSingleResult();


    Duenio pablo = new Duenio("pablito27","qsxesz00",elCampitoDeBD,
        "Pablo","Perez", LocalDate.now(),"DNI",111111,new Contacto(15472289,"pablitoPerez@gmail.com"));
    Duenio martin = new Duenio("martinkpo90","753951asd",patitasDeBD,
        "Martin","Perez",LocalDate.now(),"DNI",222222,new Contacto(1598874,"MartinPp@gmail.com"));
    Duenio juan = new Duenio("juan", "matias1234", conexionAnimalDeBD, "Juan", "Martínez", LocalDate.now(), "DNI",455445, new Contacto(5455, "asdas@asads"));
    return Arrays.asList(pablo,martin, juan);
  }

}
