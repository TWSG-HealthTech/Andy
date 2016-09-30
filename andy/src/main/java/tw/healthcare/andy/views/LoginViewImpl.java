package tw.healthcare.andy.views;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.healthcare.andy.R;

public class LoginViewImpl implements LoginView {

    private View rootView;
    private LoginViewListener listener;

    @BindView(R.id.login_form)
    View loginFormView;
    @BindView(R.id.login_progress)
    View progressView;
    @BindView(R.id.lg_email)
    EditText emailView;
    @BindView(R.id.lg_password)
    EditText passwordView;
    @BindView(R.id.sign_in_button)
    Button loginButton;

    public LoginViewImpl(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.view_login, container, false);
        ButterKnife.bind(this, rootView);


    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void setLoginViewListener(LoginViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void showLoginError(String error) {
        showProgress(false);
        passwordView.setError(error);
    }

    @OnClick(R.id.sign_in_button)
    public void onLogin() {
        if(listener != null) {
            showProgress(true);
            listener.onLogin(emailView.getText().toString(), passwordView.getText().toString());
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}
