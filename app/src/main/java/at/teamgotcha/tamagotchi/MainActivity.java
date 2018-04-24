package at.teamgotcha.tamagotchi;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import at.teamgotcha.tamagotchi.helpers.ViewHelper;
import at.teamgotcha.tamagotchi.pets.Pet;
import at.teamgotcha.tamagotchi.pets.PetOne;
import at.teamgotcha.tamagotchi.base.ObservableSubject;
import at.teamgotcha.tamagotchi.enums.PetProperties;
import at.teamgotcha.tamagotchi.interfaces.contracts.HelpContract;
import at.teamgotcha.tamagotchi.interfaces.contracts.MoodMenuContract;
import at.teamgotcha.tamagotchi.interfaces.contracts.MultiplayerInteractionContract;
import at.teamgotcha.tamagotchi.interfaces.contracts.PetBackgroundContract;
import at.teamgotcha.tamagotchi.interfaces.contracts.PetSpriteContract;
import at.teamgotcha.tamagotchi.interfaces.contracts.RestartContract;
import at.teamgotcha.tamagotchi.interfaces.contracts.SettingsContract;
import at.teamgotcha.tamagotchi.interfaces.contracts.SinglePlayerInteractionContract;
import at.teamgotcha.tamagotchi.interfaces.contracts.StatusMenuContract;

public class MainActivity extends AppCompatActivity implements SettingsContract, RestartContract, PetBackgroundContract, PetSpriteContract,
        HelpContract, MoodMenuContract, MultiplayerInteractionContract, SinglePlayerInteractionContract, StatusMenuContract {
    private BootstrapButton settingsButton;
    private BootstrapButton connectionButton;
    private BootstrapButton helpButton;
    private BootstrapButton shopButton;
    private Fragment settingsFragment;
    private Fragment helpFragment;
    private Fragment singlePlayerInteractionFragment;
    private Fragment multiPlayerInteractionFragment;
    private Fragment mainBackgroundFragment;
    private Fragment petspriteFragment;
    private Fragment statusMenuFragment;
    private Fragment restartFragment;
    private View settingsLayout;
    private View helpLayout;
    private View topMenuLayout;
    private View singlePlayerInteractionLayout;
    private View multiPlayerInteractionLayout;
    private View mainOverlayLayout;

    private Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create a new pet
        // @todo:
        pet = new PetOne(getApplicationContext());

        TypefaceProvider.registerDefaultIconSets();

        // disable the action bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        settingsButton = findViewById(R.id.settings_button);
        connectionButton = findViewById(R.id.connection_button);
        helpButton = findViewById(R.id.help_button);
        shopButton = findViewById(R.id.shop_button);

        // https://stackoverflow.com/questions/17118339/how-do-i-retrieve-an-instance-of-a-fragment-defined-in-xml
        FragmentManager mgr = getSupportFragmentManager();

        settingsFragment = mgr.findFragmentById(R.id.settings_fragment);
        helpFragment = mgr.findFragmentById(R.id.help_fragment);
        singlePlayerInteractionFragment = mgr.findFragmentById(R.id.single_player_interaction_fragment);
        multiPlayerInteractionFragment = mgr.findFragmentById(R.id.multi_player_interaction_fragment);
        mainBackgroundFragment = mgr.findFragmentById(R.id.pet_fragment);
        petspriteFragment = mgr.findFragmentById(R.id.petsprite_fragment);
        statusMenuFragment = mgr.findFragmentById(R.id.status_menu_fragment);
        restartFragment = mgr.findFragmentById(R.id.restart_fragment);

        settingsLayout = findViewById(R.id.settings_layout);
        helpLayout = findViewById(R.id.help_layout);
        topMenuLayout = findViewById(R.id.top_menu_layout);
        singlePlayerInteractionLayout = findViewById(R.id.single_player_interaction_layout);
        multiPlayerInteractionLayout = findViewById(R.id.multi_player_interaction_layout);
        mainOverlayLayout = findViewById(R.id.main_overlay_layout);

        // set invisible
        ViewHelper.setVisibility(settingsLayout, false);
        ViewHelper.setVisibility(helpLayout, false);
        ViewHelper.setVisibility(multiPlayerInteractionLayout, false);
        ViewHelper.setVisibility(mainOverlayLayout, false);

        // set listeners
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHelper.switchVisibility(settingsLayout);
                disableHelpView();
                // set the correct position
                ViewHelper.setXYAbove(settingsLayout, settingsButton);
            }
        });

        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableSettingsView();
                disableHelpView();
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableSettingsView();
                ViewHelper.switchVisibility(helpLayout);
                // set it the correct position
                ViewHelper.setXYAbove(helpLayout, helpButton);
            }
        });

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableSettingsView();
                disableHelpView();
            }
        });
    }

    private void disableSettingsView() {
        ViewHelper.setVisibility(settingsLayout, false);
    }

    private void disableHelpView() {
        ViewHelper.setVisibility(helpLayout, false);
    }

    private void showMainOverlayLayout() {
        disableSettingsView();
        ViewHelper.switchVisibility(mainOverlayLayout);
        mainOverlayLayout.post(new Runnable() {
            @Override
            public void run() {
                ViewHelper.setXYHalf(mainOverlayLayout, getView());
            }
        });
    }

    private void disableMainOverlayLayout() {
        disableSettingsView();
        ViewHelper.switchVisibility(mainOverlayLayout);
    }

    private View getView() {
        return getWindow().getDecorView().getRootView();
    }

    // shared settings functions
    @Override
    public void showRestartDialog() {
        disableSettingsView();
        showMainOverlayLayout();
        // set the correct position
        // @todo:
    }

    @Override
    public void showLanguageDialog() {
        showMainOverlayLayout();
        // @todo:
    }

    @Override
    public void showVolumeDialog() {
        showMainOverlayLayout();
        // @todo:
    }

    @Override
    public void showNotificationDialog() {
        showMainOverlayLayout();
        // @todo:
    }

    @Override
    public void showSelectPetDialog() {
        showMainOverlayLayout();
        // @todo:
    }

    @Override
    public void restartGame() {
        disableMainOverlayLayout();
        // @todo:
    }

    @Override
    public void cancelRestartGame() {
        disableMainOverlayLayout();
    }

    @Override
    public ObservableSubject<Pet, PetProperties> getPetObserver() {
        return pet;
    }
}