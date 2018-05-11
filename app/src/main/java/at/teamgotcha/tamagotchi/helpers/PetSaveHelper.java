package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.PetObserverContract;
import at.teamgotcha.tamagotchi.pets.Pet;

/**
 * Created by nikol_000 on 07.05.2018.
 */

public class PetSaveHelper implements PetObserver {
    private Pet pet;
    private Context context;

    public PetSaveHelper(Context c)
    {
        context = c;
    }

    @Override
    public void changed(Pet value) {
        pet = value;
        PersistenceHelper.SavePet(pet,context);
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
       PersistenceHelper.SavePet(pet,context);
    }
}
