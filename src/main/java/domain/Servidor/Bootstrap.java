package domain.Servidor;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import domain.Asociacion.UbicacionDeDominio;
import domain.Roles.*;
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
  EntityManager entityManager = this.entityManager();
  EntityTransaction transaction = entityManager.getTransaction();

  public void init(){

    this.guardarAsociaciones();

    this.guardarUsuarios();

    this.registrarUsuarios();
/*
    Asociacion asociacion = (Asociacion) entityManager.createQuery("from Asociacion a where a.nombreAsociacion = 'elCampito'").getSingleResult();

    transaction.begin();
    entityManager.remove(asociacion);
    transaction.commit();*/
  }

  private void guardarAsociaciones() {
    transaction.begin();
    asociaciones().forEach((asociacion) -> RepositorioAsociaciones.instance().agregarAsociacion(asociacion));
    transaction.commit();
  }

  private void guardarUsuarios() {
    transaction.begin();
    List<Usuario> usuariosRegistrados = this.usuarios();
    usuariosRegistrados.forEach((duenio -> RepositorioUsuarios.instance().guardarUsuario(duenio)));
    transaction.commit();
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
    conexionAnimal.agregarCaracteristicasA_Mascotas("pelaje");
    conexionAnimal.agregarCaracteristicasA_Mascotas("raza");
    conexionAnimal.agregarCaracteristicasA_Mascotas("color");
    return Arrays.asList(elCampito,patitas,vidaAnimal, conexionAnimal);
  }

  private List<Usuario> usuarios(){
    Asociacion conexionAnimalDeBD = (Asociacion) entityManager().createQuery("from Asociacion a where a.nombreAsociacion = 'Conexión animal'").getSingleResult();
    Duenio pablo = new Duenio("pablito27","qsxesz00",/*elCampitoDeBD,*/
        "Pablo","Perez", LocalDate.now(),"DNI",111111,new Contacto(15472289,"pablitoPerez@gmail.com"));
    Duenio martin = new Duenio("martinkpo90","753951asd",/*patitasDeBD,*/
        "Martin","Perez",LocalDate.now(),"DNI",222222,new Contacto(1598874,"MartinPp@gmail.com"));
    Duenio juan = new Duenio("juan", "matias1234", /*conexionAnimalDeBD,*/ "Juan", "Martínez", LocalDate.now(), "DNI",455445, new Contacto(5455, "asdas@asads"));

    Administrador admin = new Administrador("soyadmin", "eladmin1234", conexionAnimalDeBD, "El admin", "istrador");
    return Arrays.asList(pablo,martin, juan, admin);
  }

  private void registrarUsuarios() {
    Asociacion elCampitoDeBD = (Asociacion) entityManager().createQuery("from Asociacion a where a.nombreAsociacion = 'elCampito'").getSingleResult();
    Asociacion patitasDeBD = (Asociacion) entityManager().createQuery("from Asociacion a where a.nombreAsociacion = 'patitas'").getSingleResult();
    Asociacion conexionAnimalDeBD = (Asociacion) entityManager().createQuery("from Asociacion a where a.nombreAsociacion = 'Conexión animal'").getSingleResult();

    Duenio pablo = RepositorioUsuarios.instance().buscarDuenio("pablito27", "qsxesz00");
    Duenio martin = RepositorioUsuarios.instance().buscarDuenio("martinkpo90", "753951asd");
    Duenio juan = RepositorioUsuarios.instance().buscarDuenio("juan", "matias1234");

    elCampitoDeBD.registrarDuenio(pablo);
    patitasDeBD.registrarDuenio(martin);
    conexionAnimalDeBD.registrarDuenio(juan);

    transaction.begin();
    RepositorioAsociaciones.instance().agregarAsociacion(elCampitoDeBD);
    RepositorioAsociaciones.instance().agregarAsociacion(patitasDeBD);
    RepositorioAsociaciones.instance().agregarAsociacion(conexionAnimalDeBD);
    transaction.commit();
  }

}