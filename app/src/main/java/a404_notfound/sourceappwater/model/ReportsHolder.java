package a404_notfound.sourceappwater.model;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joshua on 3/1/2017.
 */

public class ReportsHolder {
    private static final SparseArray<Report> holder = new SparseArray<>();
    private static int i = 0;

    public static void addReport(Report report) {
        Integer id = Integer.valueOf(i);
        holder.put(id, report);
        i++;
    }

    public static Report getReport(Integer i) {
        return holder.get(i);
    }

    public static HashMap getReportList() {
        return holder;
    }

    public static String getHolder() {

        String list = "";
        for (int i = 0; i < holder.size(); i++) {
            list = list + holder.valueAt(i)
                    + "\n";
        }
        return list;
    }


}
