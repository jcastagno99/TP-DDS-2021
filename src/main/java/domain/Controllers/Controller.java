package domain.Controllers;

import spark.Request;
import spark.Response;

public abstract class Controller {

  private boolean hayCookieDeNombreDeUsuario(Request request) {
    return request.cookie("nombreDeUsuario") != null;
  }

  public void validarExistenciaDeCookieDeNombreDeUsuario(Request request, Response response) {
    if (!this.hayCookieDeNombreDeUsuario(request)) {
      response.redirect("/");
    }
  }
}
