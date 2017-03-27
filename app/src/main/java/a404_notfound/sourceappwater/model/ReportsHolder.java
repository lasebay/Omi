package a404_notfound.sourceappwater.model;

import android.util.SparseArray;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joshua on 3/1/2017.
 *
 * Temporary Report database. To be moved to Firebase
 */

public class ReportsHolder {
    private static SparseArray<Report> holder = new SparseArray<>();
    private static int i = 0;
    private static final FirbaseUtility fbinstance = new FirbaseUtility();

    /**
     *
     *
     * @param report the report to add the database
     */
    public static void addReport(Report report) {
        Integer id = i;
        holder.put(id, report);
        i++;
    }

    /**
     * retrieves a report from the database
     *
     * @param i key of the report
     * @return the report specified
     */
    public static Report getReport(Integer i) {
        return holder.get(i);
    }

    /**
     *
     * @return a String listing all the the reports in the report holder
     */
    public static String getHolder() {

        String list = "";
        for (int i = 0; i < holder.size(); i++) {
            list = list + holder.valueAt(i)
                    + "\n";
        }
        return list;
    }

    /**
     * returns a copy of the reports Data
     * @return a Sparse array of the reports
     */
    public static SparseArray<Report> supplyReports() {
        return holder;
    }


    /**
     * Method used to connect to database and show user all reports submitted.
     */
    public static void updateReportList() {
        fbinstance.getmRef().child("reports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                SparseArray<Report> updatedList = new SparseArray<>();

                for(DataSnapshot reportSnapShot : dataSnapshot.getChildren()) {
                    Map<String, Object> reportMap =
                            (HashMap<String, Object>) reportSnapShot.getValue();
                    String key = reportSnapShot.getKey();
                    Report r = ReportsHolder.translateMapToReport(reportMap, key);

                    updatedList.put(i,r);
                    i++;
                }
                holder = updatedList;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static Report translateMapToReport(Map<String, Object> map, String key) {
        Report r;
        String date = (String) map.get("date");
        String waterType = (String) map.get("watertype");
        String reporter = (String) map.get("reporter");
        String waterCondition = (String) map.get("watercondition");
        double lat = (double) map.get("lat");
        double lng = (double) map.get("lng");
        String reportType = (String) map.get("type");

        LatLng latLng = new LatLng(lat,lng);

        if (("Worker").equals(reportType)) {
            long vpm = (long) map.get("vpm");
            long ppm = (long) map.get("ppm");

            r = new WorkerReport(reportType, reporter, latLng,
                    waterType, waterCondition, date, ppm, vpm);
        } else {
            r = new Report(reportType,reporter,latLng,waterType,waterCondition,date);
        }
        r.setId(key);
        return r;

    }


}
