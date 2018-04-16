package at.teamgotcha.tamagotchi.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import at.teamgotcha.tamagotchi.R;

public class PetSpriteFragment extends Fragment {
    private ImageView petsprite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_petsprite,container,false);
        petsprite = view.findViewById(R.id.petsprite_image);
        return view;

    }
}