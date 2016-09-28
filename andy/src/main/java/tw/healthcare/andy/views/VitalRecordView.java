package tw.healthcare.andy.views;

import android.view.View;

import tw.healthcare.andy.entities.VitalRecord;

public interface VitalRecordView {
    public interface VitalRecordViewListener {
        void onSaveVitalRecord(VitalRecord record);
    }

    View getRootView();

    void showVitalRecord(VitalRecord record);

    void setVitalRecordViewListener(VitalRecordViewListener listener);
}
