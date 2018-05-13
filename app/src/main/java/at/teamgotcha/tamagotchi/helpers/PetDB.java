package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetDB extends SQLiteOpenHelper{
    private static final String DB_NAME = "tamatamagotchi.db";
    private static final int VERSION = 1;

    // pet
    protected static final String PET_TABLE = "pet";
    protected static final String PET_NAME_COLUMN = "name";
    protected static final String PET_HEALTH_COLUMN = "health";
    protected static final String PET_MOOD_COLUMN = "mood";
    protected static final String PET_HUNGER_COLUMN = "hunger";
    protected static final String PET_GENDER_COLUMN = "gender";
    protected static final String PET_APPEARANCE_COLUMN = "appearance";
    protected static final String PET_BACKGROUND_COLUMN = "background";

    // settings
    protected static final String SETTINGS_TABLE = "setting";
    protected static final String SETTINGS_LANGUAGE_COLUMN = "language";

    public PetDB(Context context) {
        super(context, DB_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + PET_TABLE +"(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                PET_HEALTH_COLUMN + " REAL NOT NULL," +
                PET_MOOD_COLUMN + " REAL NOT NULL," +
                PET_HUNGER_COLUMN + " REAL NOT NULL," +
                PET_NAME_COLUMN + " TEXT NOT NULL," +
                PET_GENDER_COLUMN + " INTEGER NOT NULL," +
                PET_APPEARANCE_COLUMN + " BLOB NOT NULL," +
                PET_BACKGROUND_COLUMN + " BLOB NOT NULL);");

        sqLiteDatabase.execSQL("CREATE TABLE setting(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "language TEXT NOT NULL);");

        // insert a settings row
        sqLiteDatabase.execSQL("INSERT INTO " + SETTINGS_TABLE +"(" + SETTINGS_LANGUAGE_COLUMN + ") VALUES(\"\");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + PET_TABLE);

        // for the future
        /*if (VERSION == 2) {
            sqLiteDatabase.execSQL("DROP TABLE " + SETTINGS_TABLE);
        }*/
    }
}