package pe.torganizagroup.easyhotelapp.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ubigeoCliente {

    private static Retrofit retrofit = null;

    public static Retrofit getUbigeoData(String urlServidor){

        if (retrofit==null){
            Gson gson = new GsonBuilder ()
                    .setDateFormat ("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create ();

            retrofit = new Retrofit.Builder ()
                    .baseUrl (urlServidor)
                    .addConverterFactory (GsonConverterFactory.create ())
                    .build();
        }

        return retrofit;
    }

}
