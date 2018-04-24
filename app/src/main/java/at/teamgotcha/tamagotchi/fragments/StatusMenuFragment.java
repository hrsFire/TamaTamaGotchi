package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.StatusMenuContract;

public class StatusMenuFragment extends ContractV4Fragment<StatusMenuContract> implements PetObserver {
    private BootstrapThumbnail genderButton;
    private TextView nameTextBox;

    public StatusMenuFragment() {
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
        View view = inflater.inflate(R.layout.fragment_status_menu, container, false);
        genderButton = view.findViewById(R.id.gender_button);
        nameTextBox = view.findViewById(R.id.name_textbox);

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

    private void setListeners() {
        genderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        nameTextBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void changed(Pet value) {
        // @todo: update fragment
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        // @todo: update fragment
    }
}