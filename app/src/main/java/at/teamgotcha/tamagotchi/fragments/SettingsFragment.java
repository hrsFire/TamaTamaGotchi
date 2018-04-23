package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beardedhen.androidbootstrap.BootstrapButton;

import at.teamgotcha.helpers.AnimHelper;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.interfaces.SettingsContractable;

public class SettingsFragment extends ContractV4Fragment<SettingsContractable> {
    private BootstrapButton restartButton;
    private BootstrapButton languageButton;
    private BootstrapButton volumeButton;
    private BootstrapButton notificationButton;
    private BootstrapButton selectPetButton;

    public SettingsFragment() {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        restartButton = view.findViewById(R.id.restart_button);
        languageButton = view.findViewById(R.id.language_button);
        volumeButton = view.findViewById(R.id.volume_button);
        notificationButton = view.findViewById(R.id.notification_button);
        selectPetButton = view.findViewById(R.id.select_pet_button);

        setListeners();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setListeners() {
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContract().showRestartDialog();
                v.startAnimation(AnimHelper.AddAlphaAnimation(restartButton.getContext()));
            }
        });

        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContract().showLanguageDialog();
                v.startAnimation(AnimHelper.AddAlphaAnimation(languageButton.getContext()));
            }
        });

        volumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContract().showVolumeDialog();
                v.startAnimation(AnimHelper.AddAlphaAnimation(volumeButton.getContext()));
            }
        });

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContract().showNotificationDialog();
                v.startAnimation(AnimHelper.AddAlphaAnimation(notificationButton.getContext()));
            }
        });

        selectPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContract().showSelectPetDialog();
                v.startAnimation(AnimHelper.AddAlphaAnimation(selectPetButton.getContext()));
            }
        });
    }
}