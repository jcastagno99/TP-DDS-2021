package domain.Controllers;

import domain.Asociacion.Asociacion;
import domain.Asociacion.RepositorioAsociaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {


  public static ModelAndView index(Request request, Response response) {
    return new ModelAndView("lo que necesito para generar la view","home.hdbs");
  }

}
