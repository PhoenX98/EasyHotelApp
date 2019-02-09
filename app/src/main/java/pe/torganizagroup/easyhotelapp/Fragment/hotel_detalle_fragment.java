package pe.torganizagroup.easyhotelapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.torganizagroup.easyhotelapp.R;

public class hotel_detalle_fragment extends Fragment {


    public hotel_detalle_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate (R.layout.fragment_hotel_detalle, container, false);
    }

}
