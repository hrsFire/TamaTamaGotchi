package at.teamgotcha.tamagotchi.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.security.InvalidParameterException;
import java.util.Dictionary;
import java.util.Hashtable;

import at.teamgotcha.tamagotchi.R;

public class Icons {
    private static Context currentContext;
    private static Icons instance;
    private Dictionary<Integer, Bitmap> imageCache;

    private Icons() {
        imageCache = new Hashtable<>();
    }

    public static Icons getInstance() {
        if (instance != null && currentContext == null) {
            throw new InvalidParameterException("context is not set");
        } else {
            instance = new Icons();
        }

        return instance;
    }

    public static void setContext(Context context) {
        currentContext = context;
    }

    // Backgrounds
    public Bitmap getYellowBackground() {
        return getBitmap(R.drawable.background);
    }

    // Appearances
    public Bitmap getSquidAppearance() {
        return getBitmap(R.drawable.pet_squid);
    }

    public Bitmap getBubblegumAppearance() {
        return getBitmap(R.drawable.pet_bubblegum);
    }

    // Battery
    public Bitmap getBatteryEmpty() {
        return getBitmap(R.drawable.battery_icon_empty);
    }

    public Bitmap getBatteryOneQuarter() {
        return getBitmap(R.drawable.battery_icon_one_quarter);
    }

    public Bitmap getBatteryHalf() {
        return getBitmap(R.drawable.battery_icon_half);
    }

    public Bitmap getBatteryThreeQuarters() {
        return getBitmap(R.drawable.battery_icon_three_quarters);
    }

    public Bitmap getBatteryFull() {
        return getBitmap(R.drawable.battery_icon_full);
    }

    // Mood
    public Bitmap getMoodLow() {
        return getBitmap(R.drawable.mood_icon3);
    }

    public Bitmap getMoodAverage() {
        return getBitmap(R.drawable.mood_icon2);
    }

    public Bitmap getMoodHappy() {
        return getBitmap(R.drawable.mood_icon);
    }

    // Gender
    public Bitmap getGenderFemale() {
        return getBitmap(R.drawable.female_icon);
    }

    public Bitmap getGenderMale() {
        return getBitmap(R.drawable.male_icon);
    }

    private Bitmap getBitmap(int id) {
        Bitmap result = imageCache.get(id);

        if (result == null) {
            result = BitmapFactory.decodeResource(currentContext.getResources(), id);
            imageCache.put(id, result);
        }

        return result;
    }
}