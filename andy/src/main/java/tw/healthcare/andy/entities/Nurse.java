package tw.healthcare.andy.entities;

import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class, allFields = true)
public class Nurse extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private Long id;
    private Long externalId;
    private String email;
    private String name;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id=" + id +
                ", externalId=" + externalId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static Nurse findById(Long nurseId) {
        return SQLite.select().from(Nurse.class).where(Nurse_Table.id.is(nurseId)).querySingle();
    }
}
