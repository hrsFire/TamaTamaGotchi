package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        getContract().getPetObserver().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        getContract().getPetObserver().unregister(this);
    }

    @Override
    public void changed(Pet value) {
        // @todo: update fragment
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        // @todo: update fragment
    }

    private void setListeners() {
        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(nutritionButton.getContext()));
            }
        });

        bathingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(bathingButton.getContext()));
            }
        });

        sleepingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(sleepingButton.getContext()));
            }
        });

        playingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(playingButton.getContext()));
            }
        });

        strollingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(strollingButton.getContext()));
            }
        });

        makeMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(makeMusicButton.getContext()));
            }
        });
    }
}