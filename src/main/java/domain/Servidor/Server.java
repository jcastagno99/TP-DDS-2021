package domain.Servidor;

import spark.Spark;
import spark.debug.DebugScreen;



public class Server {

  public static void main(String[] args){
    DebugScreen.enableDebugScreen();
    //Bootstrap.INSTANCE.init();
    Spark.port(9000);

    Router.getInstance().configure();

    Spark.init();
  }
}
