package pe.torganizagroup.easyhotelapp.Retrofit;

import pe.torganizagroup.easyhotelapp.Pojo.LocalRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalService {

    @GET("local")
    Call<LocalRespuesta> obtenerLocales(@Query("current_page") int page);
}