package domain.services.RefugiosDDS;

import domain.Mascotas.MascotaPerdida;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import domain.services.RefugiosDDS.entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

  private ListadoDeHogares listadoDeHogares() throws IOException {
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
    return listadoDeHogares().getHogares();
  }

  // TODO de alguna manera vamos a tener que conseguir la mascota para la cual se le va a buscar un hogar
  public List<Hogar> filtrar(MascotaPerdida mascotaPerdida, String ... caracteristicas) throws IOException {
    List<Hogar> hogaresBase = this.obtenerHogaresBase(mascotaPerdida);

    /* Necesitamos tres listas:
     * 1. Hogares potenciales (que cumplen con la base + disponibilidad y aca no miro las caracteristicas etxras, pueden o no tener)
     * 2. Si tengo caracteristas extras para ofrecer yo como mascota -> filtro HP por mis CE
     * 3. Si no tengo caracteristicas extras, filtro HP por aquellos que no tienen requerimientos extras
     * */

    List<String> caracteristicasParaFiltrado = new ArrayList<>();
    Collections.addAll(caracteristicasParaFiltrado, caracteristicas);
    if(!caracteristicasParaFiltrado.isEmpty())
      return this.filtrarPorCaracteristicasAdicionales(caracteristicasParaFiltrado, hogaresBase);
    else
    {
      return this.filtrarSinRequerimientosExtras(hogaresBase);
    }
  }

  private List<Hogar> filtrarSinRequerimientosExtras(List<Hogar> hogares) {
    return hogares.stream().filter(hogar -> !hogar.tieneRequerimientosExtras()).collect(Collectors.toList());
  }

  private List<Hogar> filtrarPorCaracteristicasAdicionales(List<String> caracteristicasParaFiltrado, List<Hogar> hogares){
    return hogares.stream().filter(hogar -> hogar.tiene(caracteristicasParaFiltrado)).collect(Collectors.toList());
  }

  private List<Hogar> obtenerHogaresBase(MascotaPerdida mascotaPerdida) throws IOException{
    // Retorna hogares a los que puede entrar por especie, tamaño y disponibilidad
    return this.listarHogares().stream().filter(hogar -> hogar.puedeAdmitirMascota(mascotaPerdida)).collect(Collectors.toList());
}

}