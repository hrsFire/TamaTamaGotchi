package at.teamgotcha.tamagotchi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;

import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.interfaces.contracts.FaqFragmentContract;

public class FAQFragment extends ContractV4Fragment<FaqFragmentContract> {
    private BootstrapButton okButton;

    public FAQFragment() {
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
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        okButton = view.findViewById(R.id.ok_button);

        setListeners();

        return view;
    }

    private void setListeners() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContract().closeFaqView();
            }
        });
    }
}