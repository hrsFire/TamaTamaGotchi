package at.teamgotcha.tamagotchi.helpers;

import android.content.Context;

import at.teamgotcha.tamagotchi.R;
import at.teamgotcha.tamagotchi.pets.Pet;

public class PetNotificationHelper {
    private final static int REQ_CODE_HUNGER = 1;

    public static void addPetHungerNotification(Context context, Pet pet) {
        if (pet.isHungry()) {
            NotificationHelper.showNotification(context, REQ_CODE_HUNGER, context.getString(R.string.notification_hunger));
        }
    }
}