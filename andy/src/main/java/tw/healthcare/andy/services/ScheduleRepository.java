package tw.healthcare.andy.services;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

import tw.healthcare.andy.R;
import tw.healthcare.andy.models.VisitSchedule;
import tw.healthcare.andy.utils.JsonUtil;

public class ScheduleRepository {

    private SharedPreferences prefs;

    public ScheduleRepository(Context context) {
        String prefKey = context.getString(R.string.pref_file_name);
        prefs = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
    }

    public boolean existSchedule(String nurseId, Calendar date) {
        return prefs.contains(getScheduleKey(nurseId, date));
    }

    public VisitSchedule getSchedule(String nurseId, Calendar date) {
        return JsonUtil.deserialize(
                prefs.getString(getScheduleKey(nurseId, date), null),
                VisitSchedule.class
        );
    }

    public void saveSchedule(String nurseId, Calendar date, VisitSchedule schedule) {
        prefs
                .edit()
                .putString(
                        getScheduleKey(nurseId, date),
                        JsonUtil.serialize(schedule)
                ).apply();
    }

    private String getScheduleKey(String id, Calendar date) {
        return id +
                "-" +
                date.get(Calendar.YEAR) +
                "-" +
                date.get(Calendar.MONTH) +
                "-" +
                date.get(Calendar.DAY_OF_MONTH);
    }

}
