package tw.healthcare.andy.services.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VitalsDto {
    private Long id;
    @JsonProperty(value = "patient_id")
    private Long patientId;
    private String height;
    private String weight;
    private Integer pulse;
    private String temperature;
    @JsonProperty(value = "bp_high")
    private Integer bloodpressureHigh;
    @JsonProperty(value = "bp_low")
    private Integer bloodpressureLow;
    @JsonProperty(value = "created_at")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Integer getBloodpressureHigh() {
        return bloodpressureHigh;
    }

    public void setBloodpressureHigh(Integer bloodpressureHigh) {
        this.bloodpressureHigh = bloodpressureHigh;
    }

    public Integer getBloodpressureLow() {
        return bloodpressureLow;
    }

    public void setBloodpressureLow(Integer bloodpressureLow) {
        this.bloodpressureLow = bloodpressureLow;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
