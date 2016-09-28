package tw.healthcare.andy.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import tw.healthcare.andy.R;
import tw.healthcare.andy.entities.VisitingSchedule;
import tw.healthcare.andy.entities.Nurse;
import tw.healthcare.andy.utils.ToastUtil;
import tw.healthcare.andy.views.HomeView;
import tw.healthcare.andy.views.HomeViewImpl;

public class HomeFragment extends Fragment implements HomeView.HomeViewListener {

    private static final String ARG_NURSE_ID = "ARG_NURSE_ID";

    private HomeView view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = new HomeViewImpl(inflater, container);
        view.setHomeViewListener(this);
        return view.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            Long nurseId = getNurseId();
            Nurse nurse = Nurse.findById(nurseId);
            List<VisitingSchedule> schedules = VisitingSchedule.findByNurseId(nurseId);
            view.showSchedulesOfNurse(schedules, nurse);
        } catch (Exception e) {
            ToastUtil.showToast(getContext(), e.getMessage());
            Log.e(getClass().getName(), e.getMessage(), e);
        }
    }

    private Long getNurseId() {
        Bundle args = getArguments();
        if (!args.containsKey(ARG_NURSE_ID)) {
            throw new RuntimeException("No nurse ID is given!");
        } else {
            return args.getLong(ARG_NURSE_ID);
        }
    }

    // implementation of HomeViewListener

    @Override
    public void onSyncData() {
        ToastUtil.showToast(getContext(), "Data sync is done!");
    }

    @Override
    public void onClickSchedule(VisitingSchedule schedule) {
        VitalRecordFragment fragment = new VitalRecordFragment();
        Bundle args = new VitalRecordFragment.BundleBuilder()
                .patientId(schedule.getPatient().getId())
                .dateMeasured(new Date())
                .build();
        fragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static class BundleBuilder {
        private Bundle bundle;

        public BundleBuilder() {
            bundle = new Bundle();
        }

        public BundleBuilder nurseId(Long nurseId) {
            bundle.putLong(ARG_NURSE_ID, nurseId);
            return this;
        }

        public Bundle build() {
            return bundle;
        }
    }
}
