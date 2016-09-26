package tw.healthcare.andy.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tw.healthcare.andy.models.Nurse;
import tw.healthcare.andy.models.Patient;
import tw.healthcare.andy.models.VisitSchedule;
import tw.healthcare.andy.models.VitalRecord;

public class ScheduleRepository {

    private static ScheduleRepository instance;

    private ScheduleRepository() {

    }

    public static ScheduleRepository getInstance() {
        if(instance == null) {
            instance = new ScheduleRepository();
        }
        return instance;
    }

    public VisitSchedule getSchedule(Nurse nurse) {
        VisitSchedule schedule = new VisitSchedule();
        schedule.setScheduledDate(new Date());
        schedule.setNurse(nurse);
        schedule.setPatients(createFakePatients());
        return schedule;
    }

    private List<Patient> createFakePatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("1", "Jim", 56, "male", new VitalRecord()));
        patients.add(new Patient("2", "Alice", 49, "female", new VitalRecord()));
        patients.add(new Patient("3", "David", 75, "male", new VitalRecord()));
        patients.add(new Patient("4", "Tommy", 63, "male", new VitalRecord()));
        patients.add(new Patient("5", "Bob", 50, "male", new VitalRecord()));
        return patients;
    }
}
