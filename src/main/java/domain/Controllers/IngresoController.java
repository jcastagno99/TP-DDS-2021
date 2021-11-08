package domain.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IngresoController {

  public ModelAndView controlarIngreso(Request request, Response response) {

    return new ModelAndView(null, "YaTeRegistraste.hbs");
  }

}
