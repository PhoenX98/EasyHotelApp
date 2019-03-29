package pe.torganizagroup.easyhotelapp.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pe.torganizagroup.easyhotelapp.Pojo.Departamento;
import pe.torganizagroup.easyhotelapp.Pojo.Distrito;
import pe.torganizagroup.easyhotelapp.Pojo.Provincia;
import pe.torganizagroup.easyhotelapp.Pojo.UbigeoPojo;
import pe.torganizagroup.easyhotelapp.R;
import pe.torganizagroup.easyhotelapp.Retrofit.Ubigeo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;
import static pe.torganizagroup.easyhotelapp.Retrofit.Utilidades.NEW_TEST_URL;

public class busqueda_fragment extends Fragment {

    ArrayList<Departamento> depList = new ArrayList<> ();
    ArrayList<Provincia> provList = new ArrayList<> ();
    ArrayList<Distrito> disList = new ArrayList<> ();
    ArrayAdapter<String> depAdap;
    ArrayAdapter<String> provAdap;
    ArrayAdapter<String> disAdap;
    Spinner spDep;
    Spinner spPro;
    Spinner spDis;
    Departamento depClass = new Departamento("01","asd");
    String val1="",val2="",val3="";

    private static final  String TAG = "Locales";
    private static final String TAG_ERROR = "Debug: ";

    private Retrofit rtUbigeo;
    private Ubigeo spinnerFilter;

    public busqueda_fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        rtUbigeo = new Retrofit.Builder ()
                .baseUrl (NEW_TEST_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();

        setDeptData();
//        setProvData ();
//        setDistData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_busqueda, container, false);

        spDep = (Spinner) v.findViewById (R.id.spDepartamento);
        spPro = (Spinner) v.findViewById (R.id.spProvincia);
        spDis = (Spinner) v.findViewById (R.id.spDistrito);

        spDep.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String dep = spDep.getItemAtPosition (spDep.getSelectedItemPosition ()).toString ();
                Toast.makeText(getContext(),dep,Toast.LENGTH_SHORT).show();
                setProvData ();

                if (dep.contentEquals ("Amazonas")){
                    Toast.makeText(getContext(),"Muestra 1",Toast.LENGTH_SHORT).show();
                } else if (dep.contentEquals ("Ancash")){
                    Toast.makeText(getContext(),"Muestra 2",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spPro.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDis.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

        private void setDeptData () {

//            spinnerFilter = rtUbigeo.create (Ubigeo.class);
//            Call<List<UbigeoPojo>> call = spinnerFilter.getDepartamentos ("00", "00");
//            call.enqueue (new Callback<List<UbigeoPojo>> () {
//                @Override
//                public void onResponse(Call<List<UbigeoPojo>> call, Response<List<UbigeoPojo>> response) {
//                    if (response.isSuccessful ()) {
//                        try {
//                            List<UbigeoPojo> dep = response.body ();
//                            ArrayList<String> dex = new ArrayList<> ();
//                            ArrayList<String> dex2 = new ArrayList<> ();
//
//                            dex.add ("--Seleccione un Departamento--");
//                            if (dep != null) {
//                                for (UbigeoPojo h: dep){
//
//                                    String d1 = h.getDepartamento ();
//                                    String d2 = h.getNombre ();
//
////                                    Departamento d = new Departamento (d1,d2);
//
////                                    dex.add (d1);
//                                    dex.add (d2);
//
//                                }
//                            }
//
//                            depAdap = new ArrayAdapter<String> (getContext (), android.R.layout.simple_spinner_dropdown_item,dex);
//                            depAdap.notifyDataSetChanged ();
//                            spDep.setAdapter (depAdap);
//
//                        } catch (Exception e) {
//                            Log.d (TAG_ERROR, "Hay un error");
//                            e.printStackTrace ();
//                        }
//                    } else {
//                        Log.i (TAG, "El metodo try ha fallado: " + response.errorBody ());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<UbigeoPojo>> call, Throwable t) {
//                    Log.i (TAG,"Hay un error en la respuesta: " + t.getMessage ());
//                }
//            });

        }

        private void setProvData () {
//            spinnerFilter = rtUbigeo.create (Ubigeo.class);
//            Call<List<UbigeoPojo>> call2 = spinnerFilter.getProvincias ("01","00");
//            call2.enqueue (new Callback<List<UbigeoPojo>> () {
//                  @Override
//                  public void onResponse(Call<List<UbigeoPojo>> call, Response<List<UbigeoPojo>> response) {
//                      if (response.isSuccessful ()) {
//                          try {
//                              List<UbigeoPojo> dep1 = response.body ();
//                              ArrayList<String> dex1 = new ArrayList<> ();
//
//                              dex1.add ("--Seleccione una Provincia--");
//
//                              if (dep1 != null) {
//                                  for (UbigeoPojo h: dep1){
//                                      String d1 = h.getDepartamento ();
//                                      String d2 = h.getNombre ();
//                                      Departamento d = new Departamento (d1,d2);
////                                      dex.add (d1);
//                                      dex1.add (d2);
//                                  }
//                              }
//                              provAdap = new ArrayAdapter<String> (getContext (), android.R.layout.simple_spinner_dropdown_item,dex1);
//                              provAdap.notifyDataSetChanged ();
//                              spPro.setAdapter (provAdap);
//                          } catch (Exception e) {
//                              Log.d (TAG_ERROR, "Hay un error");
//                              e.printStackTrace ();
//                          }
//                      } else {
//                          Log.i (TAG, "El metodo try ha fallado: " + response.errorBody ());
//                      }
//                  }
//                  @Override
//                  public void onFailure(Call<List<UbigeoPojo>> call, Throwable t) {
//                      Log.i (TAG,"Hay un error en la respuesta: " + t.getMessage ());
//                  }
//              });

        }

        private void setDistData () {

            //        Distritos

        }

}
