package at.teamgotcha.tamagotchi.fragments;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;

import java.io.IOException;
import java.util.EnumSet;

import at.teamgotcha.tamagotchi.helpers.AnimHelper;
import at.teamgotcha.tamagotchi.helpers.BluetoothHelper;
import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.MultiplayerInteractionContract;

public class MultiPlayerInteractionFragment extends ContractV4Fragment<MultiplayerInteractionContract> implements PetObserver {
    private BootstrapThumbnail communicationButton;
    private BootstrapThumbnail playingButton;
    private BootstrapThumbnail sendGiftButton;
    private BootstrapThumbnail haveFunButton;
    private Pet pet;

    public MultiPlayerInteractionFragment() {
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
        View view = inflater.inflate(R.layout.fragment_multi_player_interaction, container, false);

        communicationButton = view.findViewById(R.id.communication_button);
        playingButton = view.findViewById(R.id.playing_button);
        sendGiftButton = view.findViewById(R.id.send_gift_button);
        haveFunButton = view.findViewById(R.id.have_fun_button);

        setListeners();
        pet = getContract().getPetObserver().getObject();
        changed(pet);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        getContract().getPetObserver().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        getContract().getPetObserver().register(this);
    }

    @Override
    public void changed(Pet value) {
        changed(EnumSet.allOf(PetProperties.class));
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        for (PetProperties property : properties) {
            switch (property) {
                // @todo: update fragment
            }
        }
    }

    private void setListeners() {
        communicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(communicationButton.getContext()));

                Toast.makeText(getContext(), R.string.communicate, Toast.LENGTH_SHORT).show();

                try {
                    BluetoothDevice device = getContract().getActiveBluetoothDevice();
                    BluetoothHelper.sendData("this is a test", device);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        playingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(playingButton.getContext()));

                Toast.makeText(getContext(), R.string.playing, Toast.LENGTH_SHORT).show();
            }
        });

        sendGiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(sendGiftButton.getContext()));

                Toast.makeText(getContext(), R.string.send_gift, Toast.LENGTH_SHORT).show();
            }
        });

        haveFunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(haveFunButton.getContext()));

                Toast.makeText(getContext(), R.string.have_fun, Toast.LENGTH_SHORT).show();
            }
        });
    }
}