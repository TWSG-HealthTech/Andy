package tw.healthcare.andy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tw.healthcare.andy.views.LoginFragment;

public class MainActivity extends AppCompatActivity {

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
}
