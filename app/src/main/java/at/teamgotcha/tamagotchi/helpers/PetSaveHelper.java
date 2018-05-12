package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.pets.Pet;

public class PetSaveHelper implements PetObserver {
    private Pet pet;
    private Context context;

    public PetSaveHelper(Context c)
    {
        context = c;
    }

    @Override
    public void changed(Pet value) {
        if (pet != null) {
            pet = value;
            PersistenceHelper.SavePet(pet,context);
        }
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        if(pet != null) {
            PersistenceHelper.SavePet(pet,context);
        }
    }
}
