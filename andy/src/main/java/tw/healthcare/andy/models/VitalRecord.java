package tw.healthcare.andy.models;

import java.util.Date;

public class VitalRecord {
    private int height;
    private int weight;
    private int temperature;
    private int pulse;
    private int upperBloodPressure;
    private int lowerBloodPressure;
    private Date dateCreated;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getUpperBloodPressure() {
        return upperBloodPressure;
    }

    public void setUpperBloodPressure(int upperBloodPressure) {
        this.upperBloodPressure = upperBloodPressure;
    }

    public int getLowerBloodPressure() {
        return lowerBloodPressure;
    }

    public void setLowerBloodPressure(int lowerBloodPressure) {
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
                "height=" + height +
                ", weight=" + weight +
                ", temperature=" + temperature +
                ", pulse=" + pulse +
                ", upperBloodPressure=" + upperBloodPressure +
                ", lowerBloodPressure=" + lowerBloodPressure +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
