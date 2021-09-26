package domain.services.HogaresDeTransitoDDS;

import domain.services.HogaresDeTransitoDDS.entities.ListadoDeHogares;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HogarDeTransitoDdsService {
  @GET("hogares")
  Call<ListadoDeHogares> hogares();
  //TODO resolver consulta total de hogares y el tema de logearse para que traiga
  //todas los hogares distintos y no solo el primero de prueba
}
