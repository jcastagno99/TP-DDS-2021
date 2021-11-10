package domain.Servidor;
import domain.Asociacion.Asociacion;
import domain.Roles.Contacto;
import domain.Roles.Duenio;
import domain.Roles.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import spark.Spark;
import spark.debug.DebugScreen;

import java.time.LocalDate;


public class Server {

  public static void main(String[] args){
    DebugScreen.enableDebugScreen();
    //Bootstrap.INSTANCE.init();
    Spark.port(9000);

    Router.getInstance().configure();

    Spark.init();
  }
}
