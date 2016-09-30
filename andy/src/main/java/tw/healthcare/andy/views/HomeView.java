package tw.healthcare.andy.views;

import android.view.View;

import java.util.Collection;

import tw.healthcare.andy.entities.Nurse;
import tw.healthcare.andy.entities.VisitingSchedule;

public interface HomeView {
    public interface HomeViewListener {
        void onSyncData();

        void onClickSchedule(VisitingSchedule schedule);
    }

    View getRootView();

    void showSchedulesOfNurse(Collection<VisitingSchedule> schedules, Nurse nurse);

    void setHomeViewListener(HomeViewListener listener);
}
