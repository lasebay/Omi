package a404_notfound.sourceappwater.controllers;

import android.os.Bundle;
import android.widget.TextView;


import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.ReportsHolder;

/**
 * Controller for the view report page
 */
public class ViewReportActivity extends DrawerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView mView = (TextView) findViewById(R.id.viewableReport);

        mView.setText(ReportsHolder.getHolder());

//        ValueEventListener reportListner = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Report report = dataSnapshot.getValue(Report.class);
//                mView.setText(report.toMap().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
    }

    @Override
    protected int getcView() {
        return R.layout.activity_view_report;
    }
}
