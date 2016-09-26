package tw.healthcare.andy.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.healthcare.andy.R;
import tw.healthcare.andy.databinding.FragmentHomeBinding;
import tw.healthcare.andy.models.VisitSchedule;

public class HomeFragment extends Fragment {

    private VisitSchedule schedule;
    private PatientItemRecyclerViewAdapter.PatientItemViewListener listener;

    @BindView(R.id.list) RecyclerView recyclerView;

    public void setListener(PatientItemRecyclerViewAdapter.PatientItemViewListener listener) {
        this.listener = listener;
    }

    public void setSchedule(VisitSchedule schedule) {
        this.schedule = schedule;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setSchedule(schedule);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.sync_data)
    public void onSyncData() {
        Log.i(getClass().getName(), "Syncing data");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(new PatientItemRecyclerViewAdapter(schedule.getPatients(), listener));
    }

}
