package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
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
    private Pet currentPet;
    private ImageView backgroundView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet,container,false);

        backgroundView = view.findViewById(R.id.pet_image);
        currentPet = getContract().getPetObserver().getObject();

        setListeners();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        getContract().getPetObserver().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getContract().getPetObserver().unregister(this);
    }

    @Override
    public void changed(Pet value) {
        currentPet = value;
        changed(EnumSet.allOf(PetProperties.class));
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        // @todo: update fragment

        if (properties.contains(PetProperties.BACKGROUND)) {
            backgroundView.setImageBitmap(currentPet.getBackground());
        }
    }

    private void setListeners() {
    }
}
