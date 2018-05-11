package at.teamgotcha.tamagotchi.common;

import android.support.v4.app.Fragment;

public class FragmentEntry {
    private boolean isActive;
    private Fragment fragment;

    public FragmentEntry(Fragment fragment) {
        setActive(false);
        setFragment(fragment);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}