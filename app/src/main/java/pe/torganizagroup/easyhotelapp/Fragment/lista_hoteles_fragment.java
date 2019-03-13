package pe.torganizagroup.easyhotelapp.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import pe.torganizagroup.easyhotelapp.Adapters.GenHotAdapter;
import pe.torganizagroup.easyhotelapp.Adapters.HotelAdapter;
import pe.torganizagroup.easyhotelapp.Pojo.GenHot;
import pe.torganizagroup.easyhotelapp.Pojo.GenHotRespuesta;
import pe.torganizagroup.easyhotelapp.Pojo.Hotel;
import pe.torganizagroup.easyhotelapp.Pojo.LocalRespuesta;
import pe.torganizagroup.easyhotelapp.R;
import pe.torganizagroup.easyhotelapp.Retrofit.LocalService;
import pe.torganizagroup.easyhotelapp.Retrofit.LocalTest;
import pe.torganizagroup.easyhotelapp.Retrofit.Utilidades;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pe.torganizagroup.easyhotelapp.Retrofit.Utilidades.NEW_TEST_URL;

public class lista_hoteles_fragment extends Fragment {

    private static final  String TAG = "Locales";
    private static final String TAG_ERROR = "Debug: ";

    private RecyclerView recyclerView;
    private HotelAdapter listaHotelesAdapter;
    private GenHotAdapter lhAdapter;
    private ArrayList<Hotel> listaHotel = new ArrayList<> ();
    private Retrofit retrofit,retrofit2;
    private List<GenHot> lH = new ArrayList<> ();

    private LocalService localService;
    private LocalTest localTest;

    private int pagina;
    private boolean aptoParaCargar;

    public lista_hoteles_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        localTest = Utilidades.getService ();

//        retrofit = new Retrofit.Builder ()
//                .baseUrl (NEW_TEST_URL)
//                .addConverterFactory (GsonConverterFactory.create ())
//                .build ();

//        aptoParaCargar = true;

//        pagina = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_lista_hoteles, container, false);

        recyclerView = view.findViewById (R.id.ListaFullHotel);
//        listaHotelesAdapter = new HotelAdapter (getContext ());
        lhAdapter = new GenHotAdapter (getContext (),lH);
        recyclerView.setAdapter (lhAdapter);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager (getActivity ());
        recyclerView.setLayoutManager (linearLayoutManager);
        recyclerView.setHasFixedSize (true);
        recyclerView.setItemAnimator (new DefaultItemAnimator ());

//
//        recyclerView.addOnScrollListener (new RecyclerView.OnScrollListener () {
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled (recyclerView, dx, dy);
//                //parametros para pasar a cargar siguiente pagina del recyclerview
//                if (dy > 0) {
//                    int visibleItemCount = linearLayoutManager.getChildCount ();
//                    int totalItemCount = linearLayoutManager.getItemCount ();
//                    int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition ();
//
//                    if (aptoParaCargar) {
//                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
//                            Log.i(TAG, "Llegamos al final de la pagina " +pagina+" del Json, pasando a siguiente...");
//
//                            aptoParaCargar = false;
//                            pagina += 1;
//                            obtenerDatos ();
//                        }
//                    }
//                }
//
//            }
//        });

        return view;
    }

    private void obtenerDatos() {

        Call<List<GenHot>> call = localTest.getlocal ();

        call.enqueue (new Callback<List<GenHot>> () {
            @Override
            public void onResponse(Call<List<GenHot>> call, Response<List<GenHot>> response) {
                if(response.isSuccessful ()){
                    try {

                        List<GenHot> hotels = response.body ();

                        for (GenHot h: hotels){
                            Log.d ("name", String.valueOf (h.getNombreLocal ()));
                            Log.d ("direccion", String.valueOf (h.getDireccion ()));
                            Log.d ("distrito", String.valueOf (h.getDistrito ()));
                            Log.d ("imagen", String.valueOf (h.getNombreLocal ()));
                        }


//                        String lista = response.body().toString ();
//                        Type listType = new TypeToken<List<GenHot>> () {}.getType ();
////                        List<GenHot> hot = new Gson().fromJson (jsonString, type);
//                        lH = getTemListFromJson(lista, listType);
////                        recyclerView.setAdapter (new HotelAdapter (getAplicationContext(), lH));
//                        lhAdapter = new GenHotAdapter (getContext (),lH);
//                        recyclerView.setAdapter (lhAdapter);


                    } catch (Exception e){
                        Log.d (TAG_ERROR, "Hay un error");
                        e.printStackTrace ();
                    }
                } else {
                    Log.i (TAG,"El metodo try ha fallado: " + response.errorBody ());
                }
            }

            @Override
            public void onFailure(Call<List<GenHot>> call, Throwable t) {
                Log.i (TAG_ERROR,"Error en el parseo de JSON, redefinir parametros"+t.getMessage ());
                t.printStackTrace ();
            }

        });

//        Call<LocalRespuesta> call = localService.obtenerLocales (pagina);
//
//        call.enqueue (new Callback<LocalRespuesta> () {
//            @Override
//            public void onResponse(Call<LocalRespuesta> call, Response<LocalRespuesta> response) {
//                listaHotel.clear ();
//                aptoParaCargar=true;
//                if (response.isSuccessful ()) {
//                    try {
//
//                        LocalRespuesta localRespuesta = response.body ();
//                        listaHotel = Objects.requireNonNull (localRespuesta).getData ();
//                        listaHotelesAdapter.adicionarListaHoteles (listaHotel);
//
//                    } catch (Exception e){
//                        Log.d (TAG_ERROR, "Hay un error");
//                        e.printStackTrace ();
//                    }
//                    listaHotelesAdapter.notifyDataSetChanged ();
//
//                } else {
//                    Log.i (TAG,"El metodo try ha fallado: " + response.errorBody ());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LocalRespuesta> call, Throwable t) {
//                aptoParaCargar=true;
//                Log.i (TAG,"Hay un error en la respuesta: " + t.getMessage ());
//            }
//        });

    }

    public static <T> List <T> getTemListFromJson(String jsonString, Type type){
        if (!isValid(jsonString)) {
            return null;
        }
        return new Gson ().fromJson (jsonString, type);
    }

    public static boolean isValid(String json) {
        try{
            new JsonParser ().parse (json);
            return true;
        }catch (JsonSyntaxException jse){
            return false;
        }
    }

    @Override
    public void onStart(){
        super.onStart ();
        obtenerDatos();
    }
}
