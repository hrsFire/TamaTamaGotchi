package at.teamgotcha.tamagotchi.pets;

import at.teamgotcha.tamagotchi.common.Icons;
import at.teamgotcha.tamagotchi.helpers.PetValues;

public class PetOne extends Pet {
    public PetOne() {
        Icons icons = Icons.getInstance();

        this.appearance = icons.getSquidAppearance();
        this.background = icons.getYellowBackground();
    }

    public PetOne(PetValues pv)
    {
        super(pv);
    }
}