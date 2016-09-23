package andy.app.models;

import java.util.List;

public class Patient {
    public String name;
    public int age;
    public List<VitalRecord> vitals;

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
