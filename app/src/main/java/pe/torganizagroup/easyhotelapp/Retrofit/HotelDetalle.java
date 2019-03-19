package pe.torganizagroup.easyhotelapp.Retrofit;

import java.util.List;

import pe.torganizagroup.easyhotelapp.Pojo.GenHot;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HotelDetalle {

    @GET("")
    Call<List<GenHot>> getDetalle();
}
