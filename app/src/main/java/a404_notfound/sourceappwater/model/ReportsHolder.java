package a404_notfound.sourceappwater.model;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joshua on 3/1/2017.
 *
 * Temporary Report database. To be moved to Firebase
 */

public class ReportsHolder {
    private static final SparseArray<Report> holder = new SparseArray<>();
    private static int i = 0;

    /**
     *
     *
     * @param report the report to add the database
     */
    public static void addReport(Report report) {
        Integer id = Integer.valueOf(i);
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


}
