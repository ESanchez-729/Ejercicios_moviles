package com.example.ejercicio_googlemaps;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ejercicio_googlemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Huesca and move the camera
        //LatLng huesca = new LatLng(42.1401, -0.408898);
        //mMap.addMarker(new MarkerOptions().position(huesca).title("Marker in Huesca"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(huesca));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(huesca, 10.0f));

        //LatLng zaragoza = new LatLng(41.6563, -0.876566);
        //mMap.addMarker(new MarkerOptions().position(zaragoza).title("Marker in Huesca"));

        //Circle circle = mMap.addCircle(new CircleOptions().center(huesca).radius(10000).strokeColor(Color.RED).fillColor(Color.BLUE));

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

        }

        while (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {}

        try {

            Location locat = manager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            LatLng locActual = new LatLng(locat.getLatitude(), locat.getAltitude());
            mMap.addMarker(new MarkerOptions().position(locActual).title("Marker in Huesca"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locActual));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locActual, 10.0f));

        }

        catch(SecurityException e) {

            e.printStackTrace();
        }



    }
}