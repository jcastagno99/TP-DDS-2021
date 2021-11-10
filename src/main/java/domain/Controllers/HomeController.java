package domain.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

  public static ModelAndView index(Request request, Response response) {
    return new ModelAndView(null,"home.hbs");
  }

}
