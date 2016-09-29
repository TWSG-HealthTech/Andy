package tw.healthcare.andy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import tw.healthcare.andy.controllers.HomeFragment;
import tw.healthcare.andy.entities.Setting;
import tw.healthcare.andy.services.DataSyncTask;
import tw.healthcare.andy.utils.ToastUtil;

public class AndyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        prepareToLoadHomeView();
    }

    private void prepareToLoadHomeView() {
        new DataSyncTask(new DataSyncTask.DataSyncTaskListener() {
            @Override
            public void onSyncSuccessful() {
                Long currentUser = Setting.getLong(Setting.CURRENT_USER);
                if (currentUser == null) {
                    Setting.putLong(Setting.CURRENT_USER, 2L);
                }
                loadHomeView();
            }

            @Override
            public void onSyncFailed(Exception e) {
                ToastUtil.showToast(getBaseContext(), e.getMessage());
                Log.i(getClass().getName(), e.getMessage(), e);
            }
        }).execute((Void) null);
    }

    private void loadHomeView() {
        Long nurseId = Setting.getLong(Setting.CURRENT_USER);
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new tw.healthcare.andy.controllers.HomeFragment.BundleBuilder().nurseId(nurseId).build();
        homeFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, homeFragment).commit();
    }
}
