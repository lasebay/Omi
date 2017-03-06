package a404_notfound.sourceappwater.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joshua on 3/1/2017.
 */

public class ReportsHolder {
    private static HashMap<Integer, Report> holder = new HashMap<>();
    private static int i = 0;

    public static void addReport(Report report) {
        Integer id = new Integer(i);
        holder.put(id, report);
        i++;
    }

    public static Report getReport(Integer i) {
        return holder.get(i);
    }

    public static String getHolder() {

        String list = "";
        for (Map.Entry<Integer, Report> entry : holder.entrySet()) {
            list = list + entry.getValue()
                    + "\n";
        }
        return list;
    }


}
