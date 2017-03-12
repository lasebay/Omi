package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import a404_notfound.sourceappwater.R;

public class CreateContamReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contam_report);

        Button viewreport = (Button) findViewById(R.id.viewreport);
        viewreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), ViewContamReport.class);
                startActivity(switchScreen);
            }
        });

        Button createreport = (Button) findViewById(R.id.createreport);
       createreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), CreateContamReportActivity.class);
                startActivity(switchScreen);
            }
        });

    }
}
