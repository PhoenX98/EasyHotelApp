package pe.torganizagroup.easyhotelapp.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import pe.torganizagroup.easyhotelapp.Adapters.HotelAdapter;
import pe.torganizagroup.easyhotelapp.Pojo.Hotel;
import pe.torganizagroup.easyhotelapp.Pojo.LocalRespuesta;
import pe.torganizagroup.easyhotelapp.R;
import pe.torganizagroup.easyhotelapp.Retrofit.LocalService;
import pe.torganizagroup.easyhotelapp.Retrofit.Utilidades;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class lista_hoteles_fragment extends Fragment {

    private static final  String TAG = "Locales";
    private static final String TAG_ERROR = "Debug: ";

    private RecyclerView recyclerView;
    private HotelAdapter listaHotelesAdapter;
    private ArrayList<Hotel> listaHotel = new ArrayList<> ();
    private Retrofit retrofit;
    private LocalService localService;

    private int pagina;
    private boolean aptoParaCargar;

    public lista_hoteles_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        localService = Utilidades.getService ();

        aptoParaCargar = true;
        obtenerDatos();
        pagina = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_lista_hoteles, container, false);

        recyclerView = view.findViewById (R.id.ListaFullHotel);
        listaHotelesAdapter = new HotelAdapter (getContext ());
        recyclerView.setAdapter (listaHotelesAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager (getActivity ());
        recyclerView.setLayoutManager (linearLayoutManager);
        recyclerView.setHasFixedSize (true);

        recyclerView.addOnScrollListener (new RecyclerView.OnScrollListener () {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled (recyclerView, dx, dy);
                //parametros para pasar a cargar siguiente pagina del recyclerview
                if (dy > 0) {
                    int visibleItemCount = linearLayoutManager.getChildCount ();
                    int totalItemCount = linearLayoutManager.getItemCount ();
                    int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition ();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, "Llegamos al final de la pagina " +pagina+" del Json, pasando a siguiente...");

                            aptoParaCargar = false;
                            pagina += 1;
                            obtenerDatos ();
                        }
                    }
                }

            }
        });

        return view;
    }

    private void obtenerDatos() {

        Call<LocalRespuesta> call = localService.obtenerLocales (pagina);

        call.enqueue (new Callback<LocalRespuesta> () {
            @Override
            public void onResponse(Call<LocalRespuesta> call, Response<LocalRespuesta> response) {
                listaHotel.clear ();
                aptoParaCargar=true;
                if (response.isSuccessful ()) {
                    try {

                        LocalRespuesta localRespuesta = response.body ();
                        listaHotel = Objects.requireNonNull (localRespuesta).getData ();
                        listaHotelesAdapter.adicionarListaHoteles (listaHotel);

                    } catch (Exception e){
                        Log.d (TAG_ERROR, "Hay un error");
                        e.printStackTrace ();
                    }
                    listaHotelesAdapter.notifyDataSetChanged ();

                } else {
                    Log.i (TAG,"El metodo try ha fallado: " + response.errorBody ());
                }
            }

            @Override
            public void onFailure(Call<LocalRespuesta> call, Throwable t) {
                aptoParaCargar=true;
                Log.i (TAG,"Hay un error en la respuesta: " + t.getMessage ());
            }
        });

    }

}
