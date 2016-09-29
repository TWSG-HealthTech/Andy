package tw.healthcare.andy.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

import tw.healthcare.andy.entities.Patient;
import tw.healthcare.andy.entities.VitalRecord;
import tw.healthcare.andy.utils.ToastUtil;
import tw.healthcare.andy.views.VitalRecordView;
import tw.healthcare.andy.views.VitalRecordViewImpl;

public class VitalRecordFragment extends Fragment implements VitalRecordView.VitalRecordViewListener {

    private static final String ARG_PATIENT_ID = "ARG_PATIENT_ID";
    private static final String ARG_DATE_MEASURED = "ARG_DATE_MEASURED";

    private VitalRecordView view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = new VitalRecordViewImpl(inflater, container);
        view.setVitalRecordViewListener(this);
        return view.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            Long patientId = getPatientId();
            Date dateMeasured = getDateMeasured();
            VitalRecord record = VitalRecord.findByPatientIdAndDateMeasured(patientId, dateMeasured);
            if (record == null) {
                record = createVitalRecord(patientId);
            }
            view.showVitalRecord(record);
        } catch (Exception e) {
            ToastUtil.showToast(getContext(), e.getMessage());
            Log.e(getClass().getName(), e.getMessage(), e);
        }
    }

    private VitalRecord createVitalRecord(Long patientId) {
        Patient patient = Patient.findById(patientId);
        if(patient == null) {
            throw new RuntimeException("No patient with ID " + patientId);
        }
        VitalRecord record = new VitalRecord();
        record.setPatient(patient);
        return record;
    }

    private Date getDateMeasured() {
        Bundle args = getArguments();
        if (!args.containsKey(ARG_DATE_MEASURED)) {
            throw new RuntimeException("No date measured is given!");
        } else {
            return new Date(args.getLong(ARG_DATE_MEASURED));
        }
    }

    private Long getPatientId() {
        Bundle args = getArguments();
        if (!args.containsKey(ARG_PATIENT_ID)) {
            throw new RuntimeException("No patient ID is given!");
        } else {
            return args.getLong(ARG_PATIENT_ID);
        }
    }

    @Override
    public void onSaveVitalRecord(VitalRecord record) {
        record.setDateMeasured(new Date());
        record.setModified(true);
        record.save();
        ToastUtil.showToast(getContext(), "Vital Record saved!");
        getFragmentManager().popBackStack();
    }

    public static class BundleBuilder {
        private Bundle bundle;

        public BundleBuilder() {
            bundle = new Bundle();
        }

        public BundleBuilder patientId(Long patientId) {
            bundle.putLong(ARG_PATIENT_ID, patientId);
            return this;
        }

        public BundleBuilder dateMeasured(Date dateMeasured) {
            bundle.putLong(ARG_DATE_MEASURED, dateMeasured.getTime());
            return this;
        }

        public Bundle build() {
            return bundle;
        }
    }
}
