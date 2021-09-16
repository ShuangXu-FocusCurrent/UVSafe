package com.e.uvsafeaustralia;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.uvsafeaustralia.models.LocationModel;

import org.json.JSONException;
import org.json.JSONObject;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<LocationModel> location;
    private String lat;
    private String lon;
    private MutableLiveData<String> dtValue;
    private MutableLiveData<String> temperature;
    private MutableLiveData<String> uvlValue;
    private MutableLiveData<String> sunriseValue;
    private MutableLiveData<String> sunsetValue;
    private MutableLiveData<String> switchTag;

    public SharedViewModel(){
        location = new MutableLiveData<>();
        temperature = new MutableLiveData<>();
        uvlValue = new MutableLiveData<>();
        sunriseValue = new MutableLiveData<>();
        sunsetValue = new MutableLiveData<>();
        switchTag = new MutableLiveData<>();
        lat = "-37.8136";
        lon = "144.9631";
        dtValue = new MutableLiveData<>();
    }

    public void setLocation(LocationModel message) { location.setValue(message); }
    public LiveData<LocationModel> getLocation() { return location; }
    public String getLat() { return lat; }
    public void setLat(String lat) { this.lat = lat;}
    public String getLon() { return lon; }
    public void setLon(String lon) { this.lon = lon; }
    public void setDtValue(String dtValueMes) { dtValue.setValue(dtValueMes); }
    public LiveData<String>  getDtValue() { return dtValue; }
    public LiveData<String> getTemperature() { return temperature; }
    public LiveData<String>  getUvlValue() { return uvlValue; }
    public LiveData<String>  getSunriselValue() { return sunriseValue; }
    public LiveData<String>  getSunSetValue() { return sunsetValue; }
    public LiveData<String> getSwitchTag() { return switchTag; }
    public void setUvlValue(String uvlValueMes) { uvlValue.setValue(uvlValueMes); }
    public void setTemperature(String temperatureMes) { uvlValue.setValue(temperatureMes); }
    public void setSunriseValue(String sunriseMes) { sunriseValue.setValue(sunriseMes); }
    public void setSunsetValue(String sunsetMes) { sunsetValue.setValue(sunsetMes); }
    public void setSwitchTag(String switchTagMes) { switchTag.setValue(switchTagMes);}


    public void getWeatherInfor(View view){
        String tempUrl = "";
        tempUrl=UtilTools.URL+"?lat="+lat+"&lon="+lon+"&exclude=minutely,hourly,daily&appid="+UtilTools.APPID;

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

                    temperature.setValue(String.valueOf(UtilTools.TEMPDF.format(temp)));
                    Log.i("Weather Response ",String.valueOf(UtilTools.TEMPDF.format(temp)));
                    uvlValue.setValue(String.valueOf(uvi));
                    sunriseValue.setValue(String.valueOf(sunrise));
                    sunsetValue.setValue(String.valueOf(sunset));
                    dtValue.setValue(String.valueOf(dt));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error Response ",error.toString().trim());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
    }
}
