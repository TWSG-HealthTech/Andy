package tw.healthcare.andy.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tw.healthcare.andy.R;
import tw.healthcare.andy.entities.VisitingSchedule;

public class VisitingScheduleView extends RecyclerView.ViewHolder {

    @BindView(R.id.vs_patient)
    TextView patientNameView;

    public VisitingScheduleView(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.view_visiting_schedule, container, false));
        ButterKnife.bind(this, itemView);
    }

    public void showVisitingSchedule(VisitingSchedule schedule) {
        patientNameView.setText(schedule.getPatient().getName());
    }
}
