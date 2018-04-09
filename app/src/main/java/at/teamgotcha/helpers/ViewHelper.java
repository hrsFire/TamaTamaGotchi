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

    public static void switchVisibility(View view) {
        switchVisibility(view, getVisibilityAsBool(view.getVisibility()));
    }

    public static void setVisibility(Fragment fragment, boolean isVisible) {
        setVisibility(fragment.getView(), isVisible);
    }

    public static void setVisibility(android.support.v4.app.Fragment fragment, boolean isVisible) {
        setVisibility(fragment.getView(), isVisible);
    }

    public static void setVisibility(View view, boolean isVisible) {
        if(isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public static boolean getVisibilityAsBool(int visibility) {
        return visibility == View.VISIBLE;
    }

    private static void switchVisibility(Fragment fragment, boolean visible) {
        ViewHelper.setVisibility(fragment.getView(), !visible);
    }

    private static void switchVisibility(android.support.v4.app.Fragment fragment, boolean visible) {
        ViewHelper.setVisibility(fragment.getView(), !visible);
    }

    private static void switchVisibility(View view, boolean visible) {
        ViewHelper.setVisibility(view, !visible);
    }
}