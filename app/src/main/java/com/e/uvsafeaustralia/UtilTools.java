package com.e.uvsafeaustralia;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
