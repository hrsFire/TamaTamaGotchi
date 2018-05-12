package at.teamgotcha.tamagotchi.fragments;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.pets.Pet;

public class FAQFragment extends ContractV4Fragment<FAQFragment> implements PetObserver {
    @Override
    public void changed(Pet value) {

    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {

    }
}