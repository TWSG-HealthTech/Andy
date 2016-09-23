package andy.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yingzhang.firstapp.R;

import andy.app.fragments.HomeFragment;
import andy.app.fragments.LoginFragment;
import andy.app.fragments.VitalsFragment;
import andy.app.models.Patient;
import andy.app.models.User;
import andy.app.models.VitalRecord;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener,
        HomeFragment.PatientListListener,
        VitalsFragment.VitalsFragmentListener {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Capture Vitals");

        loadLoginFragment();
    }

    private void loadLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, loginFragment).commit();
    }

    @Override
    public void onVitalsSaved(VitalRecord record) {
        Log.i(getClass().getName(), "Saved record " + record);

        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onLoginSuccessful(User user) {
        currentUser = user;
        user.patients = new ArrayList<>();
        user.patients.add(new Patient("Tom", 64));
        user.patients.add(new Patient("Jack", 42));
        user.patients.add(new Patient("Danny", 54));

        loadPatientListFragment();
    }

    private void loadPatientListFragment() {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setUser(currentUser);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, homeFragment).commit();
    }

    private void loadVitalsFragment(Patient patient) {
        VitalsFragment vitalsFragment = new VitalsFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, vitalsFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        vitalsFragment.setPatientName(patient.name);
        vitalsFragment.setVitalRecord(new VitalRecord());
    }

    public void syncData(View view) {
        // TODO: this callback should not be here
        Log.i(getClass().getName(), "Data sync is not implemented yet!!!");
    }

    @Override
    public void onListFragmentInteraction(Patient item) {
        loadVitalsFragment(item);
    }
}
