package pe.torganizagroup.easyhotelapp.Retrofit;

import java.util.ArrayList;
import java.util.List;

import pe.torganizagroup.easyhotelapp.Pojo.GenHot;
import pe.torganizagroup.easyhotelapp.Pojo.GenHotRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LocalTest {

    @GET("listas")
    Call<List<GenHot>> getlocal();

}
