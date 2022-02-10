package com.example.familymemberslocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.persistence.Point;
import com.backendless.persistence.QueryOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.familymemberslocation.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback , LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private String provider;

    double lat=-29.118349, lng=26.22492;

    ImageButton ibSend;

    Boolean isExistingPoint = false;

    Point existingPoint;

    List<Point> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ibSend = (ImageButton) findViewById(R.id.ibsend);
        String type=getIntent().getStringExtra("type");

        if(type.equals("Family"))
        {
            ibSend.setVisibility(View.GONE);

//            BackendlessDataQuery query = new BackendlessDataQuery();
//            query.setWhereClause("categories = Family");
//
//            Backendless.Data.of("Locations").find(query, new AsyncCallback<List<Map>>() {
//                @Override
//                public void handleResponse(List<Map> response) {
//
//                }
//
//                @Override
//                public void handleFault(BackendlessFault fault) {
//
//                }
//            });
        }
        else
        {
            ibSend.setVisibility(View.VISIBLE);

        }

        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapsActivity.this, "Busy sending location...", Toast.LENGTH_SHORT).show();
                Map<String,Object> meta = new HashMap<String,Object>();
                if(!isExistingPoint)
                {
                    List<String> categories=new ArrayList<>();
                    categories.add("Family");

                    meta.put("name",getIntent().getStringExtra("type"));
                    meta.put("updated",new Date().toString());
                    meta.put("Location",new Point().setLatitude(lat).setLongitude(lng));
                    meta.put("categories",categories);

                    Backendless.Data.of("Locations").save(meta, new AsyncCallback<Map>() {
                        @Override
                        public void handleResponse(Map response) {
                            Toast.makeText(MapsActivity.this, "Successfully saved location.", Toast.LENGTH_SHORT).show();
                            isExistingPoint=true;
                            existingPoint= (Point) response.get("Location");
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(MapsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else
                {
                    Backendless.Data.of("Loactions").remove(meta, new AsyncCallback<Long>() {
                        @Override
                        public void handleResponse(Long response) {
                            List<String> categories=new ArrayList<>();
                            categories.add("Family");

                            meta.put("name",getIntent().getStringExtra("type"));
                            meta.put("updated",new Date().toString());
                            meta.put("Location",new Point().setLatitude(lat).setLongitude(lng));
                            meta.put("categories",categories);

                            Backendless.Data.of("Locations").save(meta, new AsyncCallback<Map>() {
                                @Override
                                public void handleResponse(Map response) {
                                    Toast.makeText(MapsActivity.this, "Successfully saved location.", Toast.LENGTH_SHORT).show();
                                    isExistingPoint=true;
                                    existingPoint= (Point) response.get("Location");
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(MapsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(MapsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider=locationManager.getBestProvider(criteria,false);

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
        else
        {
            Location location = locationManager.getLastKnownLocation(provider);
            if(location!=null)
            {
                onLocationChanged(location);
            }
        }
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

        // Add a marker in Sydney and move the camera
        LatLng position = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,10));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10),2000,null);

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
        else {
            mMap.setMyLocationEnabled(true);
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();

        if(mMap!=null)
        {
            LatLng position = new LatLng(lat,lng);

            mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.person_foreground))
                .anchor(0.0f,1.0f)
                .title("Your Last Known Location")
                .position(position));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
        else {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0);
        }
        else {
            // 180000 - 3 mins mai update hogi location or 50 - 50 metres moved previous location
            locationManager.requestLocationUpdates(provider,180000,50,this);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}