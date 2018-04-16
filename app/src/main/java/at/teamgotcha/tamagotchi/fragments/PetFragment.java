package at.teamgotcha.tamagotchi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import at.teamgotcha.tamagotchi.R;

public class PetFragment extends Fragment {
    private ImageView pet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet,container,false);
        pet = view.findViewById(R.id.pet_image);

        return view;

    }
}