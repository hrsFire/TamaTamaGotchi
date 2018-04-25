package at.teamgotcha.tamagotchi.pets;

import android.graphics.Bitmap;

public class PetOne extends Pet {
    private PetOne(){
        super();
    }

    public PetOne(Bitmap appearance, Bitmap background) {
        this.appearance = appearance;
        this.background = background;
    }
}