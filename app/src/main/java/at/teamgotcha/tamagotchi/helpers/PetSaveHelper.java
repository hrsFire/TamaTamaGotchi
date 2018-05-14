package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.pets.Pet;

public class PetSaveHelper implements PetObserver, Runnable {
    private Pet pet;
    private Context context;

    public PetSaveHelper(Context c)
    {
        context = c;
    }

    @Override
    public void changed(Pet value) {
        pet = value;

        if (pet != null) {
            PersistenceHelper.savePet(pet, context);
        }
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        if(pet != null) {
            new Thread(this).start();
        }
    }

    @Override
    public void run() {
        PersistenceHelper.savePet(pet, context);
    }
}
