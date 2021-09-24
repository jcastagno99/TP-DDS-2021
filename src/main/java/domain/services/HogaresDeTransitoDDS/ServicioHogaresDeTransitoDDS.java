package domain.services.HogaresDeTransitoDDS;

import domain.Mascotas.MascotaPerdidaSinChapita;
import domain.Asociacion.UbicacionDeDominio;
import exception.ConsultaDeHogaresDeTransitoException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import domain.services.HogaresDeTransitoDDS.entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioHogaresDeTransitoDDS {
  private static final ServicioHogaresDeTransitoDDS INSTANCE = new ServicioHogaresDeTransitoDDS();
  private static final String urlApi = "https://api.refugiosdds.com.ar/api";
  private Retrofit retrofit;
  //private ListadoDeHogares ultimaConsulta;

  private ServicioHogaresDeTransitoDDS() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  //  ultimaConsulta = null;
  }

  public static ServicioHogaresDeTransitoDDS instance() {
    return INSTANCE;
  }

  // Este método queda por si implementáramos un caché de consultas
  private ListadoDeHogares listadoDeHogares() {
    HogarDeTransitoDDSService hogarDeTransitoDDSService = this.retrofit.create(HogarDeTransitoDDSService.class);
    Call<ListadoDeHogares> requestHogares = hogarDeTransitoDDSService.hogares();
    Response<ListadoDeHogares> responseHogares;

    try {
      responseHogares = requestHogares.execute();
      return responseHogares.body();
    } catch (IOException e) {
      //e.printStackTrace();
      throw new ConsultaDeHogaresDeTransitoException("Se ha producido un error en la consulta a los hogares de tránsito disponibles");
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

  // TODO de alguna manera vamos a tener que conseguir la mascota para la cual se le va a buscar un hogar
  public List<Hogar> filtrar(int radioDeCercania, MascotaPerdidaSinChapita mascotaPerdida, String ... caracteristicas) {
    List<Hogar> hogaresBase = this.obtenerHogaresBase(radioDeCercania, mascotaPerdida);

    /* Necesitamos tres listas:
     * 1. Hogares potenciales (que cumplen con la base + disponibilidad y aca no miro las caracteristicas etxras, pueden o no tener)
     * 2. Si tengo caracteristas extras para ofrecer yo como mascota -> filtro HP por mis CE
     * 3. Si no tengo caracteristicas extras, filtro HP por aquellos que no tienen requerimientos extras
     * */

    List<String> caracteristicasParaFiltrado = new ArrayList<>();
    Collections.addAll(caracteristicasParaFiltrado, caracteristicas);
    if(!caracteristicasParaFiltrado.isEmpty())
      return this.filtrarPorCaracteristicasAdicionales(caracteristicasParaFiltrado, hogaresBase);
    else {
      return this.filtrarSinRequerimientosExtras(hogaresBase);
    }
  }

  private List<Hogar> filtrarSinRequerimientosExtras(List<Hogar> hogares) {
    return hogares.stream().filter(hogar -> !hogar.tieneRequerimientosExtras()).collect(Collectors.toList());
  }

  private List<Hogar> filtrarPorCaracteristicasAdicionales(List<String> caracteristicasParaFiltrado, List<Hogar> hogares) {
    return hogares.stream().filter(hogar -> hogar.tiene(caracteristicasParaFiltrado)).collect(Collectors.toList());
  }

  private List<Hogar> obtenerHogaresBase(int radioDeCercania, MascotaPerdidaSinChapita mascotaPerdida) {
    // Retorna hogares a los que puede entrar por especie, tamaño y disponibilidad
    // TODO lo estamos hardcodeando pq no creemos que pueda ser implementable ahora
    UbicacionDeDominio mockUbicacion = new UbicacionDeDominio(12,13);
    return this.listarHogares().stream().filter(hogar -> hogar.estaDentroDelRadio(mockUbicacion, radioDeCercania) && hogar.puedeAdmitirMascota(mascotaPerdida)).collect(Collectors.toList());
  }
}