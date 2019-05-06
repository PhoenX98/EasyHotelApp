package pe.torganizagroup.easyhotelapp.Retrofit;

import java.util.List;

import pe.torganizagroup.easyhotelapp.Pojo.UbigeoPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Ubigeo {


    @GET("ubigeo")
    Call<List<UbigeoPojo>> getFilter(@Query("name") String result);

}
