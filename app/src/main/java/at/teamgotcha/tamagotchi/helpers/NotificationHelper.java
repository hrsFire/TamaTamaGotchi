package at.teamgotcha.tamagotchi.helpers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;

import at.teamgotcha.tamagotchi.MainActivity;
import at.teamgotcha.tamagotchi.R;

public class NotificationHelper {
    public static void showNotification(Context context, int requestCode, String contentText, String title, boolean isLargeText) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel("ID", "NAME", importance);

            notificationManager.createNotificationChannel(notificationChannel);

            builder = new NotificationCompat.Builder(context.getApplicationContext(), notificationChannel.getId());
        } else {
            // note: deprecation on purpose for backwards compatibility
            builder = new NotificationCompat.Builder(context.getApplicationContext());
        }

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        builder = builder
                .setSmallIcon(R.drawable.icon_ball)
                // .setColor(ContextCompat.getColor(context, R.color.color))
                .setContentTitle(title)
                // .setTicker(context.getString(R.string.text))
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        if (isLargeText) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(contentText));
        } else {
            builder.setContentText(contentText);
        }

        notificationManager.notify(requestCode, builder.build());
    }

    public static void closeNotification(Context context, int requestCode) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(requestCode);
    }

    public static boolean isNotificationActive(Context context, int requestCode) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        StatusBarNotification[] barNotifications = new StatusBarNotification[0];

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            barNotifications = notificationManager.getActiveNotifications();

            for(StatusBarNotification notification : barNotifications) {
                if (notification.getId() == requestCode) {
                    return true;
                }
            }
        }

        return false;
    }
}