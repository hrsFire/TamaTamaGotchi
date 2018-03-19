package at.teamgotcha.helpers;

import android.app.Fragment;
import android.view.View;

public class ViewHelper {
    public static void switchVisibility(Fragment fragment) {
        switchVisibility(fragment.getView(), fragment.isVisible());
    }

    public static void switchVisibility(android.support.v4.app.Fragment fragment) {
        switchVisibility(fragment.getView(), fragment.isVisible());
    }

    public static void setVisibility(View view, boolean isVisible) {
        if(isVisible) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    private static void switchVisibility(View view, boolean visible) {
        ViewHelper.setVisibility(view, visible);
    }
}