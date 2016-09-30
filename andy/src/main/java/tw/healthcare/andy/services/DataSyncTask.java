package tw.healthcare.andy.services;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import tw.healthcare.andy.entities.Nurse;
import tw.healthcare.andy.entities.Patient;
import tw.healthcare.andy.entities.VisitingSchedule;
import tw.healthcare.andy.entities.VitalRecord;
import tw.healthcare.andy.services.converters.EntityDtoConverter;
import tw.healthcare.andy.services.dtos.NurseDto;
import tw.healthcare.andy.services.dtos.PatientDto;
import tw.healthcare.andy.services.dtos.ScheduleDto;
import tw.healthcare.andy.services.dtos.VitalsDto;

public class DataSyncTask extends AsyncTask<Void, Void, Boolean> {

    private DataSyncTaskListener listener;
    private ChanseyEndpoints endpoints;
    private List<NurseDto> nurseDtos;
    private List<ScheduleDto> scheduleDtos;
    private List<PatientDto> patientDtos;
    private List<VitalsDto> vitalsDtos;
    private Exception error;

    public DataSyncTask(DataSyncTaskListener listener) {
        this.listener = listener;
        endpoints = EndpointFactory.getChanseyEndpoints();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            uploadNewRecords();
            fetchServerData();
            resetDB();
            populateDB();
            return true;
        } catch (Exception e) {
            error = e;
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean successful) {
        if (listener == null) {
            return;
        }

        if (successful) {
            listener.onSyncSuccessful();
        } else {
            listener.onSyncFailed(error);
        }
    }

    private void uploadNewRecords() throws Exception {
        List<VitalRecord> allNewRecords = VitalRecord.findAllNewRecords();
        for (VitalRecord record : allNewRecords) {
            Log.i(getClass().getName(), "Uploading " + record);
            Response<ResponseBody> response = endpoints.postVitalRecord(EntityDtoConverter.toVitalsDto(record)).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("NonOK server response " + response.code() + " for posting record " + record);
            }
            record.setModified(false);
            record.save();
        }
    }

    private void fetchServerData() throws Exception {
        nurseDtos = endpoints.getAllNurses().execute().body();
        patientDtos = endpoints.getAllPatients().execute().body();
        scheduleDtos = endpoints.getAllSchedules().execute().body();
        vitalsDtos = endpoints.getAllVitalRecords().execute().body();
    }

    private void resetDB() {
        VitalRecord.removeAll();
        VisitingSchedule.removeAll();
        Patient.removeAll();
        Nurse.removeAll();
    }

    private void populateDB() {
        for (NurseDto dto : nurseDtos) {
            EntityDtoConverter.toNurse(dto).save();
        }

        for (PatientDto dto : patientDtos) {
            EntityDtoConverter.toPatient(dto).save();
        }

        for (ScheduleDto dto : scheduleDtos) {
            VisitingSchedule schedule = EntityDtoConverter.toSchedule(dto);
            schedule.setNurse(Nurse.findById(dto.getNurseId()));
            schedule.setPatient(Patient.findById(dto.getPatientId()));
            schedule.save();
        }

        for (VitalsDto dto : vitalsDtos) {
            VitalRecord record = EntityDtoConverter.toVitalRecord(dto);
            record.setPatient(Patient.findById(dto.getPatientId()));
            record.save();
        }
    }

    public interface DataSyncTaskListener {
        void onSyncSuccessful();

        void onSyncFailed(Exception e);
    }
}
