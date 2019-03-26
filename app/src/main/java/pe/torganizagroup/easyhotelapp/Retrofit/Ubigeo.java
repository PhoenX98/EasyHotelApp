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

    //Departamento cambia de valor, distroto debe ser "00"
    @GET("ubigeo")
    Call<List<UbigeoPojo>> getProvincias(@Query ("departamento") String dep, @Query ("Distrito") String dis);

    //Departamento y provincia deben cambiar de valor dependiendo de la eleccion anterior
    @GET("ubigeo")
    Call<List<UbigeoPojo>> getDistritos(@Query ("departamento") String dep, @Query ("Provincia") String pro);
}
