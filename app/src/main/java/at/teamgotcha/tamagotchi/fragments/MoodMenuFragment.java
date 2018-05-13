package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.common.Icons;
import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.MoodMenuContract;

public class MoodMenuFragment extends ContractV4Fragment<MoodMenuContract> implements PetObserver {
    private BootstrapThumbnail healthLevelIndicator;
    private BootstrapThumbnail moodLevelIndicator;
    private BootstrapThumbnail hungerLevelIndicator;
    private Pet pet;

    public MoodMenuFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mood_menu, container, false);

        healthLevelIndicator = view.findViewById(R.id.health_level_indicator);
        moodLevelIndicator = view.findViewById(R.id.mood_level_indicator);
        hungerLevelIndicator = view.findViewById(R.id.hunger_level_indicator);

        setListeners();
        pet = getContract().getPetObserver().getObject();
        changed(pet);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        getContract().getPetObserver().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getContract().getPetObserver().unregister(this);
    }

    @Override
    public void changed(Pet value) {
        pet = value;
        changed(EnumSet.of(PetProperties.HEALTH,
                PetProperties.MOOD,
                PetProperties.HUNGER));
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        Icons icons = Icons.getInstance();

        for (PetProperties property : properties) {
            switch (property) {
                case HEALTH:
                    float health = pet.getHealth();

                    if (health <= pet.CRITICAL_HEALTH) {
                        healthLevelIndicator.setImageBitmap(icons.getBatteryEmpty());
                    } else if (health <= pet.MAX_HEALTH /4 *2) {
                        healthLevelIndicator.setImageBitmap(icons.getBatteryOneQuarter());
                    } else if (health <= pet.MAX_HEALTH /4 *3) {
                        healthLevelIndicator.setImageBitmap(icons.getBatteryHalf());
                    } else if (health <= pet.MAX_HEALTH) {
                        healthLevelIndicator.setImageBitmap(icons.getBatteryThreeQuarters());
                    } else {
                        healthLevelIndicator.setImageBitmap(icons.getBatteryFull());
                    }

                    break;
                case MOOD:
                    float mood = pet.getMood();

                    if (mood <= pet.CRITICAL_MOOD) {
                        moodLevelIndicator.setImageBitmap(icons.getMoodLow());
                    } else if (mood <= pet.MAX_MOOD / 4 *3) {
                        moodLevelIndicator.setImageBitmap(icons.getMoodAverage());
                    } else {
                        moodLevelIndicator.setImageBitmap(icons.getMoodHappy());
                    }

                    break;
                case HUNGER:
                    float hunger = pet.getHunger();
                    // @todo:
                    //hungerLevelIndicator.setImageBitmap(icons.getHunger());

                    break;
            }
        }
    }

    private void setListeners() {
    }
}