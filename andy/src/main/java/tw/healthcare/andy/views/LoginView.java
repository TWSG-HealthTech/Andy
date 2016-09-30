package tw.healthcare.andy.views;

import android.view.View;

public interface LoginView {

    public interface LoginViewListener {
        void onLogin(String email, String password);
    }

    View getRootView();

    void setLoginViewListener(LoginViewListener listener);

    void showLoginError(String error);
}
