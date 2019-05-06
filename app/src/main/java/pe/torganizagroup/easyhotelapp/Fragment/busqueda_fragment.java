package pe.torganizagroup.easyhotelapp.Fragment;


import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import pe.torganizagroup.easyhotelapp.Pojo.UbigeoPojo;
import pe.torganizagroup.easyhotelapp.R;
import pe.torganizagroup.easyhotelapp.Retrofit.HotelLista;
import pe.torganizagroup.easyhotelapp.Retrofit.Ubigeo;
import pe.torganizagroup.easyhotelapp.Retrofit.Utilidades;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class busqueda_fragment extends Fragment {

    ArrayAdapter<String> selecAdap;
    ArrayAdapter<String> depAdap;
    ArrayAdapter<String> provAdap;
    ArrayAdapter<String> disAdap;
    Button s;
    private List<Hotels> lH = new ArrayList<> ();
    private HotelLista localFilter;
    private List<UbigeoPojo> ubigeoPojos = new ArrayList<> ();
    private Ubigeo ubigeo;

    AutoCompleteTextView txtDep,txtPro,txtDis;

    Spinner spSeleccion;

    private static final  String TAG = "Locales";
    private static final String TAG_ERROR = "Debug: ";

    public busqueda_fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        ubigeo = Utilidades.getFilter ();
        localFilter = Utilidades.getService ();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_busqueda, container, false);

        spSeleccion = (Spinner) v.findViewById (R.id.spSeleccion);
        txtDep = (AutoCompleteTextView) v.findViewById (R.id.mtxtDepartamento);
        txtPro = (AutoCompleteTextView) v.findViewById (R.id.mtxtProvincia);
        txtDis = (AutoCompleteTextView) v.findViewById (R.id.mtxtDistrito);
        s = (Button) v.findViewById (R.id.btnBuscar);

        String selectionArray[] = getResources ().getStringArray (R.array.seleccion);
        String depArray[] = getResources ().getStringArray (R.array.departamentos);
        String proArray[] = getResources ().getStringArray (R.array.provincias);
        String disArray[] = getResources ().getStringArray (R.array.distritos);

        depAdap = new ArrayAdapter<String> (getContext (), android.R.layout.simple_dropdown_item_1line,depArray);
        provAdap = new ArrayAdapter<String> (getContext (), android.R.layout.simple_dropdown_item_1line,proArray);
        disAdap = new ArrayAdapter<String> (getContext (), android.R.layout.simple_dropdown_item_1line,disArray);

        txtDep.setAdapter (depAdap);
        txtPro.setAdapter (provAdap);
        txtDis.setAdapter (disAdap);

        selecAdap = new ArrayAdapter<String> (getContext (),android.R.layout.simple_spinner_item,selectionArray);
        selecAdap.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spSeleccion.setAdapter (selecAdap);

        spSeleccion.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String op = spSeleccion.getItemAtPosition (spSeleccion.getSelectedItemPosition ()).toString ();

                if(op.contentEquals ("Elegir Departamento")){
                    txtDep.setText ("");
                    txtDep.setVisibility (View.VISIBLE);
                    txtPro.setVisibility (View.GONE);
                    txtDis.setVisibility (View.GONE);

                }else if(op.contentEquals ("Elegir Provincia")){
                    txtPro.setText ("");
                    txtPro.setVisibility (View.VISIBLE);
                    txtDep.setVisibility (View.GONE);
                    txtDis.setVisibility (View.GONE);


                }else if(op.contentEquals ("Elegir Distrito")){
                    txtDis.setText ("");
                    txtDis.setVisibility (View.VISIBLE);
                    txtDep.setVisibility (View.GONE);
                    txtPro.setVisibility (View.GONE);
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

            getData ();
        return v;
    }



        private void getData() {

        s.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
            String de = txtDep.getText ().toString ();
            String pr = txtPro.getText ().toString ();
            String di = txtDis.getText ().toString ();

                doCAll(de,pr,di);
            }

        });

        }

    private void doCAll(String de, String pr, String di) {

        if (pr.equals ("") && di.equals ("")){
            Log.d (TAG,"Prueba de llegada de departamento");
        }else if (de.equals ("") && di.equals ("")){
            Log.d (TAG, "Prueba de llegada de provincia");
        }else if (de.equals ("") && pr.equals ("")){
            Log.d (TAG, "Prueba de llegada de distrito");
            Call<List<UbigeoPojo>> call = ubigeo.getFilter (di);
            call.enqueue (new Callback<List<UbigeoPojo>> () {
                @Override
                public void onResponse(Call<List<UbigeoPojo>> call, Response<List<UbigeoPojo>> response) {
                    ubigeoPojos = response.body ();
                    for (UbigeoPojo ub : ubigeoPojos){
                        String x = ub.getNumber ();

                        doCallHotel(x);
                    }
                }

                @Override
                public void onFailure(Call<List<UbigeoPojo>> call, Throwable t) {

                }
            });
        }






//    Log.d(TAG,dex);
    }

    private void doCallHotel(String x) {
        Log.d (TAG,x);
        Call<List<Hotels>> listCall = localFilter.getFilterHotels (x);
        listCall.enqueue (new Callback<List<Hotels>> () {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                lH = response.body ();
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {

            }
        });
    }


}




