package andy.app.models;

public class VitalRecord {
    public int height;
    public int weight;
    public double temperature;
    public int pulse;
    public int upperBloodPressure;
    public int lowerBloodPressure;

    public VitalRecord() {
        height = 175;
        weight = 75;
        temperature = 37.5;
        pulse = 90;
        upperBloodPressure = 120;
        lowerBloodPressure = 80;
    }
}
