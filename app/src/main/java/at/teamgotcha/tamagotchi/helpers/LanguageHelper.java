package at.teamgotcha.tamagotchi.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageHelper {
    public static void setLocale(Locale locale, Class restartActivityClass, Context context, Activity activity) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration configuration = res.getConfiguration();
        //
        // configuration.locale = locale;
        configuration.setLocale(locale);
        res.updateConfiguration(configuration, dm);
        Intent refresh = new Intent(context, restartActivityClass);
        context.startActivity(refresh);
        activity.finish();
    }

    public static Locale getLocale(Context context) {
        Resources res = context.getResources();
        Configuration configuration = res.getConfiguration();
        Locale locale;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            LocaleList localeList = configuration.getLocales();

            if (!localeList.isEmpty()) {
                locale = localeList.get(0);
            } else {
                locale = null;
            }
        } else {
            locale = configuration.locale;
        }

        return locale;
    }
}