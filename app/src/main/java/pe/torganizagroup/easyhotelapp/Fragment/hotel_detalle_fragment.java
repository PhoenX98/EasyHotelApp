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
import pe.torganizagroup.easyhotelapp.Pojo.HotelDetails;
import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
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
    private String[] hd_photoList = new String[0];
    private int[] photoPlace =  {
        R.drawable.fondo_edificio_alterno,
        R.drawable.fondo_edificio_alterno,
        R.drawable.fondo_edificio_alterno,
        R.drawable.fondo_edificio_alterno,
        R.drawable.fondo_edificio_alterno };

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

        RvS = (RecyclerView) v.findViewById (R.id.hd_RvServices);
//        RvR = (RecyclerView) v.findViewById (R.id.hd_RvRoom);
        txtName = (TextView) v.findViewById (R.id.hd_txtName);
        txtAddress = (TextView) v.findViewById (R.id.hd_txtAddress);
        txtPrice =(TextView) v.findViewById (R.id.hd_txtPrice);
        txtPhone = (TextView) v.findViewById (R.id.hd_txtPhoneNumber);
        btnBack = (Button) v.findViewById (R.id.hd_btnGoHotel);


        final GridLayoutManager LMServices = new GridLayoutManager (getContext (), 3);
//        final GridLayoutManager LMRooms = new GridLayoutManager (getContext (), 3);
//        LMRooms.setOrientation (LinearLayoutManager.VERTICAL);
        LMServices.setOrientation (LinearLayoutManager.VERTICAL);
        RvS.setLayoutManager (LMServices);
//        RvR.setLayoutManager (LMRooms);
        RvS.setHasFixedSize (true);
//        RvR.setHasFixedSize (true);
        RvS.setItemAnimator (new DefaultItemAnimator ());
//        RvR.setItemAnimator (new DefaultItemAnimator ());

        rate_bar = v.findViewById (R.id.hd_StarScore);
//        viewPager = (ViewPager) Objects.requireNonNull (this.getActivity ()).findViewById (R.id.view_pager);
        


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);


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

        }
//        retrieveData();

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



    private void obtenerDetalle(String hd_Id) {

        Call<List<HotelDetails>> detalleCall = detalleLista.getHotelDetalle (hd_Id);

        detalleCall.enqueue (new Callback<List<HotelDetails>> () {
            @Override
            public void onResponse(Call<List<HotelDetails>> call, Response<List<HotelDetails>> response) {

                if (response.isSuccessful ()){
//                    hd_photoList.clear();
                    try {
                        detailsList = response.body();

                        for (HotelDetails d : Objects.requireNonNull (detailsList)){
                            txtPhone.setText (d.getLocalTelephone ());

                            hd_photoList = d.getRoomPhotos ();
                            for (String x : hd_photoList){
                                Log.d ("Fotos: ",x.trim ());
                            }

                            viewPager = (ViewPager) getView ().findViewById(R.id.view_pager);

                            viewPager.setAdapter (new PagerAdapter () {
                                @Override
                                public int getCount() {
                                    return hd_photoList.length;
                                }

                                @Override
                                public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                                    return view == (o);
                                }

                                @NonNull
                                @Override
                                public Object instantiateItem(@NonNull ViewGroup container, int position) {
                                    ImageView imageView = new ImageView (getContext ());

                                    if(!hd_photoList[position].equals ("")){
                                        Glide.with (Objects.requireNonNull (getContext ()))
                                                .load (hd_photoList[position])
                                                .into (imageView);
                                    }else{
                                        Toast.makeText (getContext (),"error en glide",Toast.LENGTH_LONG).show ();
                                    }

                                    container.addView (imageView);
                                    return imageView;
                                }

                                @Override
                                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                                    container.removeView ((View) object);
                                }
                            });

//                            if (viewPager != null){
//
//                                Toast.makeText (getContext ()," Peticion aceptada",Toast.LENGTH_LONG).show ();

////                                Toast.makeText (getContext (),"Evaluando resultado de contexto",Toast.LENGTH_LONG).show ();

//
//                            }else{
//                                call.cancel ();
//                                Toast.makeText (getContext (),"Error en el view pager mascota",Toast.LENGTH_LONG).show ();
//                            }
//                            customPageAdapter = new CustomPageAdapter (Objects.requireNonNull (getContext ()),hd_photoList);
//                            viewPager.setAdapter (customPageAdapter);


                        }

                    }catch (Exception e){
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
    public void onAttach(Context context) {
        super.onAttach (context);
//        mContext = context;
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
