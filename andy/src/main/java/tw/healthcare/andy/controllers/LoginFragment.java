package tw.healthcare.andy.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.healthcare.andy.R;
import tw.healthcare.andy.entities.Setting;
import tw.healthcare.andy.services.DataSyncTask;
import tw.healthcare.andy.services.UserLoginTask;
import tw.healthcare.andy.views.LoginView;
import tw.healthcare.andy.views.LoginViewImpl;

public class LoginFragment extends Fragment implements LoginView.LoginViewListener, UserLoginTask.UserLoginTaskListener, DataSyncTask.DataSyncTaskListener {

    private LoginView view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = new LoginViewImpl(inflater, container);
        view.setLoginViewListener(this);
        return view.getRootView();
    }

    @Override
    public void onLogin(String email, String password) {
        new UserLoginTask(email, password, this).execute((Void) null);
    }

    @Override
    public void onLoginSuccessful(Long userId) {
        Setting.putLong(Setting.CURRENT_USER, userId);
        new DataSyncTask(this).execute((Void)null);
    }

    private void loadHomeView() {
        Long nurseId = Setting.getLong(Setting.CURRENT_USER);
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new tw.healthcare.andy.controllers.HomeFragment.BundleBuilder().nurseId(nurseId).build();
        homeFragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.main_container, homeFragment).commit();
    }

    @Override
    public void onLoginFailure(String error) {
        view.showLoginError(error);
    }

    @Override
    public void onSyncSuccessful() {
        loadHomeView();
    }

    @Override
    public void onSyncFailed(Exception e) {
        view.showLoginError(e.getMessage());
        Log.e(getClass().getName(), e.getMessage(), e);
    }
}
