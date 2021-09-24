package domain.services.HogaresDeTransitoDDS;

import domain.services.HogaresDeTransitoDDS.entities.ListadoDeHogares;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HogarDeTransitoDDSService {
  @GET("hogares")
  Call<ListadoDeHogares> hogares();
  //TODO resolver consulta total de hogares

}
