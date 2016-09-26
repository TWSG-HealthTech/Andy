package tw.healthcare.andy.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tw.healthcare.andy.R;
import tw.healthcare.andy.databinding.FragmentLoginBinding;
import tw.healthcare.andy.models.Nurse;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private Nurse user;

    @BindView(R.id.email)
    EditText emailText;
    @BindView(R.id.password)
    EditText passwordText;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        user = new Nurse();
        binding.setUser(user);
        binding.setInProgress(false);
        View view = binding.getRoot();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.email_sign_in_button)
    public void login(Button button) {
        Log.i("sample test", "Clicking button " + button.getText());
        Log.i("sample test", "Current user " + user);
        Log.i("sample test", "Current email " + emailText.getText());
        Log.i("sample test", "Current password " + passwordText.getText());

    }

}
