package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.helpers.AnimHelper;
import at.teamgotcha.tamagotchi.helpers.NotificationHelper;
import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.PetSpriteContract;

public class PetSpriteFragment extends ContractV4Fragment<PetSpriteContract> implements PetObserver {
    private Pet currentPet;
    private ImageView spriteView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petsprite,container,false);

        spriteView = view.findViewById(R.id.petsprite_image);
        currentPet = getContract().getPetObserver().getObject();

        spriteView.setImageBitmap(currentPet.getAppearance());

        // Add Notification ...
        NotificationHelper.addPetHungerNotification(currentPet);

        setListeners();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        getContract().getPetObserver().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        getContract().getPetObserver().unregister(this);
    }

    @Override
    public void changed(Pet value) {
        currentPet = value;
        // @todo: update fragment
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        // @todo: update fragment

        if (properties.contains(PetProperties.APPEARANCE)) {
            spriteView.setImageBitmap(currentPet.getAppearance());
        }
    }

    private void setListeners()
    {
        spriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimHelper.AddShakeOneAnimation(spriteView.getContext()));
            }
        });
    }
}