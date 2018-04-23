package at.teamgotcha.pets;

import android.view.View;

import at.teamgotcha.tamagotchi.R;

public class PetOne extends Pet {

    private PetOne(){

        super();
    }

    public PetOne(View view){

        super(view);

        appearance = currentView.findViewById(R.id.petsprite_image);
        background = currentView.findViewById(R.id.pet_image);
    }


}
