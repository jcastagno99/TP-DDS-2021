package domain.services.RefugiosDDS;


public class ServicioRefugioDDS {
  private static ServicioRefugioDDS instancia = null;
  private static final String urlApi = "https://api.refugiosdds.com.ar/api";
  //private Retrofit retrofit;

  /*private ServicioRefugioDDS() {
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
  }*/
}
