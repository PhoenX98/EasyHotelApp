package pe.torganizagroup.easyhotelapp.Fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pe.torganizagroup.easyhotelapp.Pojo.Hotels;
import pe.torganizagroup.easyhotelapp.R;
import pe.torganizagroup.easyhotelapp.Retrofit.HotelLista;
import pe.torganizagroup.easyhotelapp.Retrofit.Utilidades;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;

public class mapa_fragment extends Fragment implements OnMapReadyCallback, LocationListener {

    private static final String TAG = "Locales";
    private static final String TAG_ERROR = "Debug";
    private static final String TAG_MAP = "Map";
    double lat = 0.0;
    double lng = 0.0;
//    double lat1 = 0.0;
//    double lng1 = 0.0;
    AlertDialog alert = null;
    AlertDialog upss = null;

    private List<Hotels> lH1 = new ArrayList<> ();
    private HotelLista localTest;
//    GoogleApiClient mGoogleApiClient;
    MapView mMapView;
    GoogleMap mGoogleMap;

    public mapa_fragment() { }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        mMapView = Objects.requireNonNull (getActivity ()).findViewById (R.id.mapViewCompleto);
        mMapView.onCreate (savedInstanceState);
        mMapView.onResume ();

        try {
            MapsInitializer.initialize (Objects.requireNonNull (getActivity ()).getApplicationContext ());
        } catch (Exception e) {
            e.printStackTrace ();
            Log.i (TAG_MAP," Error al inicializar mapa " + e.getMessage ());
        }

        mMapView.getMapAsync (this);


    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        localTest = Utilidades.getService ();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate (R.layout.fragment_mapa, container, false);

        mMapView = (MapView) v.findViewById (R.id.mapViewCompleto);
        mMapView.onCreate (savedInstanceState);

        LocationManager locationManager = (LocationManager) getActivity ().getSystemService (LOCATION_SERVICE);

        assert locationManager != null;
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
        }


        return v;
    }

    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder( getActivity ());
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
//        mGoogleApiClient.connect ();
        setUpMap ();

    }

    @SuppressLint("MissingPermission")
    private void setUpMap() {

        LocationManager locationManager = (LocationManager) getActivity ().getSystemService (getActivity ().LOCATION_SERVICE);
        Criteria criteria = new Criteria ();
        String bestProvider = locationManager.getBestProvider (criteria, true);
        if (ActivityCompat.checkSelfPermission
                (Objects.requireNonNull (getActivity ()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission (getActivity (), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        Location location = locationManager.getLastKnownLocation (bestProvider);

            if(location!=null){
                lat = location.getLatitude ();
                lng = location.getLongitude ();

                LatLng loc = new LatLng (lat,lng);
//                mGoogleMap.moveCamera (CameraUpdateFactory.newLatLng (loc));
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 21));

            }


        locationManager.requestLocationUpdates(bestProvider, 2000, 100, this);
        mGoogleMap.setMyLocationEnabled (true);
        mGoogleMap.getUiSettings ().isCompassEnabled ();
        //max=21.0 - min=2.0
        mGoogleMap.setMaxZoomPreference (21);
        mGoogleMap.setMinZoomPreference (5);
        mGoogleMap.getUiSettings ().setMyLocationButtonEnabled (true);
        mGoogleMap.getUiSettings ().setZoomGesturesEnabled (true);
        mGoogleMap.getUiSettings ().setZoomControlsEnabled (true);
        mGoogleMap.getUiSettings ().setMapToolbarEnabled (true);
        mGoogleMap.setMapType (GoogleMap.MAP_TYPE_NORMAL);
//        mGoogleMap.setOnMarkerClickListener ();

        cargarMarcadores ();
    }

    private void cargarMarcadores() {

        Call<List<Hotels>> call = localTest.getHotels ();
        call.enqueue (new Callback<List<Hotels>> () {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                if(response.isSuccessful ()){
                    try {
                        List<Hotels> h = response.body ();

//                        assert h != null;
                        for (Hotels test: Objects.requireNonNull (h)){
                            lat = Double.parseDouble (test.getLatitude ());
                            lng = Double.parseDouble (test.getLength ());
                            LatLng latLng = new LatLng(lat, lng);
                            String title = test.getNameHotel ();
                            mGoogleMap.addMarker (new MarkerOptions ()
                                .position (latLng)
                                .title (title)
                                .icon (BitmapDescriptorFactory.fromResource (R.drawable.mini_logo_marker)));
                        }

                    } catch (Exception e){
                        Log.d (TAG_ERROR, "Hay un error" + e.getMessage ());
                        e.printStackTrace ();

                    }
                } else {
                    Log.i (TAG,"El metodo try ha fallado: " + response.errorBody ());
                    UpssAlert();
                }
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {
                Log.i (TAG_ERROR,"Error en el parseo de JSON, revisar parametros"+t.getMessage ());
                t.printStackTrace ();
                UpssAlert();
            }
        });

    }

    private void UpssAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull (getActivity ()));
        builder.setMessage("Parece que hay un error, estamos trabajando en resolverlo ;v")
                .setCancelable(false)
                .setTitle (" ¡Upps! ")
                .setIcon (R.drawable.ic_cancel_black_24dp)
                .setPositiveButton ("Ok", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel ();
                        Toast.makeText (getContext (),"Gracias por tu comprension :3",Toast.LENGTH_SHORT).show ();
                        dialog.dismiss ();
                    }
                })
        ;
        upss = builder.create();
        upss.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location!=null){
            lat = location.getLatitude ();
            lng = location.getLongitude ();

            LatLng loc = new LatLng (lat,lng);
            mGoogleMap.moveCamera (CameraUpdateFactory.newLatLng (loc));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));

        }
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

}
