package domain.services.RefugiosDDS;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioRefugioDDS {
  private static ServicioRefugioDDS instancia = null;
  private static final String urlApi = "https://api.refugiosdds.com.ar/api";
  private Retrofit retrofit;

  private ServicioRefugioDDS() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static ServicioRefugioDDS instancia(){
    if(instancia== null){
      instancia = new ServicioRefugioDDS();
    }
    return instancia;
  }
}