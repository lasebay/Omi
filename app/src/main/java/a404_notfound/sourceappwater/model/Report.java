package a404_notfound.sourceappwater.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michelle on 2/27/2017.
 */

public class Report {
    //someone please double check these types
    private String date;
    private int[] time;
    private static int reportNumber = 0;
    private int reportN = reportNumber;
    private String reporter;
    private LatLng coordinates;
    private String waterType;
    private String waterCondition;

    public Report() {
        //Needed for the datasnapshot;
    }
    public Report (String reporter, LatLng coordinates, String waterType, String waterCondition, String date) {
        this.reporter = reporter;
        this.coordinates = coordinates;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        this.date = date;
        reportN = reportNumber;
        //add date and time from fire base as default.
        reportNumber++;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public String getWaterType() {
        return waterType;
    }

    public String getWaterCondition() {
        return waterCondition;
    }

    /**
     * Method to wrap the report data for placement
     * in database
     * @return map containing instance variables
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("repoNum", reportN);
        map.put("reporter", reporter);
        map.put("coor", coordinates);
        map.put("watertype", waterType);
        map.put("watercondition", waterCondition);
        return map;
    }

    public String toString() {
        return "Date: " + date + "\n"
                    + "Report Number: " + reportN + "\n"
                    + "Reporter: " + reporter + "\n"
                    + "Coordinates : " + coordinates
                    + "\n Water Type: " + waterType
                    + "\n Water Condition: " + waterCondition
                    + "\n";
    }

}