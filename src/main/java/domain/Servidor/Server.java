package domain.Servidor;

import spark.Spark;
import spark.debug.DebugScreen;


public class Server {

  public static void main(String[] args){
    //DebugScreen.enableDebugScreen();
    int puerto = getHerokuAssignedPort();
    System.out.println(puerto);
    Spark.port(puerto);

    Router.getInstance().configure();

    Spark.init();

  }

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      System.out.println("Aguante el ds");
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }

    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }

}
