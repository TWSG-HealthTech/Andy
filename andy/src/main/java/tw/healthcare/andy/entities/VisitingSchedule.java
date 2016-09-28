package tw.healthcare.andy.entities;

import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

@Table(database = AppDatabase.class, allFields = true)
public class VisitingSchedule extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private Long id;
    private Long externalId;
    private Date appointmentDate;
    @ForeignKey
    private Nurse nurse;
    @ForeignKey
    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "VisitingSchedule{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", appointmentDate=" + appointmentDate +
                ", nurse=" + nurse +
                ", patient=" + patient +
                '}';
    }

    public static List<VisitingSchedule> findByNurseId(Long nurseId) {
        return SQLite.select().from(VisitingSchedule.class).where(VisitingSchedule_Table.nurse_id.is(nurseId)).queryList();
    }
}
