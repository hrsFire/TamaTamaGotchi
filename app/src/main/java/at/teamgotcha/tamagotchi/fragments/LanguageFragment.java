package at.teamgotcha.tamagotchi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;

import at.teamgotcha.tamagotchi.MainActivity;
import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.helpers.LanguageHelper;
import at.teamgotcha.tamagotchi.interfaces.contracts.LanguageContract;

public class LanguageFragment extends ContractV4Fragment<LanguageContract> {
    private BootstrapButton germanButton;
    private BootstrapButton englishButton;

    public LanguageFragment() {
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
        View view = inflater.inflate(R.layout.fragment_language, container, false);

        germanButton = view.findViewById(R.id.german_button);
        englishButton = view.findViewById(R.id.english_button);

        setListeners();

        return view;
    }

    private void setListeners() {
        germanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("de");
            }
        });

        englishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
            }
        });
    }

    private void setLanguage(String language) {
        LanguageHelper.setLocale(language, getActivity().getClass(), getContext(), getActivity());
    }
}