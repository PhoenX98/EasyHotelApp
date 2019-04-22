package pe.torganizagroup.easyhotelapp.Retrofit;


import pe.torganizagroup.easyhotelapp.Pojo.CoordenadaRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoordenadaService {

    //coordenadas
    @GET("ubicacion")
    Call<CoordenadaRespuesta> obtenerMarcadores();



}
