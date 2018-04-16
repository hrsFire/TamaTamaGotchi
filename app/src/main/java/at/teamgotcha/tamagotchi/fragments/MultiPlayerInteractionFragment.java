package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;

import at.teamgotcha.helpers.AnimHelper;
import at.teamgotcha.tamagotchi.R;

public class MultiPlayerInteractionFragment extends Fragment {
    private BootstrapThumbnail communicationButton;
    private BootstrapThumbnail playingButton;
    private BootstrapThumbnail sendGiftButton;
    private BootstrapThumbnail haveFunButton;

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

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setListeners() {
        communicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(communicationButton.getContext()));
            }
        });

        playingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(playingButton.getContext()));
            }
        });

        sendGiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(sendGiftButton.getContext()));
            }
        });

        haveFunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddBounceAnimation(haveFunButton.getContext()));
            }
        });
    }
}