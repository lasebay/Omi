package a404_notfound.sourceappwater.model;

import com.google.android.gms.maps.model.LatLng;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Model for Historical Report
 * Created by josh9 on 3/31/2017.
 */

public class HistoricalReport {


    private LineGraphSeries<DataPoint> series;
    private final DataPoint[] dataPoints;


    /**
     * Constructoor
     * @param year required year the reports must be in;
     * @param radius span of the location area
     * @param location the location of interest
     * @param pmType PPM or VPM data
     */
    public HistoricalReport(int year, int radius, LatLng location, String pmType) {
        series = new LineGraphSeries<>();
        List<DataPoint> dataPointList = new ArrayList<>();


        List<Report> r = ReportsHolder.getWorkerReports();
        for(int i = 0; i < r.size(); i++) {
            WorkerReport wr = (WorkerReport) r.get(i);
            Map<String, Object> c = wr.getDate();

            LatLng reportLocation = wr.getCoordinates();
            double reportLong = reportLocation.longitude;
            double reportLat = reportLocation.latitude;
            double maxLong = location.longitude + radius;
            double maxLat = location.latitude + radius;
            double minLat = location.latitude - radius;
            double minLong = location.longitude - radius;



            if ((((int)(long) c.get("year")) == year)
                    &&  ((minLat < reportLat) && (reportLat < maxLat))
                    && ((minLong < reportLong) && (reportLong < maxLong))) {
                DataPoint l;
                if(("PPM").equals(pmType)) {
                     l = new DataPoint(((int)(long)c.get("month")), wr.getPpm());
                } else {
                     l = new DataPoint(((int)(long)c.get("month")), wr.getPpm());
                }
                dataPointList.add(l);
            }
        }

        dataPoints = new DataPoint[dataPointList.size()];
        for (int i = 0; i < dataPoints.length; i++) {
            dataPoints[i] = dataPointList.get(i);
        }
        series = new LineGraphSeries<>(dataPoints);
    }

    /**
     * Method to return a seriees that is used to create
     * graph
     * @return Series of DataPoints
     */
    public LineGraphSeries<DataPoint> getGraphSeries(){
        return series;
    }

    /**
     * Method to see what datapoints are contained in graph
     * used to create a String of the points
     * @return list of datapoints
     */
    public DataPoint[] getDataPoints() {
        return dataPoints;
    }
}
