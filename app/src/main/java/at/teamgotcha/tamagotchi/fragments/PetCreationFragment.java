package at.teamgotcha.tamagotchi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.enums.Gender;
import at.teamgotcha.tamagotchi.helpers.PersistenceHelper;
import at.teamgotcha.tamagotchi.helpers.PetValues;
import at.teamgotcha.tamagotchi.interfaces.contracts.PetCreationContract;
import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.pets.PetOne;

public class PetCreationFragment extends ContractV4Fragment<PetCreationContract> {
    private EditText nameTextbox;
    private CheckBox femaleCheckbox;
    private CheckBox maleCheckbox;
    private CheckBox petSquidheckbox;
    private CheckBox petBubblegumCheckbox;
    private ImageView petSquidImage;
    private ImageView petBubblegumImage;
    private BootstrapButton createButton;

    public PetCreationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_pet_creation, container, false);

        nameTextbox = view.findViewById(R.id.name_textbox);
        femaleCheckbox = view.findViewById(R.id.female_checkbox);
        maleCheckbox = view.findViewById(R.id.male_checkbox);
        petSquidheckbox = view.findViewById(R.id.pet_squid_checkbox);
        petBubblegumCheckbox = view.findViewById(R.id.pet_bubblegum_checkbox);
        petSquidImage = view.findViewById(R.id.pet_squid_image);
        petBubblegumImage = view.findViewById(R.id.pet_bubblegum_image);
        createButton = view.findViewById(R.id.create_button);

        initialize();
        setListeners();

        return view;
    }

    private void initialize() {
        femaleCheckbox.setChecked(true);
        petSquidheckbox.setChecked(true);
    }

    private void setListeners() {
        femaleCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleCheckbox.setChecked(false);
            }
        });

        maleCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femaleCheckbox.setChecked(false);
            }
        });

        petSquidheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petBubblegumCheckbox.setChecked(false);
            }
        });

        petBubblegumCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petSquidheckbox.setChecked(false);
            }
        });

        petSquidImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petBubblegumCheckbox.setChecked(false);
                petSquidheckbox.setChecked(true);
            }
        });

        petBubblegumImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petBubblegumCheckbox.setChecked(true);
                petSquidheckbox.setChecked(false);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTextbox.getText().toString();
                boolean isFemale = femaleCheckbox.isChecked();

                if (name.isEmpty() || name.trim().equals("")) {
                    Toast.makeText(getContext(), R.string.pet_creation_name_not_valid, Toast.LENGTH_SHORT).show();
                }

                PetValues petValues = new PetValues();
                Pet pet;
                Gender gender;

                // name
                petValues.setName(name);

                // gender
                if (isFemale) {
                    gender = Gender.FEMALE;
                } else {
                    gender = Gender.MALE;
                }

                petValues.setGender(gender);

                // add them to the pet
                // @todo: which pet
                pet = new PetOne(petValues);

                PersistenceHelper.savePet(pet, getContext());

                getContract().petCreated(pet);
            }
        });
    }
}
