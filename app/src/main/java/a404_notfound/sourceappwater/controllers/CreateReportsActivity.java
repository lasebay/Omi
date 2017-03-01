package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirbaseUtility;
import a404_notfound.sourceappwater.model.Report;
import a404_notfound.sourceappwater.model.ReportsHolder;


public class CreateReportsActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mCoordinates;
    private FirbaseUtility fbinstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reports);

        mName = (EditText) findViewById(R.id.name);
        mCoordinates = (EditText) findViewById(R.id.Coordinates);
        fbinstance = new FirbaseUtility();

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

        if (name != null && coordinates != null) {
            Report report = new Report(name, coordinates);
            return report;
        }
        return null;
    }
}
