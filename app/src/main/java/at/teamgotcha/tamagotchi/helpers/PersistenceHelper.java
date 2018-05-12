package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import at.teamgotcha.tamagotchi.enums.Gender;
import at.teamgotcha.tamagotchi.pets.Pet;

public class PersistenceHelper {
    private static PetDB dbhelper;
    private static final int PET_INDEX = 0;

    private static SQLiteDatabase getWriteableDb(Context context)
    {
        if (dbhelper == null) {
            dbhelper = new PetDB(context);
        }

        return dbhelper.getWritableDatabase();
    }

    private static SQLiteDatabase getReadableDB(Context context)
    {
        if (dbhelper == null) {
            dbhelper = new PetDB(context);
        }

        return dbhelper.getReadableDatabase();
    }

    public static void SavePet(Pet pet, Context context)
    {
        if(PetSaved(pet,context)) {
            UpdatePet(pet, context);
            return;
        }
        SQLiteDatabase db = getWriteableDb(context);
        SQLiteStatement statement = db.compileStatement("INSERT INTO pet VALUES(?,?,?,?,?,?)");
        statement.bindString(1,String.valueOf(PET_INDEX));
        statement.bindDouble(2, pet.getHealth());
        statement.bindDouble(3, pet.getMood());
        statement.bindDouble(4, pet.getHunger());
        statement.bindString(5, pet.getName());
        statement.bindString(6,String.valueOf(pet.getGender().value));
        statement.executeInsert();
    }

    private static void UpdatePet(Pet pet, Context context)
    {
        SQLiteDatabase db = getWriteableDb(context);
        SQLiteStatement statement = db.compileStatement("UPDATE pet SET health = ?, mood = ?, hunger = ?, name = ?, gender = ? WHERE id = ?");
        statement.bindDouble(1, pet.getHealth());
        statement.bindDouble(2, pet.getMood());
        statement.bindDouble(3, pet.getHunger());
        statement.bindString(4, pet.getName());
        statement.bindString(5,String.valueOf(pet.getGender().value));
        statement.bindString(6,String.valueOf(PET_INDEX));
        statement.executeUpdateDelete();
    }

    private static boolean PetSaved(Pet p, Context context)
    {
        SQLiteDatabase db = getReadableDB(context);
        Cursor cursor = db.rawQuery("SELECT * FROM pet WHERE id = ?",new String[]{String.valueOf(PET_INDEX)});
        return cursor.getCount() > 0;
    }

    public static PetValues GetPet(Context context)
    {
        SQLiteDatabase db = getReadableDB(context);
        Cursor c = db.rawQuery("SELECT * FROM pet WHERE id = ?",new String[]{String.valueOf(PET_INDEX)});
        PetValues pv = null;
        if(c.getCount() > 0)
        {
            pv = new PetValues();
            pv.setHealth(c.getFloat(1));
            pv.setMood(c.getFloat(2));
            pv.setHunger(c.getFloat(3));
            pv.setName(c.getString(4));
            pv.setGender(Gender.values()[c.getInt(5)]);
        }
        c.close();
        return pv;
    }
}
