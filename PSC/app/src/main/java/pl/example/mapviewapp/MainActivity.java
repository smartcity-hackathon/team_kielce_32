package pl.example.mapviewapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private MapView mapView;
    private GoogleMap gmap;

    private Button btnDone;
    private ImageButton btnMore;
    private ImageButton btnCancel;
    private Marker activeMarker;


    private List<LatLng> markerArray = new ArrayList<>();
    private List<JSONObject> mJSONObjectArray = new ArrayList<>();

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);

        btnDone = findViewById(R.id.btnDone);
        btnMore = findViewById(R.id.btnMore);
        btnCancel = findViewById(R.id.btnCancel);

        setSupportActionBar(tb);
        tb.setSubtitle("MapView");

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        btnDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(activeMarker!=null){
                    changeStatusOfIssue(activeMarker,"DONE");
                    activeMarker.remove();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(activeMarker!=null){
                    changeStatusOfIssue(activeMarker,"CANCELED");
                    activeMarker.remove();
                }
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(activeMarker!=null){
                    changeStatusOfIssue(activeMarker,"NEW");
                    activeMarker.remove();
                }
            }
        });


    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        System.out.println(markerArray.hashCode());

        getIssues(googleMap);
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        gmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                activeMarker=marker;
                return false;
            }
        });
    }


    private void getIssues(final GoogleMap googleMap){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://77.55.208.218:8080/issues";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray myObject = new JSONArray(response);
                            for(int i=0; i < myObject.length()-1; i++){
                                mJSONObjectArray.add(myObject.getJSONObject(i));
                                markerArray.add(new LatLng(mJSONObjectArray.get(i).getLong("latiude"),mJSONObjectArray.get(i).getLong("longitude")));
                                activeMarker = googleMap.addMarker(new MarkerOptions().position(markerArray.get(i)).title("Zgłoszenie id:").snippet(mJSONObjectArray.get(i).getString("id")));
                            }
                            gmap.moveCamera(CameraUpdateFactory.newLatLng(markerArray.get(markerArray.size()-1)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }


    private void changeStatusOfIssue(Marker marker, String newStatus){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url;
        url = new StringBuilder().append("http://77.55.208.218:8080/issue/set-status/").append(marker.getSnippet()).append("/").append(newStatus).toString();
        System.out.println(url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    /*@Override
    public boolean onMarkerClick(final Marker marker) {
        activeMarker = marker;
        return false;
    }*/

    @Override
    public void onClick(View view) {

    }




}
