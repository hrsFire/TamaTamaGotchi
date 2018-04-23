package at.teamgotcha.pets;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import at.teamgotcha.tamagotchi.R;

public abstract class Pet {

    // pet's current view
    protected View currentView;

    // pet's appearance
    protected ImageView appearance;

    // pet's background
    protected ImageView background;

    // pet's nutrition
    protected final static int MAX_HUNGER = 100;
    protected final static int MIN_HUNGER = 0;
    protected final static int CRITICAL_HUNGER = 20;
    protected final static int INITIAL_HUNGER = 50;

    protected int currentHunger;



    public Pet(){

        currentHunger = INITIAL_HUNGER;
    }

    public Pet(View view){

        this();

        currentView = view;
    }

    // Methods
    public View getView(){

        return currentView;
    }

    public Context getContext(){

        return currentView.getContext();
    }

    public ImageView getMyLook(){

        return appearance;
    }

    public ImageView getMyBackground(){

        return background;
    }

    // Hunger Games ;-)
    public int getMyHunger(){

        return currentHunger;
    }

    public void updateHunger(int change){

        currentHunger += change;
    }

    // String content crucial for notification
    public String isHungry(){

        if(currentHunger >= MAX_HUNGER){

            currentHunger = MAX_HUNGER;
            return currentView.getContext().getString(R.string.hunger_full);

        } else if(currentHunger <= CRITICAL_HUNGER){

            return currentView.getContext().getString(R.string.hunger_critical);

        } else if(currentHunger <= MIN_HUNGER){

            currentHunger = MIN_HUNGER;
            return currentView.getContext().getString(R.string.hunger_empty);
        }

        return null;
    }

}
