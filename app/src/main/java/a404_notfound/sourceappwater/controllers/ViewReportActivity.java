package a404_notfound.sourceappwater.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirbaseUtility;
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
        ReportsHolder.updateReportList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recylceView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ReportViewAdapter adapter;
        if (("Manager").equals(FirbaseUtility.getRole())) {
             adapter = new ReportViewAdapter(ReportsHolder.getWorkerReports());
        } else {
            adapter = new ReportViewAdapter(ReportsHolder.getUserReports());
        }
        recyclerView.setAdapter(adapter);


    }
}
