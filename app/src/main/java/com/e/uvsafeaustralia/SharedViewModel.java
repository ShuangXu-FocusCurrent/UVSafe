package com.e.uvsafeaustralia;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<LocationModel> location;
    private final String url = "https://api.openweathermap.org/data/2.5/onecall";
    private final String appid = "03bbeee1e357560e71cdde42465aad22";
    DecimalFormat tempdf = new DecimalFormat("#");

    private String lat;
    private String lon;




    private MutableLiveData<String> temperature;

    private MutableLiveData<String> uvlValue;

    public SharedViewModel(){
        location = new MutableLiveData<>();
        temperature = new MutableLiveData<>();
        uvlValue=new MutableLiveData<>();
        lat="";
        lon="";
    }
    public void setLocation(LocationModel message) {
        location.setValue(message);
    }
    public LiveData<LocationModel> getLocation() {
        return location; }

    public String getLat() { return lat; }
    public void setLat(String lat) { this.lat = lat;}
    public String getLon() { return lon; }
    public void setLon(String lon) { this.lon = lon; }

    public LiveData<String> getTemperature() { return temperature; }
    public LiveData<String>  getUvlValue() { return uvlValue; }
    public void setUvlValue(String uvlValueMes) {
        uvlValue.setValue(uvlValueMes)
        ;

    }
    public void setTemperature(String temperatureMes) {
        uvlValue.setValue(temperatureMes);
    }





    public void getWeatherInfor(View view){
        String tempUrl = "";

//
        tempUrl=url+"?lat="+lat+"&lon="+lon+"&exclude=minutely,hourly,daily&appid="+appid;

        StringRequest stringRequest=new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.e("response",response);
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject jsonCurrent = jsonResponse.getJSONObject("current");
                    Integer dt = jsonCurrent.getInt("dt");
                    Integer sunrise = jsonCurrent.getInt("sunrise");
                    Integer sunset = jsonCurrent.getInt("sunset");
                    Double temp = jsonCurrent.getDouble("temp")-273.15;
                    Double feelsLike = jsonCurrent.getDouble("feels_like")-273.15;

                    Integer uvi = jsonCurrent.getInt("uvi");



                    temperature.setValue(String.valueOf(tempdf.format(temp)));
                    Log.e("Weather Response ",String.valueOf(tempdf.format(temp)));
                    uvlValue.setValue(String.valueOf(uvi));








                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(requireActivity(),error.toString().trim(),Toast.LENGTH_LONG).show();
                Log.e("Weather Response ",error.toString().trim());

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);

    }


}
