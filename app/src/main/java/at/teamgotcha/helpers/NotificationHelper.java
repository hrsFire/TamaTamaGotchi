package at.teamgotcha.helpers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import at.teamgotcha.pets.Pet;
import at.teamgotcha.tamagotchi.R;

public class NotificationHelper {

    private final static int REQ_CODE_HUNGER = 1;

    public static void addPetHungerNotification(Pet pet){

        // Build the Notifcation (if required)
        if(pet.isHungry() != null){

            NotificationManager notificationManager = (NotificationManager) pet.getContext().getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = null;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                int importance = NotificationManager.IMPORTANCE_DEFAULT;

                NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);

                notificationManager.createNotificationChannel(notificationChannel);

                builder = new NotificationCompat.Builder(pet.getContext().getApplicationContext(), notificationChannel.getId());

            } else {

                builder = new NotificationCompat.Builder(pet.getContext().getApplicationContext());
            }

            builder = builder
                    .setSmallIcon(R.drawable.icon_ball)
                    // .setColor(ContextCompat.getColor(context, R.color.color))
                    .setContentTitle(pet.getContext().getString(R.string.notification_hunger))
                    // .setTicker(context.getString(R.string.text))
                    .setContentText(pet.isHungry())
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true);

            notificationManager.notify(REQ_CODE_HUNGER, builder.build());
        }

    }

}
