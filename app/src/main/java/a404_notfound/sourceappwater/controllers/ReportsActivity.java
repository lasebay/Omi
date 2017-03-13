package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import a404_notfound.sourceappwater.R;

/**
 * Controller for the reports tab on the UI
 */
public class ReportsActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button viewreport = (Button) findViewById(R.id.viewreport);
        viewreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(), ViewReportActivity.class);
                startActivity(switchScreen);
            }
        });

        Button createreport = (Button) findViewById(R.id.createreport);
        createreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getApplicationContext(),
                        CreateReportsActivity.class);
                startActivity(switchScreen);
            }
        });




    }

    @Override
    protected int getcView() {
        return R.layout.activity_reports;
    }
}
