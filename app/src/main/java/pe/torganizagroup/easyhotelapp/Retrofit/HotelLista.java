package pe.torganizagroup.easyhotelapp.Retrofit;

import java.util.List;

import pe.torganizagroup.easyhotelapp.Pojo.GenHot;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HotelLista {

//    @GET("hotels/find?status=1")
//    Call<List<Hotels>> getHotels();
    @GET("hotels")
    Call<List<Hotels>> getHotels();

    @GET("hotels")
    Call<List<Hotels>> getFilterHotels(@Query ("district") String result);

}
