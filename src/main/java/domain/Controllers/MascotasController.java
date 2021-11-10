package domain.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotasController {

  public ModelAndView mostrarFormDeRegistro(Request request, Response response){
    return new ModelAndView(null, "pantallaRegistrarMascota.hbs");
  }

  public ModelAndView registrarMascota(Request request, Response response) {
    /*
    *
    * ACÁ SE HARÁ LA PERSISTENCIA
    *
    *
    * */
    return new ModelAndView(null, null); // redirigir a segundo hbs imprimiento mensaje de registro corecto
  }
}
