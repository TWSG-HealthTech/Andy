package tw.healthcare.andy;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import tw.healthcare.andy.models.Nurse;
import tw.healthcare.andy.models.Patient;
import tw.healthcare.andy.models.VisitSchedule;
import tw.healthcare.andy.services.ScheduleRepository;
import tw.healthcare.andy.views.HomeFragment;
import tw.healthcare.andy.views.LoginFragment;
import tw.healthcare.andy.views.PatientItemRecyclerViewAdapter;
import tw.healthcare.andy.views.VitalRecordFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener,
        PatientItemRecyclerViewAdapter.PatientItemViewListener,
        VitalRecordFragment.VitalRecordFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLoginView();
    }

    private void loadLoginView() {
        LoginFragment loginView = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, loginView).commit();
    }

    @Override
    public void onLogin(Nurse nurse) {
        VisitSchedule schedule = ScheduleRepository.getInstance().getSchedule(nurse);
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
        getSupportFragmentManager().popBackStack();
    }
}
