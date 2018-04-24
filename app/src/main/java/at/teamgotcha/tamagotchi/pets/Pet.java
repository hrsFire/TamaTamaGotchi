package at.teamgotcha.tamagotchi.pets;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ObservableSubject;
import at.teamgotcha.tamagotchi.enums.PetProperties;

public abstract class Pet extends ObservableSubject<Pet,PetProperties> {
    // pet's current view
    protected Context context;

    // pet's appearance
    protected Bitmap appearance;

    // pet's background
    protected Bitmap background;

    // pet's nutrition
    protected final static int MAX_HUNGER = 100;
    protected final static int MIN_HUNGER = 0;
    protected final static int CRITICAL_HUNGER = 20;
    protected final static int INITIAL_HUNGER = 10;

    protected int currentHunger;

    public Pet(){
        super(PetProperties.class);
        setObject(this);
        currentHunger = INITIAL_HUNGER;
    }

    public Pet(Context context){
        this();
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

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

    // HUNGER Games ;-)
    public int getMyHunger() {
        return currentHunger;
    }

    public void updateHunger(int change) {
        currentHunger += change;
        addChangedProperties(EnumSet.of(PetProperties.HUNGER));
    }

    // String content crucial for notification
    public String isHungry() {
        if(currentHunger >= MAX_HUNGER){
            currentHunger = MAX_HUNGER;
            // return currentView.getContext().getString(R.string.hunger_full);
        } else if(currentHunger <= CRITICAL_HUNGER){
            return context.getString(R.string.hunger_critical);
        } else if(currentHunger <= MIN_HUNGER){
            currentHunger = MIN_HUNGER;
            // return currentView.getContext().getString(R.string.hunger_empty);
        }

        return null;
    }
}