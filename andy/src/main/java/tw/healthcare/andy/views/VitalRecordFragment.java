package tw.healthcare.andy.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.healthcare.andy.R;
import tw.healthcare.andy.databinding.FragmentVitalRecordBinding;
import tw.healthcare.andy.models.Patient;

public class VitalRecordFragment extends Fragment {

    private Patient patient;
    private VitalRecordFragmentListener listener;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof VitalRecordFragmentListener) {
            listener = (VitalRecordFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentVitalRecordBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vital_record, container, false);
        binding.setPatient(patient);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_save_vitals)
    public void OnSaveVitals() {
        if(listener != null) {
            listener.onVitalRecordSaved();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface VitalRecordFragmentListener {
        void onVitalRecordSaved();
    }
}
