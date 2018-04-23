package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.EnumSet;

import at.teamgotcha.helpers.AnimHelper;
import at.teamgotcha.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.HelpContract;

public class HelpFragment extends ContractV4Fragment<HelpContract> implements PetObserver {
    private BootstrapButton faqButton;
    private BootstrapButton tutorialButton;

    public HelpFragment() {
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
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        faqButton = view.findViewById(R.id.faq_button);
        tutorialButton = view.findViewById(R.id.tutorial_button);

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
        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddAlphaAnimation(v.getContext()));
            }
        });

        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimHelper.AddAlphaAnimation(v.getContext()));
            }
        });
    }
}