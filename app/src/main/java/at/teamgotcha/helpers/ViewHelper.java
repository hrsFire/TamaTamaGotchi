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

    public static void setXYAbove(View targetView, View sourceView) {
        targetView.setX(sourceView.getX());
        targetView.setY(sourceView.getY() + sourceView.getHeight());
    }

    public static void setXY(View targetView, float x, float y) {
        targetView.setX(x);
        targetView.setY(y);
    }

    public static void setXYHalf(View targetView, View sourceView) {
        setXY(targetView, sourceView.getX() - targetView.getWidth() /2 + sourceView.getWidth() /2, sourceView.getY() - targetView.getHeight() /2 + sourceView.getHeight() /2);
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