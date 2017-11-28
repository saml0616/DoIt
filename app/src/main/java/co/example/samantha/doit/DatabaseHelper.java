package co.example.samantha.doit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Asus Rtrix on 11/21/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Database";

    private static final String TABLE_NAME = "Entry";

    private static final String KEY_BET_ID = "_id";
    private static final String KEY_OWNER_ID = "ownerID";
    private static final String KEY_BETMATE_ID = "betMateID";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CREATE_DATE = "createDate";
    private static final String KEY_END_DATE = "endDate";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_WAGER = "wager";
    private SQLiteDatabase db;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_BET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_OWNER_ID + " TEXT, "
                + KEY_BETMATE_ID + " TEXT, " + KEY_DESCRIPTION + " TEXT, "+ KEY_CREATE_DATE + " TEXT, "
                + KEY_END_DATE + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_WAGER + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addEntry(DataItem entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BET_ID, entry.getBetID());
        values.put(KEY_OWNER_ID, entry.getOwnerID());
        values.put(KEY_BETMATE_ID, entry.getBetMateID());
        values.put(KEY_DESCRIPTION, entry.getDescription());
        values.put(KEY_CREATE_DATE, entry.getCreateDate().toString());
        values.put(KEY_END_DATE, entry.getEndDate().toString());
        values.put(KEY_LOCATION, entry.getLocation());
        values.put(KEY_WAGER, entry.getWager());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public void deleteEntry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_BET_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public Cursor getCursor(){
        return db.rawQuery("SELECT  * FROM Entry", null);
    }
}
