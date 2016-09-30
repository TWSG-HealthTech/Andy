package tw.healthcare.andy.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.healthcare.andy.R;
import tw.healthcare.andy.entities.Nurse;
import tw.healthcare.andy.entities.VisitingSchedule;

public class HomeViewImpl extends RecyclerView.Adapter<VisitingScheduleView> implements HomeView {

    private View rootView;
    private Nurse nurse;
    private List<VisitingSchedule> schedules;
    private HomeViewListener listener;

    @BindView(R.id.hm_nurse_name)
    TextView nurseNameView;
    @BindView(R.id.hm_schedule_date)
    TextView scheduleDateView;
    @BindView(R.id.hm_schedule_list)
    RecyclerView scheduleListView;

    public HomeViewImpl(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.view_home, container, false);
        ButterKnife.bind(this, rootView);

        schedules = new ArrayList<>();
        scheduleListView.setAdapter(this);
    }

    // implementation of HomeView

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void showSchedulesOfNurse(Collection<VisitingSchedule> schedules, Nurse nurse) {
        this.nurse = nurse;
        this.schedules.clear();
        this.schedules.addAll(schedules);
        updateUI();
    }

    private void updateUI() {
        nurseNameView.setText(nurse.getName());
        notifyDataSetChanged();
    }

    @Override
    public void setHomeViewListener(HomeViewListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.hm_sync)
    public void onClickSyncButton() {
        if (listener != null) {
            listener.onSyncData();
        }
    }

    // implementation of Adapter

    @Override
    public VisitingScheduleView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VisitingScheduleView(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(VisitingScheduleView holder, int position) {
        final VisitingSchedule schedule = schedules.get(position);
        holder.showVisitingSchedule(schedule);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickSchedule(schedule);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }
}
