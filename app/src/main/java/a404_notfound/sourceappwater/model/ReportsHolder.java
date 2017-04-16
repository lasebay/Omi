package a404_notfound.sourceappwater.model;

import android.util.SparseArray;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joshua on 3/1/2017.
 *
 * Temporary Report database. To be moved to Firebase
 */

public class ReportsHolder {
    private static SparseArray<Report> holder = new SparseArray<>();
    private static final List<Report> workerReports = new ArrayList<>();
    private static final List<Report> userReports = new ArrayList<>();
    private static final List<Report> yourReport = new ArrayList<>();
    public static  final List<Report> emptyList = new ArrayList<>();
    public static HistoricalReport historicalReport;


    private static int i = 0;
    private static final FirebaseUtility fbinstance = new FirebaseUtility();

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
     *
     * @return Return the generated list of User Reports
     */
    public static List<Report> getUserReports() {
        return userReports;
    }

    /**
     *
     * @return Return the generated list of Worker Reports
     */
    public static List<Report> getWorkerReports() {
        return workerReports;
    }


    /**
     *
     * @return Reports associated with the usersID
     */
    public static List<Report> getYourReport() { return yourReport; }

    /**
     * Method used to connect to database and show user all reports submitted.
     */
    public static void updateReportList() {
        fbinstance.getmRef().child("reports").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                SparseArray<Report> updatedList = new SparseArray<>();
                workerReports.clear();
                userReports.clear();

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


    /**
     * Method to set what the current Historical Report is
     * used as a workaround until Reports can fully implment
     * Serilizable class
     * @param h Historical Report created in HistoricalReportCritera
     */
    public static  void setHRReport(HistoricalReport h) {
        historicalReport = h;

    }
    private static Report translateMapToReport(Map<String, Object> map, String key) {
        Report r;
        Map<String, Object> date = (Map<String, Object>) map.get("date");
        String waterType = (String) map.get("watertype");
        String reporter = (String) map.get("reporter");
        String waterCondition = (String) map.get("watercondition");
        double lat = (double) map.get("lat");
        double lng = (double) map.get("lng");
        String reportType = (String) map.get("type");
        String userId = (String) map.get("userId");

        LatLng latLng = new LatLng(lat,lng);

        if (("Worker").equals(reportType)) {
            long vpm = (long) map.get("vpm");
            long ppm = (long) map.get("ppm");

            r = new WorkerReport(reportType, reporter, latLng,
                    waterType, waterCondition, date, userId, ppm, vpm);
            r.setId(key);
            workerReports.add(r);
        } else {
            r = new Report(reportType,reporter,latLng,waterType,waterCondition,date,userId);
            r.setId(key);
            userReports.add(r);
        }

        if (fbinstance.getUser().equals(userId)) {
            yourReport.add(r);
        }
        return r;

    }


}
