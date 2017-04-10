package a404_notfound.sourceappwater.controllers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirebaseUtility;
import a404_notfound.sourceappwater.model.Report;
import a404_notfound.sourceappwater.model.WaterCondition;
import a404_notfound.sourceappwater.model.WaterType;

/**
 * Class Written to make user Report Activities
 */
public class CreateReportsActivity extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private TextView mName;
    private FirebaseUtility fbinstance;
    private Spinner spinner;
    private Spinner spinner2;
    String waterCondition;
    String waterType;
    String author;

    private MapView mMapView;
    private GoogleMap mMap;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LatLng mMarkerPosition;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_create_reports, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mName = (TextView) view.findViewById(R.id.reportAuthor);
        fbinstance = new FirebaseUtility();

        Spinner spinner = (Spinner) view.findViewById(R.id.waterType);
        ArrayAdapter<WaterType> adapter = new ArrayAdapter(
                getActivity(),android.R.layout.simple_spinner_item,
                new ArrayList<>(Arrays.asList(WaterType.values())));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                waterType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                waterType = parent.getItemAtPosition(1).toString();
            }
        });

        Spinner spinner2 = (Spinner) view.findViewById(R.id.waterCondition);
        SpinnerAdapter adapter2 = new ArrayAdapter(
                getActivity(),android.R.layout.simple_spinner_item,
                new ArrayList<>(Arrays.asList(WaterCondition.values())));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                waterCondition = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                waterCondition = parent.getItemAtPosition(1).toString();
            }
        });

//        Button mCancelBttn = (Button) view.findViewById(R.id.reportCancelBttn);
//        mCancelBttn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
        Button mCreateReportbttn = (Button) view.findViewById(R.id.createReport);
        mCreateReportbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report rep = makeReport();

                if (rep != null) {
                    //ReportsHolder.addReport(rep);
                    fbinstance.addReport(fbinstance.getmRef(), rep);
                }


            }
        });


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        fbinstance.getmRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                author = dataSnapshot.child("users")
                        .child(fbinstance.getUser())
                        .child("name")
                        .getValue()
                        .toString();

                String authorView = "Author: " + author;
//                mName.setText(authorView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    private Report makeReport() {
        String name = author;
        String wt = waterType;
        String wc = waterCondition;
        Calendar c = Calendar.getInstance();
        Map<String, Object> arr = new HashMap<>();
       arr.put("year", c.get(Calendar.YEAR));
        arr.put("month", c.get(Calendar.MONTH));
        arr.put("day", c.get(Calendar.DAY_OF_MONTH));

        String s = c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        arr.put("time", s);



        if (mMarkerPosition != null) {
            return new Report("User",name, mMarkerPosition, wt, wc, arr, fbinstance.getUser());
        }
        return null;
    }


    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                mMarkerPosition = marker.getPosition();
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                mMarkerPosition = marker.getPosition();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mMarkerPosition = marker.getPosition();
            }
        });

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        }
        LatLng curLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(curLocation)
                .draggable(true)
                .title("Location of Water"));
        mMarkerPosition = curLocation;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(curLocation));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    LatLng getmMarkerPosition() {
        return mMarkerPosition;
    }
}

