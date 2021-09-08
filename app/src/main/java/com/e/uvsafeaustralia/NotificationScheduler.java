package com.e.uvsafeaustralia;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.e.uvsafeaustralia.App.CHANNEL_1_ID;

public class NotificationScheduler extends Worker {
    private SharedViewModel sharedViewModel;
    private Integer dt;
    private Integer sunrise;
    private Integer sunset;
    private Double temp;
    private Integer uvi;

    public NotificationScheduler(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data locationData = getInputData();
        String lat = locationData.getString("latitude").trim();
        String lon = locationData.getString("longitude").trim();
        callWeatherAPI(lat, lon);
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // for testing purpose only
        // user must receive notification
        // set the uv index to different UV level here
//        uvi = 2;
//        // set time (dt) to either between sunrise and sunset or outside
//        sunrise = 1630874063;
//        dt = 1630915321;
//        sunset = 1630917557;
//        System.out.println(dt);
//        System.out.println(sunrise);
//        System.out.println(sunset);
//        System.out.println(uvi);

        if (dt >= sunrise && dt <= sunset && uvi >= 3) {
            setNotification();
            System.out.println("Response recorded");
        }
        return Result.success();
    }

    private void setNotification() {
        Intent intent = new Intent(getApplicationContext(), ProtectionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("UVSafeAustralia")
                .setContentText("Sun protection is now required.")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Sun protection is now required. See what protection measures you can take."))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(1, builder);
    }

    public void callWeatherAPI(String lat, String lon) {
        String tempUrl = "";
        tempUrl=UtilTools.URL+"?lat="+lat+"&lon="+lon+"&exclude=minutely,hourly,daily&appid="+UtilTools.APPID;

        StringRequest stringRequest=new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.e("response",response);
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject jsonCurrent = jsonResponse.getJSONObject("current");
                    dt = jsonCurrent.getInt("dt");
                    sunrise = jsonCurrent.getInt("sunrise");
                    sunset = jsonCurrent.getInt("sunset");
                    temp = jsonCurrent.getDouble("temp")-273.15;
                    Double feelsLike = jsonCurrent.getDouble("feels_like")-273.15;

                    uvi = jsonCurrent.getInt("uvi");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Weather Response ",error.toString().trim());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
