package a404_notfound.sourceappwater.model;

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
    private String coordinates;
    private String waterType;
    private String waterCondition;
    private int contamLevel;

    /**
     * Needed for the datasnapshot
     */
    public Report() {
    }

    /**
     *  Constructor for Report.
     *  Used when users want to report a new water source.
     *
     * @param reporter Name of user making the report
     * @param coordinates The longitude and latitude of the water source
     * @param waterType Denotes what is the source of the water
     * @param waterCondition The suitability of the water for drinking
     * @param date The day the report is made
     */
    public Report (String reporter, String coordinates, String waterType, String waterCondition, String date) {
        this.reporter = reporter;
        this.coordinates = coordinates;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        this.date = date;
        reportN = reportNumber;
        //add date and time from fire base as default.
        reportNumber++;
    }

    /**
     *  Second Constructor for Report specifically for Contaminaion Report
     *  Used when worker wants to report a contamin
     * @param reporter Name of user making the report
     * @param coordinates The longitude and latitude of the water source
     * @param waterCondition The suitability of the water for drinking
     * @param date The day the report is made
     * @param contamLevel The contamination level for parts per million
     */


    public Report(String reporter, String coordinates, String waterCondition, String date, int contamLevel){
        this.reporter = reporter;
        this.coordinates = coordinates;
        this.waterCondition = waterCondition;
        this.date = date;
        this.contamLevel = contamLevel;
        reportN = reportNumber;
        reportNumber++;
    }

    /**
     * Method to wrap the report data for placement
     * in database
     *
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

    /**
     * Formats the report's information in this order:
     *  Date: The day the report was made
     *  Report number: The unique ID# for the report
     *  Reporter: Name of user who made the report
     *  Coordinates: Longitutde and  of the location of the water
     *  Water type: Denotes what is the source of the water
     *  Water condition: The suitability of the water for drinking
     * And returns it as a String
     *
     * @return String representation of the report
     */
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