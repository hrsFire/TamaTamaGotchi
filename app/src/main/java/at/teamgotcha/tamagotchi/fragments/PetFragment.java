package at.teamgotcha.tamagotchi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import at.teamgotcha.pets.Pet;
import at.teamgotcha.pets.PetOne;
import at.teamgotcha.tamagotchi.R;

// Background ...
public class PetFragment extends Fragment {

    private Pet currentPet;
    private ImageView background;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet,container,false);

        background = view.findViewById(R.id.pet_image);

        currentPet = new PetOne(view);
        // updateBackground();

        return view;
    }

    public ImageView getBackground(){

        return background;
    }

    public void setBackground(ImageView nView){

        background = nView;
    }

    public void updateBackground(){

        background = currentPet.getMyBackground();
    }


}
