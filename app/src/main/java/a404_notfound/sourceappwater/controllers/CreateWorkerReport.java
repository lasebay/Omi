package a404_notfound.sourceappwater.controllers;


import android.support.annotation.Nullable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import android.widget.TabHost;



import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Locale;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.FirebaseUtility;

import a404_notfound.sourceappwater.model.WorkerReport;

/**
 * Creates a worker report class using the information submitted
 */
public class CreateWorkerReport extends CreateReportsActivity {

    private FirebaseUtility fbinstance;
    private EditText editText;
    private EditText editText1;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_create_worker_report, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabHost host = (TabHost) view.findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("User Report");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Worker Report");
        host.addTab(spec);


        editText = (EditText) view.findViewById(R.id.vpm);
        editText1 = (EditText) view.findViewById(R.id.ppm);


        fbinstance = new FirebaseUtility();
        Button mCreateReportbttn2 = (Button) view.findViewById(R.id.createReport2);
        mCreateReportbttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkerReport rep = makeWorkerReport();

                if (rep != null) {
                    //ReportsHolder.addReport(rep);
                    fbinstance.addReport(fbinstance.getmRef(), rep);
                }

                 //startActivity(new Intent(getActivity().getApplicationContext(), NavigationMain.class));

            }
        });


    }

    private WorkerReport makeWorkerReport() {
        String name = super.author;
        String wt = super.waterType;
        String wc = super.waterCondition;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:s", Locale.US);
        String vpm = editText.getText().toString();
        String ppm = editText1.getText().toString();

        int p = Integer.parseInt(vpm);
        int v = Integer.parseInt(ppm);

        String formattedDate = df.format(c.getTime());


        if (super.getmMarkerPosition() != null) {
            return new WorkerReport("Worker", name, super.getmMarkerPosition(), wt, wc, formattedDate, p, v);
        }
        return null;
    }
}
