package domain.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotasController {

  public ModelAndView mostrarFormDeRegistro(Request request, Response response){
    return new ModelAndView(null, "pantallaRegistrarMascota.hbs");
  }

  public ModelAndView registrarMascota(Request request, Response response) {
    return new ModelAndView(null, null);
  }
}
