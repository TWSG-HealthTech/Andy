package tw.healthcare.andy.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.healthcare.andy.R;
import tw.healthcare.andy.entities.VitalRecord;
import tw.healthcare.andy.utils.DataConverter;

public class VitalRecordViewImpl implements VitalRecordView {

    private View rootView;
    private VitalRecord record;
    private VitalRecordViewListener listener;

    @BindView(R.id.vr_patient)
    TextView patientNameView;
    @BindView(R.id.vr_height)
    EditText heightText;
    @BindView(R.id.vr_weight)
    EditText weightText;
    @BindView(R.id.vr_temperature)
    EditText temperatureText;
    @BindView(R.id.vr_pulse)
    EditText pulseText;
    @BindView(R.id.vr_blood_pressure_high)
    EditText bpHighText;
    @BindView(R.id.vr_blood_pressure_low)
    EditText bpLowText;

    public VitalRecordViewImpl(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.view_vital_record, container, false);
        ButterKnife.bind(this, rootView);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void showVitalRecord(VitalRecord record) {
        this.record = record;
        updateUI();
    }

    private void updateUI() {
        patientNameView.setText(record.getPatient().getName());
        heightText.setText(DataConverter.toTwoDecimalPlacesString(record.getHeight()));
        weightText.setText(DataConverter.toTwoDecimalPlacesString(record.getWeight()));
        pulseText.setText(DataConverter.toString(record.getPulse()));
        temperatureText.setText(DataConverter.toTwoDecimalPlacesString(record.getTemperature()));
        bpLowText.setText(DataConverter.toString(record.getBloodPressureLow()));
        bpHighText.setText(DataConverter.toString(record.getBloodPressureHigh()));
    }

    @Override
    public void setVitalRecordViewListener(VitalRecordViewListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.vr_save)
    public void onSaveVitalRecord() {
        updateState();
        if (listener != null && record != null) {
            listener.onSaveVitalRecord(record);
        }
    }

    private void updateState() {
        if (record != null) {
            record.setHeight(DataConverter.toDouble(heightText.getText().toString()));
            record.setWeight(DataConverter.toDouble(weightText.getText().toString()));
            record.setPulse(DataConverter.toInteger(pulseText.getText().toString()));
            record.setTemperature(DataConverter.toDouble(temperatureText.getText().toString()));
            record.setBloodPressureHigh(DataConverter.toInteger(bpHighText.getText().toString()));
            record.setBloodPressureLow(DataConverter.toInteger(bpLowText.getText().toString()));
        }
    }

}
