package a404_notfound.sourceappwater.controllers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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
import com.jjoe64.graphview.GraphView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.HistoricalReport;
import a404_notfound.sourceappwater.model.ReportsHolder;
import a404_notfound.sourceappwater.model.WaterCondition;

/**
 * Class used to supply the criteria for the Historical Report
 * edited by Joshua Davis 4/5/17
 */
public class HistoricalReportCriteria extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private MapView mMapView;
    private GoogleMap mMap;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    protected LatLng mMarkerPosition;
    private EditText mYear;

    private final int SPAN_OF_LOCATION = 20;
    String typeReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_report_criteria);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        mYear = (EditText) findViewById(R.id.yearInput);

        Spinner spinner = (Spinner) findViewById(R.id.typeInput);
        ArrayAdapter adapter2 = new ArrayAdapter(
                this,android.R.layout.simple_spinner_item, new String[]{"PPM", "VPM"}
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeReport = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                typeReport = (String) parent.getItemAtPosition(1);
            }
        });

        Button createReportButton = (Button) findViewById(R.id.createHistoricalReport);

        createReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createReport(mYear.getText().toString(), SPAN_OF_LOCATION,
                        mMarkerPosition, typeReport);
            }
        });
    }

    /**
     * Method to Create the Historical Report also handles switching the Activity
     * @param year Selected year for report
     * @param radius Used to create an area containg report Locations
     * @param location the orginal Location for the report;
     * @param typeReport If the user is searching for PPM;
     */
    private void createReport(String year, int radius, LatLng location, String typeReport) {
        mYear.setError(null);
        boolean canCreateReport = true;
        View focusView = null;
        Integer y = 0;

        try {
             y = Integer.valueOf(year);
        } catch (IllegalArgumentException e) {
            mYear.setError("Please enter a valid Year");
            focusView = mYear;
            canCreateReport = false;
        }

        if (canCreateReport) {
            HistoricalReport h = new HistoricalReport(y, 10, mMarkerPosition, "PPM");
            ReportsHolder.setHRReport(h);
            Intent intent = new Intent(getBaseContext(), ViewHistoricalReport.class);
            startActivity(intent);
        }  else {
            focusView.requestFocus();
        }
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

        if (ContextCompat.checkSelfPermission(this,
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
        if (ContextCompat.checkSelfPermission(this,
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

    protected LatLng getmMarkerPosition() {
        return mMarkerPosition;
    }
}

