package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.Locale;
import java.util.Objects;

import at.teamgotcha.tamagotchi.enums.Gender;
import at.teamgotcha.tamagotchi.pets.Pet;

public class PersistenceHelper {
    private static PetDB dbhelper;
    private static final int PET_INDEX = 0;

    private static SQLiteDatabase getWriteableDb(Context context) {
        if (dbhelper == null) {
            dbhelper = new PetDB(context);
        }

        return dbhelper.getWritableDatabase();
    }

    private static SQLiteDatabase getReadableDB(Context context) {
        if (dbhelper == null) {
            dbhelper = new PetDB(context);
        }

        return dbhelper.getReadableDatabase();
    }

    public static void savePet(Pet pet, Context context) {
        if(petSaved(pet,context)) {
            updatePet(pet, context);
            return;
        }

        SQLiteDatabase db = getWriteableDb(context);
        SQLiteStatement statement = db.compileStatement("INSERT INTO pet VALUES(?,?,?,?,?,?)");
        statement.bindString(1, String.valueOf(PET_INDEX));
        statement.bindDouble(2, pet.getHealth());
        statement.bindDouble(3, pet.getMood());
        statement.bindDouble(4, pet.getHunger());
        statement.bindString(5, pet.getName());
        statement.bindString(6, String.valueOf(pet.getGender().value));
        statement.executeInsert();
    }

    private static void updatePet(Pet pet, Context context) {
        SQLiteDatabase db = getWriteableDb(context);
        SQLiteStatement statement = db.compileStatement("UPDATE pet SET health = ?, mood = ?, hunger = ?, name = ?, gender = ? WHERE id = ?");
        statement.bindDouble(1, pet.getHealth());
        statement.bindDouble(2, pet.getMood());
        statement.bindDouble(3, pet.getHunger());
        statement.bindString(4, pet.getName());
        statement.bindString(5, String.valueOf(pet.getGender().value));
        statement.bindString(6, String.valueOf(PET_INDEX));
        statement.executeUpdateDelete();
    }

    private static boolean petSaved(Pet p, Context context) {
        SQLiteDatabase db = getReadableDB(context);
        Cursor cursor = db.rawQuery("SELECT * FROM pet WHERE id = ?",new String[]{String.valueOf(PET_INDEX)});
        return cursor.getCount() > 0;
    }

    public static PetValues getPet(Context context) {
        SQLiteDatabase db = getReadableDB(context);
        Cursor c = db.rawQuery("SELECT * FROM pet WHERE id = ?",new String[]{String.valueOf(PET_INDEX)});
        PetValues pv = null;

        if(c.getCount() > 0) {
            c.moveToFirst();
            pv = new PetValues();
            pv.setHealth(c.getFloat(c.getColumnIndex(PetDB.PET_HEALTH_COLUMN)));
            pv.setMood(c.getFloat(c.getColumnIndex(PetDB.PET_MOOD_COLUMN)));
            pv.setHunger(c.getFloat(c.getColumnIndex(PetDB.PET_HUNGER_COLUMN)));
            pv.setName(c.getString(c.getColumnIndex(PetDB.PET_NAME_COLUMN)));
            pv.setGender(Gender.values()[c.getInt(c.getColumnIndex(PetDB.PET_GENDER_COLUMN))]);
        }

        c.close();
        return pv;
    }

    public static void updateLanguage(Locale language, Context context) {
        SQLiteDatabase db = getWriteableDb(context);
        String tmpLanguage;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            tmpLanguage = language.toLanguageTag();
        } else {
            tmpLanguage = language.toString();
        }

        SQLiteStatement statement = db.compileStatement("UPDATE setting SET language = ?");
        statement.bindString(1, tmpLanguage);
        statement.executeInsert();
    }

    public static Locale getLanguage(Context context) {
        SQLiteDatabase db = getReadableDB(context);
        Cursor c = db.rawQuery("SELECT * FROM setting", new String[] {});
        Locale language = null;

        if(c.getCount() > 0) {
            c.moveToFirst();
            String lang = c.getString(c.getColumnIndex(PetDB.SETTINGS_LANGUAGE_COLUMN));

            if (Objects.equals(lang, "")) {
                language = null;
            } else {
                language = new Locale(lang);
            }
        }

        c.close();
        return language;
    }
}
