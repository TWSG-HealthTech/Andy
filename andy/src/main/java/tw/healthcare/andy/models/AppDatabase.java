package tw.healthcare.andy.models;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "Andy";
    public static final int VERSION = 1;
}
