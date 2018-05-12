package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.helpers.AnimHelper;
import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.SinglePlayerInteractionContract;

public class SinglePlayerInteractionFragment extends ContractV4Fragment<SinglePlayerInteractionContract> implements PetObserver {
    private BootstrapThumbnail nutritionButton;
    private BootstrapThumbnail bathingButton;
    private BootstrapThumbnail sleepingButton;
    private BootstrapThumbnail playingButton;
    private BootstrapThumbnail strollingButton;
    private BootstrapThumbnail makeMusicButton;
    private Pet pet;

    public SinglePlayerInteractionFragment() {
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
        View view = inflater.inflate(R.layout.fragment_single_player_interaction, container, false);

        nutritionButton = view.findViewById(R.id.nutrition_button);
        bathingButton = view.findViewById(R.id.bathing_button);
        sleepingButton = view.findViewById(R.id.sleeping_button);
        playingButton = view.findViewById(R.id.playing_button);
        playingButton.setBackgroundResource(R.drawable.icon_ball);
        strollingButton = view.findViewById(R.id.strolling_button);
        makeMusicButton = view.findViewById(R.id.make_music_button);

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
        changed(EnumSet.allOf(PetProperties.class));
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        for (PetProperties property : properties) {
            switch (property) {
                // feed the pet
                case HUNGER:
                    break;

                // increase the pet's moord
                case MOOD:
                    break;

                case HEALTH:
                    break;
                case GENDER:
                    break;
                case NAME:
                    break;
                case BACKGROUND:
                    break;
                case APPEARANCE:
                    break;
            }
        }
    }

    private void setListeners() {
        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(nutritionButton.getContext()));

                pet.updateHunger(10);
                pet.updateHealth(10);

                pet.notifyObservers();
                Toast.makeText(getContext(), R.string.nutrition, Toast.LENGTH_SHORT).show();
            }
        });

        bathingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(bathingButton.getContext()));

                // pet.notifyObservers();
                Toast.makeText(getContext(), R.string.bathing, Toast.LENGTH_SHORT).show();
            }
        });

        sleepingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(sleepingButton.getContext()));

                // pet.notifyObservers();
                Toast.makeText(getContext(), R.string.sleeping, Toast.LENGTH_SHORT).show();
            }
        });

        playingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(playingButton.getContext()));

                if(!pet.isHungry() && !pet.isInjured()){
                    pet.updateMood(10);
                    pet.updateHealth(-10);
                    pet.updateHunger(-10);

                    pet.notifyObservers();

                    Toast.makeText(getContext(), R.string.playing, Toast.LENGTH_SHORT).show();
                }
            }
        });

        strollingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(strollingButton.getContext()));

                pet.updateMood(20);

                pet.notifyObservers();

                Toast.makeText(getContext(), R.string.strolling, Toast.LENGTH_SHORT).show();
            }
        });

        makeMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(makeMusicButton.getContext()));

                if(!pet.isHungry()){
                    pet.updateMood(25);
                    pet.updateHunger(-15);

                    pet.notifyObservers();

                    Toast.makeText(getContext(), R.string.make_music, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}