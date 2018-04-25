package at.teamgotcha.tamagotchi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapThumbnail;
import com.google.android.gms.plus.model.people.Person;

import java.util.EnumSet;

import at.teamgotcha.tamagotchi.common.Icons;
import at.teamgotcha.tamagotchi.enums.Gender;
import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.PetObserver;
import at.teamgotcha.tamagotchi.interfaces.contracts.StatusMenuContract;

public class StatusMenuFragment extends ContractV4Fragment<StatusMenuContract> implements PetObserver {
    private BootstrapThumbnail genderIndicator;
    private TextView nameTextBox;
    private Pet pet;

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

        genderIndicator = view.findViewById(R.id.gender_indicator);
        nameTextBox = view.findViewById(R.id.name_textbox);

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

    private void setListeners() {
        genderIndicator.setOnClickListener(new View.OnClickListener() {
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
        pet = value;
        changed(EnumSet.of(PetProperties.GENDER,
                PetProperties.NAME));
    }

    @Override
    public void changed(EnumSet<PetProperties> properties) {
        Icons icons = Icons.getInstance();

        for (PetProperties property : properties) {
            switch (property) {
                case GENDER:
                    Gender gender = pet.getGender();

                    if (gender == Gender.FEMALE) {
                        genderIndicator.setImageBitmap(icons.getGenderFemale());
                    } else if (gender == Gender.MALE) {
                        genderIndicator.setImageBitmap(icons.getGenderMale());
                    }

                    break;
                case NAME:
                    nameTextBox.setText(pet.getName());
                    break;
            }
        }
    }
}