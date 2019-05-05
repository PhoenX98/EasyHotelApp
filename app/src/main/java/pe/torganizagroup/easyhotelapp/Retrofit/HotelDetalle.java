package pe.torganizagroup.easyhotelapp.Retrofit;

import java.util.List;

import pe.torganizagroup.easyhotelapp.Pojo.GenHot;
import pe.torganizagroup.easyhotelapp.Pojo.HotelDetails;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HotelDetalle {

    @GET("hotelsDetails")
    Call<List<HotelDetails>> getHotelDetalle(@Query ("hotelId") String HotelId);

}
