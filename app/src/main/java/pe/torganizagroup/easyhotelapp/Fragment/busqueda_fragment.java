package pe.torganizagroup.easyhotelapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.torganizagroup.easyhotelapp.R;

public class busqueda_fragment extends Fragment {


    public busqueda_fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate (R.layout.fragment_busqueda, container, false);

        return v;
    }

}
