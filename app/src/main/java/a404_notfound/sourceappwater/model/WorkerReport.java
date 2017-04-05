package a404_notfound.sourceappwater.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

/**
 * Created by josh9 on 3/23/2017.
 */

public class WorkerReport extends Report {
    //ppm is not final anymore
    private  long ppm;
    private final long vpm;
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


    //dummy method for Junit Testing
    public long getPpm(){
        return ppm;
    }

    public void setPpm(long ppm1) {
        if (ppm < 0) {
            throw new IllegalArgumentException("PPM cannot be negative");
        }

        this.ppm = ppm1;
    }
}
