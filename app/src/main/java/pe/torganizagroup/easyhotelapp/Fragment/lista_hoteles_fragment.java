package pe.torganizagroup.easyhotelapp.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pe.torganizagroup.easyhotelapp.Adapters.ListaHotelAdapter;
import pe.torganizagroup.easyhotelapp.DrawableActivity;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import pe.torganizagroup.easyhotelapp.R;
import pe.torganizagroup.easyhotelapp.Retrofit.HotelLista;
import pe.torganizagroup.easyhotelapp.Retrofit.Ubigeo;
import pe.torganizagroup.easyhotelapp.Retrofit.Utilidades;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lista_hoteles_fragment extends Fragment {

    private static final String TAG = "Locales";
    private static final String TAG_ERROR = "Debug: ";

    private RecyclerView recyclerView;
    private ListaHotelAdapter HAdapter;
    private List<Hotels> lH1 = new ArrayList<> ();

    private HotelLista localTest;
    AlertDialog upss = null;
    private Context context;

    FloatingActionButton fab;
    Button btnFilter;

    public lista_hoteles_fragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        localTest = Utilidades.getService ();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_lista_hoteles, container, false);
        context = getActivity ();
        recyclerView = view.findViewById (R.id.ListaFullHotel);
        fab = (FloatingActionButton) view.findViewById (R.id.list_fabBtn);
        btnFilter = (Button) view.findViewById (R.id.list_btnFilter);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager (getContext (), 2);
        gridLayoutManager.setSpanSizeLookup (new GridLayoutManager.SpanSizeLookup () {
            @Override
            public int getSpanSize(int position) {
                return (position % 3 == 0 ? 2 : 1);
            }
        });

        HAdapter = new ListaHotelAdapter (getContext (), lH1, this);
        recyclerView.setAdapter (HAdapter);

        recyclerView.setLayoutManager (gridLayoutManager);
        recyclerView.setHasFixedSize (true);
        recyclerView.setItemAnimator (new DefaultItemAnimator ());

        btnFilter.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                busqueda_fragment h = new busqueda_fragment ();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = Objects.requireNonNull (fragmentManager).beginTransaction();
                fragmentTransaction.replace (R.id.contenedor,h);
                fragmentTransaction.commit ();
            }
        });

        fab.setImageResource (R.drawable.fab_mapa);

        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mapa_fragment h = new mapa_fragment ();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = Objects.requireNonNull (fragmentManager).beginTransaction();
                fragmentTransaction.replace (R.id.contenedor,h);
                fragmentTransaction.commit ();
            }
        });

        return view;
    }

    private void obtenerDatos() {

        Call<List<Hotels>> call1 = localTest.getHotels ();
        call1.enqueue (new Callback<List<Hotels>> () {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                lH1.clear ();
                if (response.isSuccessful ()) {
                    try {
                        List<Hotels> h = response.body ();
                        lH1 = Objects.requireNonNull (h);
                        HAdapter.addHotelItem (lH1);

                    } catch (Exception e) {
                        Log.d (TAG_ERROR, "Hay un error");
                        e.printStackTrace ();
                    }

                } else {
                    Log.i (TAG, "El metodo try ha fallado: " + response.errorBody ());
                    UpssAlert ();
                }
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {
                Log.i (TAG_ERROR, "Error en el parseo de JSON" + t.getMessage ());
                t.printStackTrace ();
                UpssAlert ();
            }
        });

    }

    private void UpssAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder (Objects.requireNonNull (getActivity ()));
        builder.setMessage ("Parece que hay un error, estamos trabajando en resolverlo ;v")
                .setCancelable (false)
                .setTitle (" ¡Upps! ")
                .setIcon (R.drawable.ic_cancel_black_24dp)
                .setPositiveButton ("Ok", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel ();
                        Toast.makeText (getContext (), "Gracias por tu comprension :3", Toast.LENGTH_SHORT).show ();
                        dialog.dismiss ();
                    }
                })
        ;

        //1090, no presionar enter

        upss = builder.create ();
        upss.show ();

    }

    @Override
    public void onStart() {
        super.onStart ();
        lH1.clear ();
        obtenerDatos ();

    }

    @Override
    public void onResume() {
        super.onResume ();
    }

    @Override
    public void onStop() {
        super.onStop ();
    }


    public void openHotelDetailFragment(int position, View view) {
        if (context instanceof DrawableActivity) {
            Hotels hotel = lH1.get (position);
            hotel_detalle_fragment hotelDetail = new hotel_detalle_fragment ();
            Bundle bundle = new Bundle ();
            bundle.putString ("transitionId", "transition" + position);
            bundle.putString ("transitionName", "transition" + position);
            bundle.putString ("transitionAddress", "transition" + position);
            bundle.putString ("transitionScore", "transition" + position);
            bundle.putSerializable ("hotel", hotel);
            hotelDetail.setArguments (bundle);
            ((DrawableActivity) context).showFragmentWithTransition (this, hotelDetail, "hotelDetail", view, "transition" + position);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();

    }
}
