package pe.torganizagroup.easyhotelapp.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.skoumal.fragmentback.BackFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pe.torganizagroup.easyhotelapp.Adapters.CustomPageAdapter;
import pe.torganizagroup.easyhotelapp.Adapters.DhServicioAdapter;
import pe.torganizagroup.easyhotelapp.DrawableActivity;
import pe.torganizagroup.easyhotelapp.Pojo.HotelDetails;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import pe.torganizagroup.easyhotelapp.Pojo.Service;
import pe.torganizagroup.easyhotelapp.R;
import pe.torganizagroup.easyhotelapp.Retrofit.HotelDetalle;
import pe.torganizagroup.easyhotelapp.Retrofit.Utilidades;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hotel_detalle_fragment extends Fragment implements BackFragment {

    private static final String TAG = "Locales";
    private static final String TAG_ERROR = "Debug: ";

    private Context mContext;
    private TextView txtAddress;
    private TextView txtPhone;
    private TextView txtPrice;
    private TextView txtName;
    private String hd_Id,hd_name,hd_address,hd_phoneNumber,hd_price,hd_score;
    private RecyclerView RvS,RvR;
    private List<HotelDetails> detailsList = new ArrayList<> ();
    private List<String> serviceList = new ArrayList<> ();
    private DhServicioAdapter servicioAdapter;
    private String[] hd_photoList = new String[0];
    private HotelDetalle detalleLista;
    private RatingBar rate_bar;
    private Button btnBack;
    private ViewPager viewPager;
    private CustomPageAdapter customPageAdapter;

    public hotel_detalle_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        detalleLista = Utilidades.getData ();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_hotel_detalle, container, false);
        mContext = getActivity ();
        RvS = (RecyclerView) v.findViewById (R.id.hd_RvServices);
        txtName = (TextView) v.findViewById (R.id.hd_txtName);
        txtAddress = (TextView) v.findViewById (R.id.hd_txtAddress);
        txtPrice =(TextView) v.findViewById (R.id.hd_txtPrice);
        txtPhone = (TextView) v.findViewById (R.id.hd_txtPhoneNumber);
        btnBack = (Button) v.findViewById (R.id.hd_btnGoHotel);
//        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
//        CustomPageAdapter customPageAdapter = new CustomPageAdapter (mContext,hd_photoList);
//        viewPager.setAdapter (customPageAdapter);
        servicioAdapter =  new DhServicioAdapter (getContext (),serviceList,this);
        RvS.setAdapter (servicioAdapter);

        final GridLayoutManager LMServices = new GridLayoutManager (getContext (), 3);
        LMServices.setOrientation (LinearLayoutManager.VERTICAL);
        RvS.setLayoutManager (LMServices);
        RvS.setHasFixedSize (true);
        RvS.setItemAnimator (new DefaultItemAnimator ());
        rate_bar = v.findViewById (R.id.hd_StarScore);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
    }


    private void createData() {
        Bundle b = getArguments ();
        if(b!=null){

            hd_Id = getArguments ().getString ("transitionId");
            hd_name = getArguments ().getString ("transitionName");
            hd_address = getArguments ().getString ("transitionAddress");
            hd_score = getArguments ().getString ("transitionScore");
            Hotels ho = (Hotels) b.getSerializable ("hotel");

            if (ho != null){

                txtName.setText (ho.getNameHotel ());
                txtAddress.setText (ho.getAddress ());
                txtPrice.setText (ho.getMinimalRate ());
                rate_bar.setNumStars (ho.getQualification ());
                String hotId = ho.getId ();
                obtenerDetalle(hotId);
            }

            btnBack.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    lista_hoteles_fragment h = new lista_hoteles_fragment ();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = Objects.requireNonNull (fragmentManager).beginTransaction();
                    fragmentTransaction.replace (R.id.contenedor,h);
                    fragmentTransaction.commit ();
                }
            });
        }


    }


    private void obtenerDetalle(String hd_Id) {

        Call<List<HotelDetails>> detalleCall = detalleLista.getHotelDetalle (hd_Id);

        detalleCall.enqueue (new Callback<List<HotelDetails>> () {
            @Override
            public void onResponse(Call<List<HotelDetails>> call, Response<List<HotelDetails>> response) {

                if (response.isSuccessful ()){
                    try {

                        List<HotelDetails> details = response.body();

                        detailsList = Objects.requireNonNull (details);
//                        servicioAdapter.addServices(details);

                        for (HotelDetails d : Objects.requireNonNull (detailsList)) {
                            txtPhone.setText (d.getLocalTelephone ());
                            List<String> services = d.getService ();
                            servicioAdapter.addServices(services);

//                            viewPager.setAdapter (new CustomPageAdapter (mContext,hd_photoList));

                        }
//                        for (HotelDetails d : Objects.requireNonNull (detailsList)){
//                            txtPhone.setText (d.getLocalTelephone ());
//                            List<String> services = d.getService ();
//                            hd_photoList = d.getRoomPhotos ();
//                            serviceList = Objects.requireNonNull (services);
//
//                            for (String x : hd_photoList){
//                                Log.d ("Fotos: ",x.trim ());
//                            }
//
//                        }



                    }catch (Exception e){
                        call.cancel ();
                        Log.d (TAG_ERROR, "Hay un error");
                        e.printStackTrace ();
                    }

                } else {
                    Log.i (TAG,"El metodo try ha fallado: " + response.errorBody ());
                }

            }

            @Override
            public void onFailure(Call<List<HotelDetails>> call, Throwable t) {
                Log.i (TAG,"Hay un error en la respuesta: " + t.getMessage ());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart ();
        createData();
    }



    @Override
    public void onStop() {
        super.onStop ();
    }

    @Override
    public void onResume() {
        super.onResume ();
    }




    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public int getBackPriority() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();

    }
}
