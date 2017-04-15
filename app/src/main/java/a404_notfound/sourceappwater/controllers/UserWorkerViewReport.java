package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.ReportsHolder;

/**
 * Class shown when workers and users want to see a list of reports
 *
 */
public class UserWorkerViewReport extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user_worker_view_report, container, false);
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

        Button seeYourReport = (Button) view.findViewById(R.id.seeMyReports);
        seeYourReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getContext(),
                        YourReportsView.class);
                startActivity(switchScreen);
            }
        });


    }
}
