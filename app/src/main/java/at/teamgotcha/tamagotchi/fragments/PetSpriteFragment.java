package at.teamgotcha.tamagotchi.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.helpers.AnimHelper;
import at.teamgotcha.tamagotchi.helpers.PetNotificationHelper;
import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.PetSpriteContract;

public class PetSpriteFragment extends ContractV4Fragment<PetSpriteContract> implements PetObserver {
    private Pet pet;
    private View spriteView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petsprite,container,false);

        spriteView = view.findViewById(R.id.petsprite_image);
        pet = getContract().getPetObserver().getObject();
        changed(pet);

        // Add Notification ...
        PetNotificationHelper.addPetHungerNotification(getContext(), pet);

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
        changed(EnumSet.of(PetProperties.APPEARANCE));
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        for (PetProperties property : properties) {
            switch (property) {
                case APPEARANCE:
                    spriteView.setBackground(new BitmapDrawable(getResources(), pet.getAppearance()));
                    //spriteView.setBackground(new BitmapDrawable(getResources(), pet.getAppearance()));
                    break;
            }
        }
    }

    private void setListeners() {
        spriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimHelper.AddShakeOneAnimation(spriteView.getContext()));
            }
        });
    }
}