package domain.Controllers;

import domain.MascotasPerdidasManagement.RepositorioMascotasPerdidas;
import domain.Publicaciones.PublicacionMascotaPerdida;
import domain.Publicaciones.RepositorioDePublicaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicacionesController {
  public ModelAndView mostrarPublicacionesDeMascotasPerdidas(Request request, Response response) {
    List<PublicacionMascotaPerdida> publicacionMascotaPerdidas = RepositorioDePublicaciones.instance().obtenerPublicacionesDeMascotasPerdidas();
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("publicaciones", publicacionMascotaPerdidas);
    return new ModelAndView(modelo, "publicacionesDeMascotasPerdidas.hbs");
  }
}
