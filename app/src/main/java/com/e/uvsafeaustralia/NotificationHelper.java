package com.e.uvsafeaustralia;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.e.uvsafeaustralia.MainFunction;
import com.e.uvsafeaustralia.R;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channel1";
    public static final String channelName = "reapplySunblock";
    private NotificationManager manager;


    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            createChannels();
        }

    }


    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels(){
        NotificationChannel channel = new NotificationChannel(channelID,channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableVibration(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.design_default_color_primary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel);

    }
    public NotificationManager getManager(){
        if(manager==null){
            manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public NotificationCompat.Builder getChannelNotification(String title,String message){
        Intent resultIntent = new Intent(this, MainFunction.class);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(),channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_alarm)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                ;
    }
}