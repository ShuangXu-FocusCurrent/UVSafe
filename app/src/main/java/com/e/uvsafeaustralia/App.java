package com.e.uvsafeaustralia;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App  extends Application {
    public static final String CHANNEL_1_ID = "uvNotification";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel uvnotification = new NotificationChannel(
                    CHANNEL_1_ID,
                    "UV Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            uvnotification.setDescription("This is UV Notification");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(uvnotification);
        }
    }
}
