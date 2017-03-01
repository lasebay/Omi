package a404_notfound.sourceappwater.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michelle on 2/27/2017.
 */

public class Report {
    //someone please double check these types
    private int[] date;
    private int[] time;
    private static int reportNumber;
    private String reporter;
    private String coordinates;

    public Report() {
        //Needed for the datasnapshot;
    }
    public Report (String reporter, String coordinates) {
        this.reporter = reporter;
        this.coordinates = coordinates;
        //add date and time from fire base as default.
        reportNumber++;
    }

    /**
     * Method to wrap the report data for placement
     * in database
     * @return map containing instance variables
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", "March 1 2017");
        map.put("time", "1:16");
        map.put("repoNum", 5);
        map.put("reporter", reporter);
        map.put("coor", coordinates);
        return map;
    }

    public String toString() {
        return "Date: March 1 2017 \n"
                    + "Time: 1:16 \n"
                    + "Report Number: 5 \n"
                    + "Reporter: " + reporter + "\n"
                    + "Coordinates : " + coordinates
                    + "\n";
    }
}