package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.ReportsHolder;

public class ManagerViewReport extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_manager_view_report, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ReportsHolder.updateReportList();

        Button seeUserReport = (Button) view.findViewById(R.id.seeUserReport);

        seeUserReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getContext(),
                        CurrentUsersReports .class);
                startActivity(switchScreen);
            }
        });

        Button seeYourReport = (Button) view.findViewById(R.id.seeYourReport);
        seeYourReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getContext(),
                        YourReportsView.class);
                startActivity(switchScreen);
            }
        });

        Button seeWorkerReport = (Button) view.findViewById(R.id.seeWorkerReports);
        seeWorkerReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getContext(),
                        CurrentWorkerReports.class);
                startActivity(switchScreen);
            }
        });

        Button seeHistoricalReport = (Button) view.findViewById(R.id.seeHistoricalReport);
        seeHistoricalReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getContext(),
                        HistoricalReportCriteria .class);
                startActivity(switchScreen);
            }
        });


    }
}
