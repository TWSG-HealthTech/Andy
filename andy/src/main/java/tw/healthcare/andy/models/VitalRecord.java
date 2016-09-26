package tw.healthcare.andy.models;

import java.util.Date;

public class VitalRecord {
    private String height;
    private String weight;
    private String temperature;
    private String pulse;
    private String upperBloodPressure;
    private String lowerBloodPressure;
    private Date dateCreated;

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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getUpperBloodPressure() {
        return upperBloodPressure;
    }

    public void setUpperBloodPressure(String upperBloodPressure) {
        this.upperBloodPressure = upperBloodPressure;
    }

    public String getLowerBloodPressure() {
        return lowerBloodPressure;
    }

    public void setLowerBloodPressure(String lowerBloodPressure) {
        this.lowerBloodPressure = lowerBloodPressure;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "VitalRecord{" +
                "height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", temperature='" + temperature + '\'' +
                ", pulse='" + pulse + '\'' +
                ", upperBloodPressure='" + upperBloodPressure + '\'' +
                ", lowerBloodPressure='" + lowerBloodPressure + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
