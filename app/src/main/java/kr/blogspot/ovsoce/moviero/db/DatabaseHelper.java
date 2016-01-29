package kr.blogspot.ovsoce.moviero.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.main.vo.ProgramDataImpl;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

public class DatabaseHelper extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "schedule.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_PROGRAM_SCHEDULE = "program_schedule";

    //private static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + DATABASE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String KEY_ID = "_id";
    private static final String KEY_SCHEDULE_ID="scheduleId";
    private static final String KEY_PROGRAM_MASTER_ID="programMasterId";
    private static final String KEY_SCHEDULE_NAME="scheduleName";
    private static final String KEY_BEGIN_DATE="beginDate";
    private static final String KEY_BEGIN_TIME="beginTime";
    private static final String KEY_END_TIME="endTime";
    private static final String KEY_RUNTIME="runtime";
    private static final String KEY_LARGE_GENRE_ID="largeGenreId";
    private static final String KEY_EPISODE_NO="episodeNo";
    private static final String KEY_LIVE="live";
    private static final String KEY_REBROADCAST="rebroadcast";
    private static final String KEY_HD="hd";
    private static final String KEY_AUDIO="audio";
    private static final String KEY_SCREEN_EXPLAIN="screenExplain";
    private static final String KEY_CAPTION="caption";
    private static final String KEY_AGE_RATING="ageRating";
    private static final String KEY_SUBTITLE="subtitle";
    private static final String KEY_SIGN_LANGUAGE="signLanguage";
    private static final String KEY_CHANNEL_NAME="channelName";
    private static final String KEY_NOTIFICATIONS_VALUE="notificationsValue";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_PROGRAM_SCHEDULE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_SCHEDULE_ID + " TEXT,"
                + KEY_PROGRAM_MASTER_ID + " TEXT,"
                + KEY_SCHEDULE_NAME + " TEXT,"
                + KEY_BEGIN_DATE + " TEXT,"
                + KEY_BEGIN_TIME + " TEXT,"
                + KEY_END_TIME + " TEXT,"
                + KEY_RUNTIME + " TEXT,"
                + KEY_LARGE_GENRE_ID + " TEXT,"
                + KEY_EPISODE_NO + " TEXT,"
                + KEY_LIVE + " TEXT,"
                + KEY_REBROADCAST + " TEXT,"
                + KEY_HD + " TEXT,"
                + KEY_AUDIO + " TEXT,"
                + KEY_SCREEN_EXPLAIN + " TEXT,"
                + KEY_CAPTION + " TEXT,"
                + KEY_AGE_RATING + " TEXT,"
                + KEY_SUBTITLE + " TEXT,"
                + KEY_SIGN_LANGUAGE + " TEXT,"
                + KEY_CHANNEL_NAME + " TEXT,"
                + KEY_NOTIFICATIONS_VALUE + " TEXT"
                + ")";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAM_SCHEDULE);

        // Create tables again
        onCreate(db);
    }

    public boolean insertProgramData(ProgramData data) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_SCHEDULE_ID, data.getScheduleId());
            values.put(KEY_PROGRAM_MASTER_ID, data.getProgramMasterId());
            values.put(KEY_SCHEDULE_NAME, data.getScheduleName());
            values.put(KEY_BEGIN_DATE, data.getBeginDate());
            values.put(KEY_BEGIN_TIME, data.getBeginTime());
            values.put(KEY_END_TIME, data.getEndTime());
            values.put(KEY_RUNTIME, data.getRuntime());
            values.put(KEY_LARGE_GENRE_ID, data.getLargeGenreId());
            values.put(KEY_EPISODE_NO, data.getEpisodeNo());
            values.put(KEY_LIVE, data.getLive());
            values.put(KEY_REBROADCAST, data.getRebroadcast());
            values.put(KEY_HD, data.getHd());
            values.put(KEY_AUDIO, data.getAudio());
            values.put(KEY_SCREEN_EXPLAIN, data.getScreenExplain());
            values.put(KEY_CAPTION, data.getCaption());
            values.put(KEY_AGE_RATING, data.getAgeRating());
            values.put(KEY_SUBTITLE, data.getSubtitle());
            values.put(KEY_SIGN_LANGUAGE, data.getSignLanguage());
            values.put(KEY_CHANNEL_NAME, data.getChannelName());
            values.put(KEY_NOTIFICATIONS_VALUE, data.getNotificationsValue());

            // Inserting Row
            db.insert(TABLE_PROGRAM_SCHEDULE, null, values);
            db.close(); // Closing database connection
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateNotificationsValue(String scheduleId, String value) {
        Log.d("value = " + value);
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_NOTIFICATIONS_VALUE, value);
            // Inserting Row
            db.update(TABLE_PROGRAM_SCHEDULE, contentValues, KEY_SCHEDULE_ID + "=?", new String[]{scheduleId});
            db.close(); // Closing database connection
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteProgaramData(ProgramData data) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_PROGRAM_SCHEDULE, KEY_ID + " = ?", new String[]{data.getScheduleId()});
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ProgramData getProgramData(String scheduleId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_PROGRAM_SCHEDULE,
                new String[]{
                        KEY_ID,
                        KEY_SCHEDULE_ID,
                        KEY_PROGRAM_MASTER_ID,
                        KEY_SCHEDULE_NAME,
                        KEY_BEGIN_DATE,
                        KEY_BEGIN_TIME,
                        KEY_END_TIME,
                        KEY_RUNTIME,
                        KEY_LARGE_GENRE_ID,
                        KEY_EPISODE_NO,
                        KEY_LIVE,
                        KEY_REBROADCAST,
                        KEY_HD,
                        KEY_AUDIO,
                        KEY_SCREEN_EXPLAIN,
                        KEY_CAPTION,
                        KEY_AGE_RATING,
                        KEY_SUBTITLE,
                        KEY_SIGN_LANGUAGE,
                        KEY_CHANNEL_NAME,
                        KEY_NOTIFICATIONS_VALUE},
                KEY_SCHEDULE_ID + "=?",
                new String[]{scheduleId},
                null, null, null, null);

        ProgramDataImpl data = new ProgramDataImpl();

        //cursor.moveToNext();

        //Log.d("cursor.getString(0) = " + cursor.getString(0));

        if(cursor.moveToNext()) {
            Log.d("cursor.getString(0) = " + cursor.getString(0));
            data.setScheduleId(cursor.getString(1));
            data.setProgramMasterId(cursor.getString(2));
            data.setScheduleName(cursor.getString(3));
            data.setBeginDate(cursor.getString(4));
            data.setBeginTime(cursor.getString(5));
            data.setEndTime(cursor.getString(6));
            data.setRuntime(cursor.getString(7));
            data.setLargeGenreId(cursor.getString(8));
            data.setEpisodeNo(cursor.getString(9));
            data.setLive(cursor.getString(10));
            data.setRebroadcast(cursor.getString(11));
            data.setHd(cursor.getString(12));
            data.setAudio(cursor.getString(13));
            data.setScreenExplain(cursor.getString(14));
            data.setCaption(cursor.getString(15));
            data.setAgeRating(cursor.getString(16));
            data.setSubtitle(cursor.getString(17));
            data.setSignLanguage(cursor.getString(18));
            data.setChannelName(cursor.getString(19));
            data.setNotificationsValue(cursor.getString(20));
        }

        cursor.close();
        db.close();

        return data;
    }
    public ArrayList<ProgramData> getProgramDataList() {
        ArrayList<ProgramData> list = new ArrayList<>();
        // Select All Query

        try {
            String selectQuery = "SELECT  * FROM " + TABLE_PROGRAM_SCHEDULE;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    ProgramDataImpl contact = new ProgramDataImpl();

                    contact.setScheduleId(cursor.getString(1));
                    contact.setProgramMasterId(cursor.getString(2));
                    contact.setScheduleName(cursor.getString(3));
                    contact.setBeginDate(cursor.getString(4));
                    contact.setBeginTime(cursor.getString(5));
                    contact.setEndTime(cursor.getString(6));
                    contact.setRuntime(cursor.getString(7));
                    contact.setLargeGenreId(cursor.getString(8));
                    contact.setEpisodeNo(cursor.getString(9));
                    contact.setLive(cursor.getString(10));
                    contact.setRebroadcast(cursor.getString(11));
                    contact.setHd(cursor.getString(12));
                    contact.setAudio(cursor.getString(13));
                    contact.setScreenExplain(cursor.getString(14));
                    contact.setCaption(cursor.getString(15));
                    contact.setAgeRating(cursor.getString(16));
                    contact.setSubtitle(cursor.getString(17));
                    contact.setSignLanguage(cursor.getString(18));
                    contact.setChannelName(cursor.getString(19));
                    contact.setNotificationsValue(cursor.getString(20));
                    // Adding contact to list
                    list.add(contact);
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
        } catch (Exception e) {
            //Log.e("all_contact", "" + e);
            e.printStackTrace();
        }

        return list;
    }
    public ArrayList<ProgramData> getNotificationsList() {
        ArrayList<ProgramData> list = new ArrayList<>();

        // Select All Query

        try {
            String selectQuery = "SELECT  * FROM " + TABLE_PROGRAM_SCHEDULE;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            int a = 0;
            Log.d("out");
            if (cursor.moveToFirst()) {
                Log.d("in");
                do {
                    if( a < 5) {
                        Log.d("첫번째 value = " + cursor.getString(20));

                    }
                    a++;
                    if(!cursor.getString(20).equals("-1")) {
                        ProgramDataImpl contact = new ProgramDataImpl();
                        contact.setScheduleId(cursor.getString(1));
                        contact.setProgramMasterId(cursor.getString(2));
                        contact.setScheduleName(cursor.getString(3));
                        contact.setBeginDate(cursor.getString(4));
                        contact.setBeginTime(cursor.getString(5));
                        contact.setEndTime(cursor.getString(6));
                        contact.setRuntime(cursor.getString(7));
                        contact.setLargeGenreId(cursor.getString(8));
                        contact.setEpisodeNo(cursor.getString(9));
                        contact.setLive(cursor.getString(10));
                        contact.setRebroadcast(cursor.getString(11));
                        contact.setHd(cursor.getString(12));
                        contact.setAudio(cursor.getString(13));
                        contact.setScreenExplain(cursor.getString(14));
                        contact.setCaption(cursor.getString(15));
                        contact.setAgeRating(cursor.getString(16));
                        contact.setSubtitle(cursor.getString(17));
                        contact.setSignLanguage(cursor.getString(18));
                        contact.setChannelName(cursor.getString(19));
                        contact.setNotificationsValue(cursor.getString(20));
                        list.add(contact);
                    }
                } while (cursor.moveToNext());
            }

            // return contact list
            cursor.close();
            db.close();
        } catch (Exception e) {
            //Log.e("all_contact", "" + e);
            e.printStackTrace();
        }

        return list;
    }
    public Long queryNumEntries() {
        return DatabaseUtils.queryNumEntries(getReadableDatabase(), TABLE_PROGRAM_SCHEDULE);
    }
/*
    public void exportDB(Context context) {
        File file = new File(context.getExternalFilesDir(null), DATABASE_NAME);

        try {
            InputStream is = new FileInputStream(context.getDatabasePath(DATABASE_NAME));
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
            Log.d(file.toString());
        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.e(e.toString());

        }
    }
*/
/*
    public boolean isTable() {
        String sQuery = "select count(*) from sqlite_master Where Name = 'program_schedule'";
        Cursor c = this.getReadableDatabase().rawQuery(sQuery, null);
        String tableName = null;
        if(c.moveToFirst()) {
            tableName = c.getString(0);
        }
        Log.d("tableName = " + tableName);
        if(tableName != null) {
            return true;
        }

        return false;
    }
*/

}
