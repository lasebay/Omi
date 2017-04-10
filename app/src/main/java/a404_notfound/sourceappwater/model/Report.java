package a404_notfound.sourceappwater.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michelle on 2/27/2017.
 * Base Model to Create a User Report
 */

public class Report {
    //someone please double check these types
    private Map<String,Object> date;
    private int[] time;
    private String reporter;
    private LatLng coordinates;
    private double lat;
    private double lng;
    private String id;
    private String reportType;

    private String waterType;
    private String waterCondition;
    private String userId;

    /**
     * Needed for the datasnapshot
     */
    Report() {
    }

    /**
     *  Constructor for Report.
     *  Used when users want to report a new water source.
     * @param reportType Type of report
     * @param reporter Name of user making the report
     * @param coordinates The longitude and latitude of the water source
     * @param waterType Denotes what is the source of the water
     * @param waterCondition The suitability of the water for drinking
     * @param date The day the report is made
     * @param userId FIREBASE ID OF THE USER
     */
    public Report (String reportType, String reporter, LatLng coordinates, String waterType
            , String waterCondition, Map<String, Object> date, String userId) {
        this.reporter = reporter;
        this.coordinates = coordinates;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        this.date = date;
        lat = coordinates.latitude;
        lng = coordinates.longitude;
        this.reportType = reportType;
        this.userId = userId;
        //add date and time from fire base as default.
    }

    /**
     * Method to wrap the report data for placement
     * in database
     *
     * @return map containing instance variables
     */
    Map<String, Object> toMap() {
        double lat = coordinates.latitude;
        double lng = coordinates.longitude;
        Map<String, Object> map = new HashMap<>();
        map.put("type", reportType);
        map.put("userId",userId);
        map.put("date", date);
        map.put("reporter", reporter);
        map.put("watertype", waterType);
        map.put("watercondition", waterCondition);
        map.put("lat", lat);
        map.put("lng", lng);

        return map;
    }

    /**
     * Method that returns coordinates of the report
     * @return coordinates of the report
     */
    public LatLng getCoordinates() {
        return new LatLng(lat,lng);
    }

    /**
     *
     * @return Type of water
     */
    public String getWaterType() {
        return waterType;
    }

    /**
     *
     * @return State the water is in
     */
    public String getWaterCondition() {
        return waterCondition;
    }

    /**
     *
     * @return the date the report was made
     */
    Map<String, Object> getDate() {
        return date;
    }

    /**
     *
     * @return the author of the report
     */
    String getReporter() {
        return reporter;
    }

    /**
     * Formats the report's information in this order:
     *  Date: The day the report was made
     *  Report number: The unique ID# for the report
     *  Reporter: Name of user who made the report
     *  Coordinates: Longitude and  of the location of the water
     *  Water type: Denotes what is the source of the water
     *  Water condition: The suitability of the water for drinking
     * And returns it as a String
     *
     * @return String representation of the report
     */
    public String toString() {
        return "Report Type: " + reportType
                    +"Date: " + date.get("month") + "/"
                    + date.get("day") + "/" + date.get("year")+ " " + date.get("time") + "\n"
                    + "Reporter: " + reporter + "\n"
                    + "Coordinates : " + coordinates
                    + "\n Water Type: " + waterType
                    + "\n Water Condition: " + waterCondition
                    + "\n";
    }

    /**
     * Method for Firebase to give the string key
     * @param id the id contained in firebase
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to retrieve that id
     * @return string containing the firebase id
     */
    public String getId() {
        if (id.isEmpty()) {
            return "No Id Supplied";
        }
        return id;
    }

    /**
     *
     * @return Return the type of report
     */
    String getReportType() {
        return reportType;
    }
}