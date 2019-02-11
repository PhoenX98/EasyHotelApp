package pe.torganizagroup.easyhotelapp.Fragment;


import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

import pe.torganizagroup.easyhotelapp.R;

public class mapa_fragment extends Fragment implements OnMapReadyCallback, LocationListener {

    double lat = -12.156496930432596;
    double lng = -76.98385873408341;
    double latitude = 0.0;
    double longitude = 0.0;

    Marker marker;
    MapView mapView;
    GoogleMap map;

    public mapa_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        MapsInitializer.initialize (Objects.requireNonNull (getActivity ()));

        if (mapView != null) {
            mapView.onCreate (savedInstanceState);
        }
        initializeMap ();
    }

    private void initializeMap() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate (R.layout.fragment_mapa, container, false);

        mapView = v.findViewById (R.id.mapViewCompleto);
        mapView.onCreate (savedInstanceState);
        mapView.getMapAsync (this);

        return v;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude ();
        longitude = location.getLongitude ();

        LatLng loc = new LatLng (latitude, longitude);

        if (marker != null) {
            marker.remove ();
        }

        marker = map.addMarker (new MarkerOptions ().position (loc).title ("My Location"));
        map.moveCamera (CameraUpdateFactory.newLatLng (loc));
        map.animateCamera (CameraUpdateFactory.newLatLngZoom (loc, 16.0f));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getActivity().getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getActivity().getBaseContext(), "Gps is turned off!!",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
