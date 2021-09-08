package com.e.uvsafeaustralia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb  = notificationHelper.getChannelNotification("Alarm ","Time to reapply sunblock");
        notificationHelper.getManager().notify(1,nb.build());
    }
}
