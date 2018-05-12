package at.teamgotcha.tamagotchi.fragments;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.beardedhen.androidbootstrap.BootstrapButton;

import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.base.ContractV4Fragment;
import at.teamgotcha.tamagotchi.interfaces.contracts.VolumeContract;

public class VolumeFragment  extends ContractV4Fragment<VolumeContract> {
    private SeekBar volumebar;
    private BootstrapButton okButton;

    private AudioManager audioManager;

    public VolumeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_volume, container, false);

        volumebar = view.findViewById(R.id.volumebar);
        okButton = view.findViewById(R.id.ok_button);

        initialize();
        setListeners();

        return view;
    }

    private void initialize() {
        audioManager = (AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumebar.setMax(maxVolume);
        volumebar.setProgress(currentVolume);
    }

    private void setListeners() {
        volumebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContract().closeVolumeView();
            }
        });
    }
}