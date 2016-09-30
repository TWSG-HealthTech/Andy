package tw.healthcare.andy.services;

import android.os.AsyncTask;

import java.util.List;

import tw.healthcare.andy.services.dtos.NurseDto;

public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

    private String email;
    private String password;
    private UserLoginTaskListener listener;
    private Long loginedUserId;
    private String error;

    public UserLoginTask(String email, String password, UserLoginTaskListener listener) {
        this.email = email;
        this.password = password;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            ChanseyEndpoints endpoints = EndpointFactory.getChanseyEndpoints();
            List<NurseDto> allNurses = endpoints.getAllNurses().execute().body();
            for (NurseDto dto : allNurses) {
                if (dto.getEmail().equals(email)) {
                    loginedUserId = dto.getId();
                    return true;
                }
            }
            error = "No such user!";
            return false;
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean successful) {
        if (successful) {
            listener.onLoginSuccessful(loginedUserId);
        } else {
            listener.onLoginFailure(error);
        }
    }

    public interface UserLoginTaskListener {
        void onLoginSuccessful(Long userId);

        void onLoginFailure(String error);
    }
}
