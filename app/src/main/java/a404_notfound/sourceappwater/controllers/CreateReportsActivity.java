package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirbaseUtility;
import a404_notfound.sourceappwater.model.Report;
import a404_notfound.sourceappwater.model.ReportsHolder;
import a404_notfound.sourceappwater.model.WaterCondition;
import a404_notfound.sourceappwater.model.WaterType;


public class CreateReportsActivity extends AppCompatActivity {

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

        if (name != null && coordinates != null) {
            Report report = new Report(name, coordinates, wt, wc);
            return report;
        }
        return null;
    }
}
