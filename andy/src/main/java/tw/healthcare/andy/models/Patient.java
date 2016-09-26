package tw.healthcare.andy.models;

/**
 * Created by yingzhang on 26/9/16.
 */

public class Patient {
    private String id;
    private String name;
    private String age;
    private String gender;
    private VitalRecord vitals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public VitalRecord getVitals() {
        return vitals;
    }

    public void setVitals(VitalRecord vitals) {
        this.vitals = vitals;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", vitals=" + vitals +
                '}';
    }
}
