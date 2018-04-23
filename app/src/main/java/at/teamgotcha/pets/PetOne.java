package at.teamgotcha.pets;

import android.content.Context;
import android.graphics.BitmapFactory;

import at.teamgotcha.tamagotchi.R;

public class PetOne extends Pet {
    private PetOne(){
        super();
    }

    public PetOne(Context context){
        super(context);

        appearance = BitmapFactory.decodeResource(context.getResources(), R.drawable.pet_squid);
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
    }
}