package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import a404_notfound.sourceappwater.R;

public class ReportScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);


        Button waterreport = (Button) findViewById(R.id.waterreport);
        waterreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), ReportsActivity.class);
                startActivity(switchScreen);
            }
        });

        Button contaminationreport = (Button) findViewById(R.id.contamreport);
        waterreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), ContaminationReport.class);
                startActivity(switchScreen);
            }
        });



    }


}
