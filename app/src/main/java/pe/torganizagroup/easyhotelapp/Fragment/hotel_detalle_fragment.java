package pe.torganizagroup.easyhotelapp.Fragment;


import android.accessibilityservice.AccessibilityService;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.torganizagroup.easyhotelapp.R;

public class hotel_detalle_fragment extends Fragment {

    private RecyclerView RvS,RvH;

    public hotel_detalle_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_hotel_detalle, container, false);

        RvS = (RecyclerView) v.findViewById (R.id.Re_servicios);
        RvH = (RecyclerView) v.findViewById (R.id.Re_Tipo_Hab);

        final GridLayoutManager LMServicios = new GridLayoutManager (getContext (), 3);
        final GridLayoutManager LMHabitaciones = new GridLayoutManager (getContext (), 3);
        LMHabitaciones.setOrientation (LinearLayoutManager.VERTICAL);
        LMServicios.setOrientation (LinearLayoutManager.VERTICAL);
        RvS.setLayoutManager (LMServicios);
        RvH.setLayoutManager (LMHabitaciones);
        RvS.setHasFixedSize (true);
        RvH.setHasFixedSize (true);
        RvS.setItemAnimator (new DefaultItemAnimator ());
        RvH.setItemAnimator (new DefaultItemAnimator ());

        return v;
    }

}
