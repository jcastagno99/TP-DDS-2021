package domain.services.RefugiosDDS;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import domain.services.RefugiosDDS.entities.*;

import java.io.IOException;
import java.util.List;

public class ServicioRefugioDDS {
  private static final ServicioRefugioDDS INSTANCE = new ServicioRefugioDDS();
  private static final String urlApi = "https://api.refugiosdds.com.ar/api";
  private Retrofit retrofit;
  //private ListadoDeHogares ultimaConsulta;


  private ServicioRefugioDDS() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  //  ultimaConsulta = null;
  }

  public static ServicioRefugioDDS instance() {
    return INSTANCE;
  }

  public ListadoDeHogares listadoDeHogares() throws IOException {
    RefugioDDSService refugioDDSService = this.retrofit.create(RefugioDDSService.class);
    Call<ListadoDeHogares> requestHogares = refugioDDSService.hogares();
    Response<ListadoDeHogares> responseHogares = requestHogares.execute();
    return responseHogares.body();
  }

  //este método serviria para cachear, se agregaria condicion allado del null
  /*public List<Hogar> listarHogares() throws IOException{
    if(ultimaConsulta==null) listadoDeHogares();
    return ultimaConsulta.hogares;
  }*/

  public List<Hogar> listarHogares() throws IOException {
    return listadoDeHogares().hogares;
  }
}