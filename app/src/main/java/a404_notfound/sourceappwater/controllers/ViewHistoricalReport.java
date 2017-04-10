package a404_notfound.sourceappwater.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.HistoricalReport;
import a404_notfound.sourceappwater.model.ReportsHolder;

/**
 * Used to view the submiteed Historical Report
 */
public class ViewHistoricalReport extends AppCompatActivity {

    private final int MAX_MONTHS = 12;
    private final int LOWEST_POSSIBLE_MONTH = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_historical_report);

        HistoricalReport h = ReportsHolder.historicalReport;

        // TODO EVENTUALLY BE REPLACED WITH A BETTER VIEW
        // CURRENTLY USED TO SEE WHICH
        //DATA POINTS ARE IN THE GRAPH AND DISPLAY THEM

        DataPoint[] d = h.getDataPoints();
        String s = "";

        for (int i = 0; i < d.length; i++) {
            s  = s + "\n " + d[i].toString();
        }

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(s);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = h.getGraphSeries();
        series.setDrawDataPoints(true);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(LOWEST_POSSIBLE_MONTH);
        graph.getViewport().setMaxX(MAX_MONTHS);
        graph.addSeries(h.getGraphSeries());

    }
}
