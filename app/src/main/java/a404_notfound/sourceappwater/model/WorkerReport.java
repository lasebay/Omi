package a404_notfound.sourceappwater.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

/**
 * Created by josh9 on 3/23/2017.
 */

public class WorkerReport extends Report {
    private final long ppm;
    private final long vpm;

    /**
     *
     * @param reportType the report type that is being passed in
     * @param reporter  the registered user who is reporting
     * @param coordinates   the coordinate of the user
     * @param waterType water type that is being reported
     * @param waterCondition    condition of the water
     * @param date the date
     * @param ppm the ppm being passed in
     * @param vpm the vpm being passed in
     */
    public WorkerReport(String reportType, String reporter, LatLng coordinates, String waterType
            , String waterCondition, String date, long ppm, long vpm) {
        super("Worker",reporter, coordinates, waterType, waterCondition, date);
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
}
