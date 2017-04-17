package a404_notfound.sourceappwater.model;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import a404_notfound.sourceappwater.R;

/**
 * Adapter that manages the Report Cards , handles
 * loading and handles addition of new reports
 * also contains Report Format
 * Created by josh9 on 3/24/2017.
 */

public class ReportViewAdapter extends RecyclerView.Adapter<ReportViewAdapter.ReportViewHolder> {
    //private SparseArray<Report> holder;
    private List<Report> userReport;
    private final List<Report> holder;

    /**
     * Adapter manages the large dataset of reports
     * @param holder the list of all the currnet reports
     */
    public ReportViewAdapter(List<Report> holder) {
        this.holder = holder;
    }   // override inspections because recycle view needs a collection to function

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_cards, parent, false);
        return new ReportViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReportViewHolder reportViewHolder, int position) {

        if (("Worker").equals(holder.get(position).getReportType())
                && (("Manager").equals(FirbaseUtility.getRole()))) {
            reportViewHolder.author.setText(holder.get(position).toString());
        } else {
            reportViewHolder.author.setText(holder.get(position).toString());
        }
//        reportViewHolder.date.setText(holder.valueAt(position).getDate());
//        reportViewHolder.waterType.setText(holder.valueAt(position).getWaterType());
//        reportViewHolder.waterCondition.setText(holder.valueAt(position).getWaterCondition());
//        reportViewHolder.coordinates.setText(holder.valueAt(position).getCoordinates().toString());
//        reportViewHolder.reportId.setText(holder.valueAt(position).getId());
    }

    @Override
    public int getItemCount() {
        return holder.size();
    }

    static class ReportViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView author;
        TextView date;
        TextView waterType;
        TextView waterCondition;
        TextView coordinates;
        TextView reportId;
        ReportViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            author = (TextView) itemView.findViewById(R.id.author);
//            date = (TextView) itemView.findViewById(R.id.date);
//            waterType = (TextView) itemView.findViewById(R.id.type);
//            waterCondition = (TextView) itemView.findViewById(R.id.condition);
//            coordinates = (TextView) itemView.findViewById(R.id.coordinates);
//            reportId = (TextView) itemView.findViewById(R.id.reportId);
        }
    }
}
