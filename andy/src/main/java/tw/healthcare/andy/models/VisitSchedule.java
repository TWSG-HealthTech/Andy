package tw.healthcare.andy.models;

import java.util.Date;
import java.util.List;

public class VisitSchedule {
    private Date scheduledDate;
    private Nurse nurse;
    private List<Patient> patients;

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "VisitSchedule{" +
                "scheduledDate=" + scheduledDate +
                ", nurse=" + nurse +
                ", patients=" + patients +
                '}';
    }
}
