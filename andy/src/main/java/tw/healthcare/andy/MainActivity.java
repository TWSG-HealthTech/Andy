package tw.healthcare.andy;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tw.healthcare.andy.entities.VisitingSchedule;
import tw.healthcare.andy.models.Nurse;
import tw.healthcare.andy.models.Patient;
import tw.healthcare.andy.models.VisitSchedule;
import tw.healthcare.andy.models.VitalRecord;
import tw.healthcare.andy.services.ScheduleRepository;
import tw.healthcare.andy.views.HomeFragment;
import tw.healthcare.andy.views.LoginFragment;
import tw.healthcare.andy.views.PatientItemRecyclerViewAdapter;
import tw.healthcare.andy.views.VitalRecordFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener,
        PatientItemRecyclerViewAdapter.PatientItemViewListener,
        VitalRecordFragment.VitalRecordFragmentListener {

    private ScheduleRepository repository;
    private VisitSchedule schedule;
    private Nurse nurse;
    private Calendar today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new ScheduleRepository(getApplicationContext());

        // loadLoginView();

        loadNewHomeView();
    }

    private void loadNewHomeView() {
        // prepare some fake data
        tw.healthcare.andy.entities.Nurse nurse = new tw.healthcare.andy.entities.Nurse();
        nurse.setName("Chansey");
        nurse.save();

        tw.healthcare.andy.entities.Patient patient = new tw.healthcare.andy.entities.Patient();
        patient.setName("Andy");
        patient.setGender("Male");
        patient.save();

        VisitingSchedule schedule = new VisitingSchedule();
        schedule.setAppointmentDate(new Date());
        schedule.setNurse(nurse);
        schedule.setPatient(patient);
        schedule.save();

        // work on the real thing
        tw.healthcare.andy.controllers.HomeFragment homeFragment = new tw.healthcare.andy.controllers.HomeFragment();
        Bundle args = new tw.healthcare.andy.controllers.HomeFragment.BundleBuilder().nurseId(nurse.getId()).build();
        homeFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, homeFragment).commit();
    }

    private void loadLoginView() {
        LoginFragment loginView = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, loginView).commit();
    }

    @Override
    public void onLogin(Nurse nurse) {
        this.nurse = nurse;
        today = Calendar.getInstance();

        if(repository.existSchedule(nurse.getId(), today)) {
            schedule = repository.getSchedule(nurse.getId(), today);
        } else {
            schedule = createFakeSchedule(nurse);
            repository.saveSchedule(nurse.getId(), today, schedule);
        }

        loadHomeView(schedule);
    }

    private void loadHomeView(VisitSchedule schedule) {
        HomeFragment homeView = new HomeFragment();
        homeView.setSchedule(schedule);
        homeView.setListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, homeView).commit();
    }

    @Override
    public void onPatientItemClick(Patient patient) {
        Log.i("test", "clicked on patient " + patient);
        loadVitalRecordView(patient);
    }

    private void loadVitalRecordView(Patient patient) {
        VitalRecordFragment vitalRecordView = new VitalRecordFragment();
        vitalRecordView.setPatient(patient);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, vitalRecordView);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onVitalRecordSaved() {
        repository.saveSchedule(nurse.getId(), today, schedule);
        getSupportFragmentManager().popBackStack();
    }

    private VisitSchedule createFakeSchedule(Nurse nurse) {
        VisitSchedule schedule = new VisitSchedule();
        schedule.setScheduledDate(new Date());
        schedule.setNurse(nurse);
        schedule.setPatients(createFakePatients());
        return schedule;
    }

    private List<Patient> createFakePatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("1", "Jim", 56, "male", new VitalRecord()));
        patients.add(new Patient("2", "Alice", 49, "female", new VitalRecord()));
        patients.add(new Patient("3", "David", 75, "male", new VitalRecord()));
        patients.add(new Patient("4", "Tommy", 63, "male", new VitalRecord()));
        patients.add(new Patient("5", "Bob", 50, "male", new VitalRecord()));
        return patients;
    }
}
