package at.teamgotcha.tamagotchi.fragments;

import android.app.Fragment;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import at.teamgotcha.helpers.NotificationHelper;
import at.teamgotcha.pets.Pet;
import at.teamgotcha.pets.PetOne;
import at.teamgotcha.tamagotchi.R;

public class PetSpriteFragment extends Fragment {

    private Pet currentPet;
    private ImageView sprite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petsprite,container,false);

        sprite = view.findViewById(R.id.petsprite_image);

        currentPet = new PetOne(view);
        // updateSprite();

        // Add Notification ...
        NotificationHelper.addPetHungerNotification(currentPet);
        
        return view;
    }

    public Pet getPet(){

        return currentPet;
    }

    public void setPet(Pet nPet){

        currentPet = nPet;
    }

    public void updateSprite(){

        sprite = currentPet.getMyBackground();
    }
}
