package at.teamgotcha.tamagotchi.pets;

import at.teamgotcha.tamagotchi.common.Icons;

public class PetOne extends Pet {
    public PetOne() {
        Icons icons = Icons.getInstance();

        this.appearance = icons.getSquidAppearance();
        this.background = icons.getYellowBackground();
    }
}