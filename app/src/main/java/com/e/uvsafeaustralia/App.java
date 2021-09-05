package com.e.uvsafeaustralia;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_1_ID = "uvNotification";
    String title = "UVSafeAustralia";
    String message = "This is UV level Notification";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel(title, message);
    }
    private void createNotificationChannel(String title, String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel uvnotification = new NotificationChannel(
                    CHANNEL_1_ID,
                    title,
                    NotificationManager.IMPORTANCE_HIGH);
            uvnotification.setDescription(message);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(uvnotification);
        }
    }
}
