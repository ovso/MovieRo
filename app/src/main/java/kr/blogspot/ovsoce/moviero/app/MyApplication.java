package kr.blogspot.ovsoce.moviero.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import kr.blogspot.ovsoce.moviero.db.DatabaseHelper;

/**
 * Created by ovso on 2016. 1. 27..
 */
public class MyApplication extends Application {

    private DatabaseHelper databaseHelper;
    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        databaseHelper = new DatabaseHelper(getApplicationContext());
    }
}
