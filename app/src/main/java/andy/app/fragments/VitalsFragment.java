package andy.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yingzhang.firstapp.R;
import andy.app.models.VitalRecord;

public class VitalsFragment extends Fragment {

    private VitalsFragmentListener mListener;

    private String patientName;
    private VitalRecord record;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof VitalsFragmentListener) {
            mListener = (VitalsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vitals, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button saveButton = (Button) getView().findViewById(R.id.button_save_vitals);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveVitals();
            }
        });
    }

    private void onSaveVitals() {
        record.height = getTextFieldAsInt(R.id.vital_height);
        record.weight = getTextFieldAsInt(R.id.vital_weight);
        record.temperature = getTextFieldAsDouble(R.id.vital_temperature);
        record.pulse = getTextFieldAsInt(R.id.vital_pulse);
        if(mListener != null) {
            mListener.onVitalsSaved(record);
        }
    }

    private int getTextFieldAsInt(int viewId) {
        EditText textField = (EditText) getView().findViewById(viewId);
        return Integer.valueOf(textField.getText().toString());
    }

    private double getTextFieldAsDouble(int viewId) {
        EditText textField = (EditText) getView().findViewById(viewId);
        return Double.valueOf(textField.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        updatePatientName();
        updateVitals();
    }

    private void updatePatientName() {
        TextView nameView = (TextView) getView().findViewById(R.id.vital_patient_name);
        nameView.setText(patientName);
    }

    private void updateVitals() {
        setTextField(R.id.vital_height, record.height);
        setTextField(R.id.vital_weight, record.weight);
        setTextField(R.id.vital_temperature, record.temperature);
        setTextField(R.id.vital_pulse, record.pulse);
    }

    private void setTextField(int viewId, int value) {
        EditText textField = (EditText) getView().findViewById(viewId);
        textField.setText(String.valueOf(value));
    }

    private void setTextField(int viewId, double value) {
        EditText textField = (EditText) getView().findViewById(viewId);
        textField.setText(String.valueOf(value));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setVitalRecord(VitalRecord record) {
        this.record = record;
    }



    public interface VitalsFragmentListener {
        // TODO: Update argument type and name
        void onVitalsSaved(VitalRecord record);
    }
}
