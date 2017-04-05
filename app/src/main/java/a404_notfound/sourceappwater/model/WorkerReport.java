package a404_notfound.sourceappwater.model;

import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Model of a worker report that extends Report
 * Created by josh9 on 3/23/2017.
 */

public class WorkerReport extends Report {
    private final long ppm;
    private final long vpm;

    /**
     *
     * @param reportType defaulted to Worker
     * @param reporter the Name of the reporter
     * @param coordinates the location of the report
     * @param waterType the type of water
     * @param waterCondition condition water is in
     * @param date date the report was created
     * @param userId key to associate the user in the database
     * @param ppm pollution count
     * @param vpm viral count
     */
    public WorkerReport(String reportType, String reporter, LatLng coordinates, String waterType
            , String waterCondition, Map<String ,Object> date, String userId, long ppm, long vpm) {
        super("Worker",reporter, coordinates, waterType, waterCondition, date, userId);
        this.ppm = ppm;
        this.vpm = vpm;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("ppm",ppm);
        map.put("vpm", vpm);
        return map;
    }

    @Override
    public String toString() {
        String s = super.toString();
        s = s + "PPM: " + ppm + "/n";
        s = s + "VPM: " + vpm + "/n";
        return s;
    }

    /**
     * Method to get the pollution count of water
     * @return long number
     */
    long getPpm() {
        return ppm;
    }

    /**
     * Method to get the viral count of water
     * @return long number
     */
    public long getVpm() {
        return vpm;
    }



}
