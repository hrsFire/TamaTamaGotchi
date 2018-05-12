package at.teamgotcha.tamagotchi.pets;

import android.graphics.Bitmap;
import java.util.EnumSet;
import at.teamgotcha.tamagotchi.base.ObservableSubject;
import at.teamgotcha.tamagotchi.enums.Gender;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.helpers.PetValues;

public abstract class Pet extends ObservableSubject<Pet,PetProperties> {
    // pet's appearance
    protected Bitmap appearance;

    // pet's background
    protected Bitmap background;

    // pet's nutrition
    public final static int MAX_HUNGER = 100;
    public final static int MIN_HUNGER = 0;
    public final static int CRITICAL_HUNGER = 20;
    public final static int INITIAL_HUNGER = 10;

    public final static int MAX_HEALTH = 100;
    public final static int MIN_HEALTH = 0;
    public final static int CRITICAL_HEALTH = 20;
    public final static int INITIAL_HEALTH = 100;

    public final static int MAX_MOOD = 100;
    public final static int MIN_MOOD = 0;
    public final static int CRITICAL_MOOD = 20;
    public final static int INITIAL_MOOD = 100;

    protected float health;
    protected float mood;
    protected float hunger;
    protected String name;
    protected Gender gender;

    public Pet() {
        super(PetProperties.class);
        setObject(this);
        health = INITIAL_HEALTH;
        mood = INITIAL_MOOD;
        hunger = INITIAL_HUNGER;
    }

    public Pet(PetValues pv)
    {
        super(PetProperties.class);
        health = pv.getHealth();
        mood = pv.getMood();
        hunger = pv.getHunger();
        name = pv.getName();
        gender = pv.getGender();
        setObject(this);
    }

    // getter and setters

    public Bitmap getBackground() {
        return background;
    }

    protected void setBackground(Bitmap background) {
        this.background = background;
        addChangedProperties(EnumSet.of(PetProperties.BACKGROUND));
    }

    public Bitmap getAppearance() {
        return appearance;
    }

    public void setAppearance(Bitmap appearance) {
        this.appearance = appearance;
        addChangedProperties(EnumSet.of(PetProperties.APPEARANCE));
    }

    public float getHealth() {
        return health;
    }

    public void updateHealth(int change) {
        health += change;

        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        } else if(health < MIN_HEALTH) {
            health = MIN_HEALTH;
        }

        addChangedProperties(EnumSet.of(PetProperties.HEALTH));
    }

    public float getMood() {
        return mood;
    }

    public void updateMood(int change) {
        mood += change;

        if (mood > MAX_MOOD) {
            mood = MAX_MOOD;
        } else if(mood < MIN_MOOD) {
            mood = MIN_MOOD;
        }

        addChangedProperties(EnumSet.of(PetProperties.MOOD));
    }

    // HUNGER Games ;-)
    public float getHunger() {
        return hunger;
    }

    public void updateHunger(int change) {
        hunger += change;

        if (hunger > MAX_HUNGER) {
            hunger = MAX_HUNGER;
        } else if(hunger < MIN_HUNGER) {
            hunger = MIN_HUNGER;
        }

        addChangedProperties(EnumSet.of(PetProperties.HUNGER));
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

    // other methods

    public boolean isHungry() {
        if(hunger <= CRITICAL_HUNGER){
            return true;
        }

        return false;
    }

    public boolean isInjured(){
        if(health <= CRITICAL_HEALTH){
            return true;
        }

        return false;
    }

    public boolean isAngry(){
        if(mood <= CRITICAL_MOOD){
            return true;
        }

        return false;
    }
}