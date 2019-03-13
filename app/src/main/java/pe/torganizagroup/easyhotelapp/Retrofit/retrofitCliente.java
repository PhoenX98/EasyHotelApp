package pe.torganizagroup.easyhotelapp.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitCliente {

    private static Retrofit retrofit = null;

    public static Retrofit getCliente(String urlServidorRest) {

        if (retrofit == null){

            Gson gson = new GsonBuilder ()
                    .setDateFormat ("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create ();

            retrofit = new Retrofit.Builder ()
                    .baseUrl (urlServidorRest)
                    .addConverterFactory (GsonConverterFactory.create (gson))
                    .build ();

        }

        return retrofit;
    }

}