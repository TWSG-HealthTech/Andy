package tw.healthcare.andy.entities;

import android.util.Log;

import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

@Table(database = AppDatabase.class, allFields = true)
public class VitalRecord extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private Long id;
    private Long externalId;
    @ForeignKey
    private Patient patient;
    private Double height;
    private Double weight;
    private int pulse;
    private Double temperature;
    private int bloodPressureHigh;
    private int bloodPressureLow;
    private Date dateMeasured;

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public int getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public void setBloodPressureHigh(int bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public int getBloodPressureLow() {
        return bloodPressureLow;
    }

    public void setBloodPressureLow(int bloodPressureLow) {
        this.bloodPressureLow = bloodPressureLow;
    }

    public Date getDateMeasured() {
        return dateMeasured;
    }

    public void setDateMeasured(Date dateMeasured) {
        this.dateMeasured = dateMeasured;
    }

    @Override
    public String toString() {
        return "VitalRecord{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", patient=" + patient +
                ", height=" + height +
                ", weight=" + weight +
                ", pulse=" + pulse +
                ", temperature=" + temperature +
                ", bloodPressureHigh=" + bloodPressureHigh +
                ", bloodPressureLow=" + bloodPressureLow +
                ", dateMeasured=" + dateMeasured +
                '}';
    }

    public static VitalRecord findByPatientIdAndDateMeasured(Long patientId, Date dateMeasured) {
        Date dayStart = new Date(dateMeasured.getYear(), dateMeasured.getMonth(), dateMeasured.getDate(), 0, 0, 0);
        Date dayEnd = new Date(dateMeasured.getYear(), dateMeasured.getMonth(), dateMeasured.getDate(), 23, 59, 59);

        return SQLite.select().from(VitalRecord.class)
                .where(VitalRecord_Table.patient_id.is(patientId))
                .and(VitalRecord_Table.dateMeasured.between(dayStart).and(dayEnd))
                .querySingle();
    }
}
