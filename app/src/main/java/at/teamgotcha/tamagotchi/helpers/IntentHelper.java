package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;
import android.content.Intent;

public class IntentHelper {
    public static void startTextIntent(Context context, String text) {
        // https://faq.whatsapp.com/en/android/28000012
        // https://developer.android.com/training/sharing/send.html
        // share with any app which supports the given type
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }
}
