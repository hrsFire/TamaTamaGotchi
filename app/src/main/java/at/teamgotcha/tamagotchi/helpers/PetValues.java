package at.teamgotcha.tamagotchi.helpers;

import at.teamgotcha.tamagotchi.enums.Gender;

/**
 * Created by nikol_000 on 07.05.2018.
 */

public class PetValues{
    protected float health;
    protected float mood;
    protected float hunger;
    protected String name;
    protected Gender gender;


    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMood() {
        return mood;
    }

    public void setMood(float mood) {
        this.mood = mood;
    }

    public float getHunger() {
        return hunger;
    }

    public void setHunger(float hunger) {
        this.hunger = hunger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
