package at.teamgotcha.tamagotchi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.contracts.PetBackgroundContract;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;

// BACKGROUND ...
public class PetFragment extends ContractV4Fragment<PetBackgroundContract> implements PetObserver {
    private Pet pet;
    private ImageView backgroundView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet,container,false);

        backgroundView = view.findViewById(R.id.pet_image);

        setListeners();
        pet = getContract().getPetObserver().getObject();
        changed(pet);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        getContract().getPetObserver().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        getContract().getPetObserver().register(this);
    }

    @Override
    public void changed(Pet value) {
        pet = value;
        changed(EnumSet.of(PetProperties.BACKGROUND));
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        for (PetProperties property : properties) {
            switch (property) {
                case BACKGROUND:
                    backgroundView.setImageBitmap(pet.getBackground());
                    break;
            }
        }
    }

    private void setListeners() {
    }
}
