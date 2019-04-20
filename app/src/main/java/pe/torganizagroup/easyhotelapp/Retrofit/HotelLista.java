package pe.torganizagroup.easyhotelapp.Retrofit;

import java.util.List;

import pe.torganizagroup.easyhotelapp.Pojo.GenHot;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HotelLista {

    @GET("listas")
    Call<List<GenHot>> getlocal();

    @GET("hotels")
    Call<List<Hotels>> getHotels();

}
