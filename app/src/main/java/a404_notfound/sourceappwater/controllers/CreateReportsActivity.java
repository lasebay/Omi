package a404_notfound.sourceappwater.controllers;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirbaseUtility;
import a404_notfound.sourceappwater.model.Report;
import a404_notfound.sourceappwater.model.ReportsHolder;
import a404_notfound.sourceappwater.model.WaterCondition;
import a404_notfound.sourceappwater.model.WaterType;


public class CreateReportsActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private EditText mName;
    private EditText mCoordinates;
    private FirbaseUtility fbinstance;
    private Spinner spinner;
    private Spinner spinner2;
    private String waterCondition;
    private String waterType;
    private GoogleApiClient mGoogleApiClient;
    private double mLatitude;
    private double mLongitude;

    private TextView lat;
    private TextView logd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reports);

        mName = (EditText) findViewById(R.id.name);
        mCoordinates = (EditText) findViewById(R.id.Coordinates);
        fbinstance = new FirbaseUtility();
        lat = (TextView) findViewById(R.id.textView6);
        logd = (TextView) findViewById(R.id.textView7);

        Spinner spinner = (Spinner) findViewById(R.id.waterType);
        ArrayAdapter<WaterType> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, new ArrayList<WaterType>(Arrays.asList(WaterType.values())));
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

        Spinner spinner2 = (Spinner) findViewById(R.id.waterCondition);
        ArrayAdapter<WaterCondition> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, new ArrayList<WaterCondition>(Arrays.asList(WaterCondition.values())));
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

        Button mCancelBttn = (Button) findViewById(R.id.reportCancleBttn);
        mCancelBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReportsActivity.class));
            }
        });
        Button mCreateReportbttn = (Button) findViewById(R.id.createReport);
        mCreateReportbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report rep = makeReport();

                if (rep != null) {
                    ReportsHolder.addReport(rep);
                    fbinstance.addReport(fbinstance.getmRef(), rep);
                }

                startActivity(new Intent(getApplicationContext(), ViewReportActivity.class));

            }
        });

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    private Report makeReport() {
        String name = mName.getText().toString();
        String coordinates = mCoordinates.getText().toString();
        String wt = waterType;
        String wc = waterCondition;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:s");
        String formattedDate = df.format(c.getTime());


        if (name != null && coordinates != null) {
            Report report = new Report(name, coordinates, wt, wc, formattedDate);
            return report;
        }
        return null;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();

            lat.setText(String.valueOf(mLatitude));
            logd.setText(String.valueOf(mLongitude));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
