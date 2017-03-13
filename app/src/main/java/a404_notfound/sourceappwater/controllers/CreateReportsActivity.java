package a404_notfound.sourceappwater.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirbaseUtility;
import a404_notfound.sourceappwater.model.Report;
import a404_notfound.sourceappwater.model.ReportsHolder;
import a404_notfound.sourceappwater.model.WaterCondition;
import a404_notfound.sourceappwater.model.WaterType;


public class CreateReportsActivity extends Activity {

    private EditText mName;
    private EditText mCoordinates;
    private FirbaseUtility fbinstance;
    private Spinner spinner;
    private Spinner spinner2;
    private String waterCondition;
    private String waterType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reports);

        mName = (EditText) findViewById(R.id.name);
        mCoordinates = (EditText) findViewById(R.id.Coordinates);
        fbinstance = new FirbaseUtility();

        Spinner spinner = (Spinner) findViewById(R.id.waterType);
        ArrayAdapter<WaterType> adapter = new ArrayAdapter(
                this,android.R.layout.simple_spinner_item,
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

        Spinner spinner2 = (Spinner) findViewById(R.id.waterCondition);
        SpinnerAdapter adapter2 = new ArrayAdapter(
                this,android.R.layout.simple_spinner_item,
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

        Button mCancelBttn = (Button) findViewById(R.id.reportCancelBttn);
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

    }

    private Report makeReport() {
        String name = mName.getText().toString();
        String coordinates = mCoordinates.getText().toString();
        String wt = waterType;
        String wc = waterCondition;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:s", Locale.US);
        String formattedDate = df.format(c.getTime());


        if (name != null && coordinates != null) {
            return new Report(name, coordinates, wt, wc, formattedDate);
        }
        return null;
    }
}
