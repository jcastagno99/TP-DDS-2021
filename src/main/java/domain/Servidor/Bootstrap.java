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

  public static final Bootstrap INSTANCE = new Bootstrap();

  public void init(){

    List<Asociacion> asociaciones = asociaciones();
    List<Duenio> usuariosRegistrados = duenios();
    EntityManager entityManager = this.entityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();
    asociaciones.forEach((asociacion) -> RepositorioAsociaciones.instance().agregarAsociacion(asociacion));
    transaction.commit();

    transaction.begin();
    usuariosRegistrados.forEach((duenio -> RepositorioUsuarios.instance().guardarUsuario(duenio)));
    transaction.commit();

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

  private static List<Duenio> duenios(){

    Duenio pablo = new Duenio("pablito27","qsxesz00",asociaciones().get(0),
        "Pablo","Perez", LocalDate.now(),"DNI",111111,new Contacto(15472289,"pablitoPerez@gmail.com"));
    Duenio martin = new Duenio("martinkpo90","753951asd",asociaciones().get(1),
        "Martin","Perez",LocalDate.now(),"DNI",222222,new Contacto(1598874,"MartinPp@gmail.com"));
    return Arrays.asList(pablo,martin);
  }

}
