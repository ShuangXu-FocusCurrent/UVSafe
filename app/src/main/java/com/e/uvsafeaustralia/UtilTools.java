package com.e.uvsafeaustralia;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;

public class UtilTools {
    static Context context;
    UtilTools(Context context) {
        this.context = context;
    }


    public static void setLocation(String location_input) {
        SharedPreferences location = context.getSharedPreferences("User_Location", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = location.edit();
        editor.putString("Location", location_input);
        editor.commit();
    }

    public static String getLocation() {
        SharedPreferences location = context.getSharedPreferences("Location", Context.MODE_PRIVATE);
        return location.getString("Location", "");
    }

    public static boolean isLocationEmpty() {
        SharedPreferences location = context.getSharedPreferences("Location", Context.MODE_PRIVATE);
        boolean isLocEmpty = location.getString("Location", "").isEmpty();
        return  isLocEmpty;
    }
}
