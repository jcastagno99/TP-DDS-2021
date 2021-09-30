package db;

import domain.Roles.Administrador;
import domain.Roles.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.EntityManager;

public class Runner implements WithGlobalEntityManager, TransactionalOps {
  public static void main(String [] args) {
    new Runner().run();
  }

  private void run() {
    this.createEntities();
    this.buscarAdministrador("mabelp", "afsfa656");
  }

  private void buscarAdministrador(String nombreUsuario, String contrasenia) {
    EntityManager entityManager = this.entityManager();
    Administrador admin = (Administrador) entityManager.createQuery("from Administrador a where a.usuario = :nombreDeUsuario and a.contrasenia = :contrasenia")
        .setParameter("nombreDeUsuario", nombreUsuario)
        .setParameter("contrasenia", contrasenia)
        .getSingleResult();
    System.out.println(admin.getApellido());
    //Administrador admin = RepositorioUsuarios.instance().buscarAdministrador(nombreUsuario, contrasenia);
    //System.out.println(admin.getApellido());
  }

  private void createEntities() {
    withTransaction(() -> {
      Administrador administrador = new Administrador("mabelp", "afsfa656", null, "Mabel", "Perez");
      Administrador administrador2 = new Administrador("pedroAbelardo", "pepeabel89522", null, "Pedro", "Fernandez");

      entityManager().persist(administrador);
      entityManager().persist(administrador2);
    });
  }
}
