package at.teamgotcha.tamagotchi;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.RelativeLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import at.teamgotcha.helpers.ViewHelper;

public class MainActivity extends AppCompatActivity {
    private BootstrapButton settingsButton;
    private BootstrapButton connectionButton;
    private BootstrapButton helpButton;
    private BootstrapButton shopButton;
    private Fragment settingsFragment;
    private Fragment helpFragment;
    private View settingsLayout;
    private View helpLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        settingsLayout = findViewById(R.id.settings_layout);
        helpLayout = findViewById(R.id.help_layout);

        // set them invisible
        ViewHelper.setVisibility(settingsLayout, false);
        ViewHelper.setVisibility(helpLayout, false);

        // set listeners
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHelper.switchVisibility(settingsLayout);
                disableHelpView();
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
}
