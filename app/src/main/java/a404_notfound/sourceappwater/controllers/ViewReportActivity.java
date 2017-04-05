package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;


import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirbaseUtility;
import a404_notfound.sourceappwater.model.HistoricalReport;
import a404_notfound.sourceappwater.model.ReportViewAdapter;
import a404_notfound.sourceappwater.model.ReportsHolder;

/**
 * Controller for the view report page
 */
public class ViewReportActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_view_report, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabHost host = (TabHost) view.findViewById(R.id.viewReportTabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.workerReportTab);
        spec.setIndicator("Worker Reports");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.userReportsTab);
        spec.setIndicator("User Reports");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Your Reports");
        spec.setContent(R.id.yourReportsTab);
        spec.setIndicator("Your Reports");
        host.addTab(spec);

        ReportsHolder.updateReportList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recylceView);
        RecyclerView userRecycleView = (RecyclerView) view.findViewById(R.id.userReycleView);

        Button button2 = (Button) view.findViewById(R.id.gotohistoricalreport);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchScreen = new Intent(getContext(),
                        HistoricalReportCriteria .class);
                startActivity(switchScreen);
            }
        });
        recyclerView.setHasFixedSize(true);
        userRecycleView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setLayoutManager(linearLayoutManager1);

        ReportViewAdapter adapter;


        ReportViewAdapter adapter2 = new ReportViewAdapter(ReportsHolder.getUserReports());
        if (("Manager").equals(FirbaseUtility.getRole())) {
            adapter = new ReportViewAdapter(ReportsHolder.getWorkerReports());

        } else {
            adapter = new ReportViewAdapter(ReportsHolder.emptyList);
        }
        recyclerView.setAdapter(adapter);
        userRecycleView.setAdapter(adapter2);


    }
}
