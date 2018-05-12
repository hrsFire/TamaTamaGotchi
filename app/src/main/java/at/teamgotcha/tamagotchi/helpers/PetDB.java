package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import junit.runner.Version;

public class PetDB extends SQLiteOpenHelper{
    private static final String DB_NAME = "tamatamagotchi.db";
    private static final int VERSION = 1;

    public PetDB(Context context) {
        super(context, DB_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE pet(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "health REAL NOT NULL," +
                "mood REAL NOT NULL," +
                "hunger REAL NOT NULL," +
                "name TEXT NOT NULL," +
                "gender INTEGER NOT NULL);");

        sqLiteDatabase.execSQL("CREATE TABLE setting(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "language TEXT NOT NULL);");

        // insert a settings row
        sqLiteDatabase.execSQL("INSERT INTO setting(language) VALUES(\"\");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE pet");

        // for the future
        /*if (VERSION == 2) {
            sqLiteDatabase.execSQL("DROP TABLE setting");
        }*/
    }
}