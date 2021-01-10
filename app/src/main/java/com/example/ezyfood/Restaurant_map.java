package com.example.ezyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Restaurant_map extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient client;
    FloatingActionButton fab;
    private String restoName;
    double clientLat  = 0 , clientLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_map);

        fab = findViewById(R.id.fab);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googlemap);
        client = LocationServices.getFusedLocationProviderClient(this);

        if(ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //kalo permission nya granted
            Task<Location> task = client.getLastLocation();
            getClientLocation(task);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION} , 44);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , MainActivity.class);
                intent.putExtra("type" , "map");
                intent.putExtra("restaurant" , restoName);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onRestart();
            }
        }
    }

    private void getClientLocation(Task<Location> task) {
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    clientLat = location.getLatitude();
                    clientLong = location.getLongitude();
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(clientLat, clientLong) , 25));
                            map.addMarker(new MarkerOptions().position(new LatLng(clientLat, clientLong)).title("IM HERE"));
                            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                @Override
                                public void onMapClick(LatLng latLng) {
                                    
                                }
                            });

                            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                @Override
                                public void onMapClick(LatLng latLng) {
                                    map.clear();
                                    map.addMarker(new MarkerOptions().position(latLng).title("disini"));
                                    getNearbyPlaces(getBaseContext() , Double.toString(latLng.latitude) , Double.toString(latLng.longitude));
                                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(clientLat, clientLong) , 15));
                                }
                            });

                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_add_location_alt_24));
                                    fab.setVisibility(View.VISIBLE);
                                    restoName = marker.getTitle();
                                    return false;
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private void getNearbyPlaces(Context mContext , String Latitude, String Longtitude) {
        String URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                 + "location=" + Latitude + "," + Longtitude + "&radius=3000"
                + "&type=restaurant" + "&sensor=true" + "&key=";
        Log.e("URL:" , URL);
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String result = response.getString("results");
                    JSONArray array = new JSONArray(result);
                    for(int i = 0; i < array.length(); i++) {
                        JSONObject maps = array.getJSONObject(i);
                        String name = maps.getString("name");
                        JSONObject geometry = new JSONObject(maps.getString("geometry"));
                        JSONObject latLng = new JSONObject(geometry.getString("location"));
                        String lat = latLng.getString("lat");
                        String lng = latLng.getString("lng");
                        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat) , Double.parseDouble(lng))).title(name));
                        marker.setTag(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}