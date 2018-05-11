package at.teamgotcha.tamagotchi;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.LinkedList;
import java.util.List;

import at.teamgotcha.tamagotchi.common.FragmentEntry;
import at.teamgotcha.tamagotchi.common.Icons;
import at.teamgotcha.tamagotchi.fragments.LanguageFragment;
import at.teamgotcha.tamagotchi.fragments.RestartFragment;
import at.teamgotcha.tamagotchi.enums.CustomPermissions;
import at.teamgotcha.tamagotchi.helpers.BluetoothHelper;
import at.teamgotcha.tamagotchi.helpers.BroadcastHelper;
import at.teamgotcha.tamagotchi.helpers.IntentHelper;
import at.teamgotcha.tamagotchi.helpers.PermissionHelper;
import at.teamgotcha.tamagotchi.helpers.PersistenceHelper;
import at.teamgotcha.tamagotchi.helpers.PetSaveHelper;
import at.teamgotcha.tamagotchi.helpers.PetValues;
import at.teamgotcha.tamagotchi.helpers.ViewHelper;
import at.teamgotcha.tamagotchi.interfaces.contracts.LanguageContract;
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

import static at.teamgotcha.tamagotchi.helpers.BluetoothHelper.REQUEST_ENABLE_BT;

public class MainActivity extends AppCompatActivity implements SettingsContract, RestartContract, PetBackgroundContract, PetSpriteContract,
        HelpContract, MoodMenuContract, MultiplayerInteractionContract, SinglePlayerInteractionContract, StatusMenuContract,
        LanguageContract {
    private BootstrapButton settingsButton;
    private BootstrapButton connectionButton;
    private BootstrapButton helpButton;
    private BootstrapButton shareButton;
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
    private PetSaveHelper petSaveHelper;
    private boolean isMultiplayerActive = false;
    private boolean bluetoothVisibilityRequested = false;

    private FragmentManager fragmentManager;
    private List<FragmentEntry> mainOverlayFragmentList;
    private BroadcastHelper mBroadcasterHelper;

    // https://stackoverflow.com/questions/9693755/detecting-state-changes-made-to-the-bluetoothadapter
    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        enableDisableMultiplayer(false);
                        break;
                    case BluetoothAdapter.STATE_ON:
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        enableDisableMultiplayer(true);
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register for broadcasts on BluetoothAdapter state change
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bluetoothReceiver, filter);

        Icons.setContext(getApplicationContext());

        // create a new pet
        PetValues pv = PersistenceHelper.GetPet(this);
        if(pv != null)
        {
            PetOne po = new PetOne(pv);
        }
        else
        {
            pet = new PetOne();
            pet.setName("Name");
        }
        petSaveHelper= new PetSaveHelper(this);
        pet.register(petSaveHelper);


        TypefaceProvider.registerDefaultIconSets();

        // disable the action bar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        settingsButton = findViewById(R.id.settings_button);
        connectionButton = findViewById(R.id.connection_button);
        helpButton = findViewById(R.id.help_button);
        shareButton = findViewById(R.id.share_button);
        shopButton = findViewById(R.id.shop_button);

        // https://stackoverflow.com/questions/17118339/how-do-i-retrieve-an-instance-of-a-fragment-defined-in-xml
        fragmentManager = getSupportFragmentManager();

        settingsFragment = fragmentManager.findFragmentById(R.id.settings_fragment);
        helpFragment = fragmentManager.findFragmentById(R.id.help_fragment);
        singlePlayerInteractionFragment = fragmentManager.findFragmentById(R.id.single_player_interaction_fragment);
        multiPlayerInteractionFragment = fragmentManager.findFragmentById(R.id.multi_player_interaction_fragment);
        mainBackgroundFragment = fragmentManager.findFragmentById(R.id.pet_fragment);
        petspriteFragment = fragmentManager.findFragmentById(R.id.petsprite_fragment);
        statusMenuFragment = fragmentManager.findFragmentById(R.id.status_menu_fragment);
        restartFragment = fragmentManager.findFragmentById(R.id.restart_fragment);

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

        mainOverlayFragmentList = new LinkedList<>();
        setListeners();

        // check if the bluetooth adapter is enabled
        BluetoothAdapter bluetoothAdapter = BluetoothHelper.getBluethoothAdapter();

        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            enableDisableMultiplayer(true);
        } else {
            enableDisableMultiplayer(false);
        }

        // (redundant at this point)
        // get bluetooth permissions
        // PermissionHelper.getAllBluetoothPermissions(this);

        // Broadcast
        mBroadcasterHelper = new BroadcastHelper();

        // Register BroadcastHelper in application
        registerReceiver(mBroadcasterHelper, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister broadcast listeners
        unregisterReceiver(bluetoothReceiver);
        pet.unregister(petSaveHelper);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                // successful
                if (resultCode > 0) {
                    enableDisableMultiplayer(true);
                } else {
                    enableDisableMultiplayer(false);
                }
        }
    }

    private void disableSettingsView() {
        ViewHelper.setVisibility(settingsLayout, false);
    }

    private void disableHelpView() {
        ViewHelper.setVisibility(helpLayout, false);
    }

    private void showMainOverlayLayout() {
        disableSettingsView();
        ViewHelper.setVisibility(mainOverlayLayout, true);

        mainOverlayLayout.post(new Runnable() {
            @Override
            public void run() {
                ViewHelper.setXYHalf(mainOverlayLayout, getView());
            }
        });
    }

    private void disableMainOverlayLayout() {
        disableSettingsView();
        ViewHelper.setVisibility(mainOverlayLayout, false);
    }

    private View getView() {
        return getWindow().getDecorView().getRootView();
    }

    private void setListeners() {
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

                if (!isMultiplayerActive) {
                    BluetoothHelper.makeDiscoverable(MainActivity.this, 20*60);
                } else {
                    enableDisableMultiplayer(false);
                }
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

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableSettingsView();
                disableHelpView();

                IntentHelper.startTextIntent(getView().getContext(), "Try TamaTamagotchi ;-)");
            }
        });

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableSettingsView();
                disableHelpView();
            }
        });

        mainOverlayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do nothing
            }
        });
    }

    private void enableDisableMultiplayer(boolean targetState) {
        if (targetState) {
            BluetoothAdapter adapter = BluetoothHelper.getBluethoothAdapter();

            if (adapter != null && !adapter.isEnabled() && !bluetoothVisibilityRequested) {
                isMultiplayerActive = true;
                bluetoothVisibilityRequested = true;
                BluetoothHelper.makeDiscoverable(MainActivity.this, 20*60);
            } else {
                isMultiplayerActive = true;
                connectionButton.setText(R.string.disconnect);
                ViewHelper.setVisibility(multiPlayerInteractionLayout, true);
                ViewHelper.setVisibility(singlePlayerInteractionLayout, false);
            }
        } else {
            isMultiplayerActive = false;
            bluetoothVisibilityRequested = false;
            connectionButton.setText(R.string.connect);
            ViewHelper.setVisibility(multiPlayerInteractionLayout, false);
            ViewHelper.setVisibility(singlePlayerInteractionLayout, true);
        }
    }

    private void disableMainOverlay() {
        ViewHelper.setVisibility(mainOverlayLayout, false);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for (FragmentEntry fragment : mainOverlayFragmentList) {
            if (fragment.isActive()) {
                transaction.remove(fragment.getFragment());
                fragment.setActive(false);
            }
        }

        transaction.commit();
    }

    private <T extends Fragment> FragmentEntry getFirstOrDefaultMainOverlayFragment(Class<T> clazz) {
        for (FragmentEntry entry : mainOverlayFragmentList) {
            if (!entry.isActive() && clazz.isInstance(entry.getFragment())) {
                return entry;
            }
        }

        try {
            T instance = clazz.newInstance();
            FragmentEntry fragmentEntry = new FragmentEntry(instance);
            mainOverlayFragmentList.add(fragmentEntry);
            return fragmentEntry;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    // shared settings functions
    @Override
    public void showRestartDialog() {
        disableSettingsView();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentEntry fragmentEntry = getFirstOrDefaultMainOverlayFragment(RestartFragment.class);
        fragmentEntry.setActive(true);
        fragmentTransaction.add(R.id.main_overlay_layout, fragmentEntry.getFragment());
        fragmentTransaction.commit();
        showMainOverlayLayout();
    }

    @Override
    public void showLanguageDialog() {
        disableSettingsView();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentEntry fragmentEntry = getFirstOrDefaultMainOverlayFragment(LanguageFragment.class);
        fragmentEntry.setActive(true);
        fragmentTransaction.add(R.id.main_overlay_layout, fragmentEntry.getFragment());
        fragmentTransaction.commit();
        showMainOverlayLayout();
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


    // Permission Stuff
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        if(requestCode == CustomPermissions.DEFAULT_REQUEST_CODE.getPermissionValue()) {

            if(PermissionHelper.validateResponse(grantResults)){

                // Success
                // @todo
            }
            else{

                // Failure
                // @todo
            }

        }
        else if(requestCode == CustomPermissions.BLUETOOTH_REQUEST_CODE.getPermissionValue()){

            // @todo
        }
        else if(requestCode == CustomPermissions.BLUETOOTH_PRIVILED_REQUEST_CODE.getPermissionValue()){

            // @todo
        }
        else if(requestCode == CustomPermissions.BLUETOOTH_ADMIN_REQUEST_CODE.getPermissionValue()){

            // @todo
        }
        else if(requestCode == CustomPermissions.ALL_BLUETOOTH_REQUEST_CODE.getPermissionValue()){

            // @todo
        }

    }
}