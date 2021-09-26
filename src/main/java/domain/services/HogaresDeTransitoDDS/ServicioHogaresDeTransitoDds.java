package domain.services.HogaresDeTransitoDDS;

import domain.Asociacion.UbicacionDeDominio;
import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.services.HogaresDeTransitoDDS.entities.Hogar;
import domain.services.HogaresDeTransitoDDS.entities.ListadoDeHogares;
import exception.ConsultaDeHogaresDeTransitoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServicioHogaresDeTransitoDds {
  private static final ServicioHogaresDeTransitoDds INSTANCE = new ServicioHogaresDeTransitoDds();
  private static final String urlApi = "https://api.refugiosdds.com.ar/api";
  private Retrofit retrofit;

  //private ListadoDeHogares ultimaConsulta;

  private ServicioHogaresDeTransitoDds() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    //  ultimaConsulta = null;
  }

  public static ServicioHogaresDeTransitoDds instance() {
    return INSTANCE;
  }

  // Este método queda por si implementáramos un caché de consultas
  private ListadoDeHogares listadoDeHogares() {
    HogarDeTransitoDdsService hogarDeTransitoDdsService = this.retrofit.create(HogarDeTransitoDdsService.class);
    Call<ListadoDeHogares> requestHogares = hogarDeTransitoDdsService.hogares();
    Response<ListadoDeHogares> responseHogares;

    try {
      responseHogares = requestHogares.execute();
      return responseHogares.body();
    } catch (IOException e) {
      //e.printStackTrace();
      throw new ConsultaDeHogaresDeTransitoException(
          "Se ha producido un error en la consulta a los hogares de tránsito disponibles");
    }

  }

  //este método serviria para cachear, se agregaria condicion allado del null
  /*public List<Hogar> listarHogares() throws IOException{
    if(ultimaConsulta==null) listadoDeHogares();
    return ultimaConsulta.hogares;
  }*/

  public List<Hogar> listarHogares() {
    return listadoDeHogares().getHogares();
  }

  public List<Hogar> filtrar(UbicacionDeDominio ubicacionDePartida, int radioDeCercania,
                             MascotaPerdidaSinChapita mascotaPerdida, String ... caracteristicas) {
    List<Hogar> hogaresBase = this.obtenerHogaresBase(ubicacionDePartida, radioDeCercania);
    List<String> caracteristicasParaFiltrado = new ArrayList<>();
    Collections.addAll(caracteristicasParaFiltrado, caracteristicas);
    return hogaresBase.stream().filter(hogar ->
        hogar.admitePorCriterios(mascotaPerdida,
            caracteristicasParaFiltrado)).collect(Collectors.toList());
  }

  private List<Hogar> obtenerHogaresBase(UbicacionDeDominio ubicacionDePartida,
      int radioDeCercania) {
    return this.listarHogares().stream().filter(hogar ->
        hogar.estaDentroDelRadio(ubicacionDePartida, radioDeCercania)
            && hogar.hayCapacidad()).collect(Collectors.toList());
  }
}


