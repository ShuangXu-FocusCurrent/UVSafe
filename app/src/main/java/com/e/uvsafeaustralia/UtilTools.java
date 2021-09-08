package com.e.uvsafeaustralia;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.DecimalFormat;

public class UtilTools {
    public static final String DEFAULT_SUBURB = "Melbourne";
    public static final String DEFAULT_POSTCODE = "3000";
    public static final String DEFAULT_LATITUDE = "-37.8136";
    public static final String DEFAULT_LONGITUDE = "144.9631";
    public static final String URL = "https://api.openweathermap.org/data/2.5/onecall";
    public static final String APPID = "03bbeee1e357560e71cdde42465aad22";
    public static final DecimalFormat TEMPDF = new DecimalFormat("#");

    static Context context;
    UtilTools(Context context) {
        this.context = context;
    }

    public static void setNotificationState(Boolean state) {
        SharedPreferences notificationState = context.getSharedPreferences("NotificationState", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = notificationState.edit();
        editor.putBoolean ("state", state);
        editor.commit();
    }

    public static boolean getNotificationState() {
        SharedPreferences notificationState = context.getSharedPreferences("NotificationState", Context.MODE_PRIVATE);
        return notificationState.getBoolean("state", false);
    }

    public static boolean isNotificationEmpty() {
        SharedPreferences notificationState = context.getSharedPreferences("NotificationState", Context.MODE_PRIVATE);
        return notificationState.contains("state");
    }


}
