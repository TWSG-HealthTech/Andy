package tw.healthcare.andy.entities;

import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class, allFields = true)
public class Setting extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private Long id;
    private String name;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public static final String CURRENT_USER = "CURRENT_USER";

    public static Long getLong(String key) {
        Setting setting = SQLite.select().from(Setting.class).where(Setting_Table.name.is(key)).querySingle();
        return setting == null ? null : Long.parseLong(setting.getValue());
    }

    public static void putLong(String key, Long value) {
        Setting setting = SQLite.select().from(Setting.class).where(Setting_Table.name.is(key)).querySingle();
        if (setting == null) {
            setting = new Setting();
            setting.setName(key);
        }
        setting.setValue(value.toString());
        setting.save();
    }
}
