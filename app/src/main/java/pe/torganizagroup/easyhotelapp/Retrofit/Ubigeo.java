package pe.torganizagroup.easyhotelapp.Retrofit;

import java.util.List;

import pe.torganizagroup.easyhotelapp.Pojo.UbigeoPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Ubigeo {

    //Solo departamentos, distrito y provincia deben ser "00"
    @GET("ubigeo")
    Call<List<UbigeoPojo>> getDepartamentos(@Query("distrito") String dist, @Query ("provincia") String prov);

    @GET("ubigeo")
    Call<List<UbigeoPojo>> getProvincias(@Query ("departamento") String dep);
}
