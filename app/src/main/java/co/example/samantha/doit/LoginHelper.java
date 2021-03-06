package co.example.samantha.doit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sam Schwartz-Horney on 11/27/2017.
 */

public class LoginHelper extends SQLiteOpenHelper {

    private static LoginHelper mInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LoginDB";
    private static final String TABLE_NAME = "Entry";
    private static final String KEY_ID = "_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TOTAL = "totalBets";

    public static synchronized LoginHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LoginHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public LoginHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_USERNAME + " TEXT, " + KEY_PASSWORD
                + " TEXT, " + KEY_EMAIL + " TEXT, "+ KEY_TOTAL + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addEntry(LoginItem entry) {
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, entry.getUsername());
        values.put(KEY_PASSWORD, entry.getPassword());
        values.put(KEY_EMAIL, entry.getEmail());
        values.put(KEY_TOTAL, entry.getTotalBets());
        SQLiteDatabase database = getWritableDatabase();
        if (database.insert(TABLE_NAME, null, values) == -1) {
            return false;
        }
        return true;
    }

    public String searchPassword(String username) {
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT " + KEY_EMAIL + ", " + KEY_PASSWORD + " FROM " + TABLE_NAME;
        String user;
        String pass = "err";
        Cursor c = database.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                user = c.getString(0);
                pass = c.getString(1);

                if (user.equals(username)) {
                    pass = c.getString(1);
                    break;
                }
            } while (c.moveToNext());
        }
        return pass;
    }

    public boolean uniqueUsername(String username) {
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT " + KEY_USERNAME + " FROM " + TABLE_NAME;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                if (c.getString(0).equals(username)) {
                    return false;
                }
            } while (c.moveToNext());
        }
        return true;
    }

    public boolean uniqueEmail(String email) {
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT " + KEY_EMAIL + " FROM " + TABLE_NAME;
        Cursor c = database.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                if (c.getString(0).equals(email)) {
                    return false;
                }
            } while (c.moveToNext());
        }
        return true;
    }

    public void deleteEntry(String username) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_USERNAME + "=\"" + username + "\";");
    }

    public Cursor getCursor() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

}
