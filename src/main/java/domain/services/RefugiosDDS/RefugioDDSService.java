package domain.services.RefugiosDDS;

import domain.services.RefugiosDDS.entities.ListadoDeHogares;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RefugioDDSService {
  @GET("hogares")
  Call<ListadoDeHogares> hogares();
}
