package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import a404_notfound.sourceappwater.R;

public class ReportScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);

        Button reportsactivity = (Button) findViewById(R.id.reports);
        reportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), ReportsActivity.class);
                startActivity(switchScreen);
            }
        });

        Button contamination = (Button) findViewById(R.id.contaminationreport);
        reportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), ContaminationReport.class);
                startActivity(switchScreen);
            }
        });
    }
}
