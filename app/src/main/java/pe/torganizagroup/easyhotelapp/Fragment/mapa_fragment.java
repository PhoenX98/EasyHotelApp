package pe.torganizagroup.easyhotelapp.Fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import pe.torganizagroup.easyhotelapp.Pojo.Coordenada;
import pe.torganizagroup.easyhotelapp.Pojo.CoordenadaRespuesta;
import pe.torganizagroup.easyhotelapp.R;
import pe.torganizagroup.easyhotelapp.Retrofit.CoordenadaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pe.torganizagroup.easyhotelapp.Retrofit.Utilidades.COORDENADA_URL;

public class mapa_fragment extends Fragment implements OnMapReadyCallback, LocationListener {

    //Etiquetas de puracion
    private static final  String TAG = "Locales";
    private static final String TAG_ERROR = "Debug";
    private ArrayList<Coordenada> listaMarker = new ArrayList<> ();
    double lat = -12.156496930432596;
    double lng = -76.98385873408341;
    double latitude = 0.0;
    double longitude = 0.0;

    private Retrofit retrofit;
    private CoordenadaService markerService;

    Marker marker;
    MapView mMapView;
    GoogleMap mGoogleMap;

    private boolean mapsSupported= true;
//    private GoogleApiClient mGoogleApiClient;

    public mapa_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        retrofit = new Retrofit.Builder ()
                .baseUrl (COORDENADA_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();

        cargarMarcadores();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated (savedInstanceState);
//        MapsInitializer.initialize (Objects.requireNonNull (getActivity ()));
//
//        if (mMapView != null) {
//            mMapView.onCreate (savedInstanceState);
//        }
//        initializeMap ();
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated (view, savedInstanceState);
        // Gets the MapView from the XML layout and creates it
        mMapView = view.findViewById (R.id.mapViewCompleto);
        mMapView.onCreate (savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(Objects.requireNonNull (getActivity ()).getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync (this); //this is important

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate (R.layout.fragment_mapa, container, false);

        mMapView = (MapView) v.findViewById (R.id.mapViewCompleto);
        mMapView.onCreate (savedInstanceState);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setUpMap ();

//        LatLng marker = new LatLng(lat, lng);

//        CameraPosition camera = new CameraPosition.Builder()
//                .target (marker)
//                .zoom (18)          //limite -> 21°
//                .bearing (90)       //nivel de rotacion de mapa(sentido horario) -> 365°
//                .tilt(30)           //nivel de angulo de inclinacion de mapa -> limite= 90°
//                .build();
//
//        googleMap.animateCamera (CameraUpdateFactory.newCameraPosition (camera));

    }

    @SuppressLint("MissingPermission")
    private void setUpMap() {
        mGoogleMap.getUiSettings ().isCompassEnabled ();
        mGoogleMap.setMaxZoomPreference (15);
        mGoogleMap.setMinZoomPreference (10);
        mGoogleMap.getUiSettings ().setMyLocationButtonEnabled (true);
        mGoogleMap.getUiSettings ().setZoomControlsEnabled (true);

        mGoogleMap.moveCamera (CameraUpdateFactory.newLatLngZoom (new LatLng (lat, lng), 15));

        if (ActivityCompat.checkSelfPermission (Objects.requireNonNull (getActivity ()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission (getActivity (), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        mGoogleMap.setMyLocationEnabled (true);
    }

    private void cargarMarcadores() {
        markerService = retrofit.create (CoordenadaService.class);
        Call<CoordenadaRespuesta> call = markerService.obtenerMarcadores ();
        call.enqueue (new Callback<CoordenadaRespuesta> () {
            @Override
            public void onResponse(@NonNull Call<CoordenadaRespuesta> call, @NonNull Response<CoordenadaRespuesta> response) {
                if (response.isSuccessful ()) {
                    try {
                        CoordenadaRespuesta coordenadaRespuesta = response.body ();
                        listaMarker = Objects.requireNonNull (coordenadaRespuesta).getData ();

                        for (int i = 0; i<listaMarker.size (); i++){
                            Coordenada I = listaMarker.get (i);
//                        Log.i (TAG," Local Latitud: " + I.getLatitud ());
//                        Log.i(TAG, " Local Longitud: " + I.getLongitud ());
                            Double lat = Double.parseDouble (I.getLatitud ());
                            Double lng = Double.parseDouble (I.getLongitud ());
                            String title = I.getNombreEmpresa ();
                            MarkerOptions markerOptions = new MarkerOptions();
                            LatLng latLng = new LatLng(lat, lng);
                            markerOptions.position (latLng);
                            markerOptions.title (title);
                            markerOptions.icon (BitmapDescriptorFactory.fromResource (R.drawable.mini_logo_marker));
                            Marker m = mGoogleMap.addMarker (markerOptions);
                        }

                    } catch (Exception e) {
                        Log.d (TAG_ERROR, "Hay un error");
                        e.printStackTrace ();
                    }
                } else {
                    Log.i (TAG,"El metodo try ha fallado: " + response.errorBody ());
                }
            }

            @Override
            public void onFailure(Call<CoordenadaRespuesta> call, Throwable t) {
                Log.i (TAG,"Hay un error en la respuesta: " + t.getMessage ());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
