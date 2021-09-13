package com.e.uvsafeaustralia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityMainFunctionBinding;

import java.text.DecimalFormat;

public class MainFunction extends AppCompatActivity {
    private static String defaultSuburb = "Melbourne";
    private static  String defaultPostcode = "3000";
    private static String defaultLatitude = "-37.8136";
    private static String defaultLongitude = "144.9631";
    private static String weatherUrl = "https://api.openweathermap.org/data/2.5/onecall";
    private static String appId = "03bbeee1e357560e71cdde42465aad22";
    private static DecimalFormat tempDf= new DecimalFormat("#");
    private ActivityMainFunctionBinding binding;
    private LocationModel locationModel;
    private SharedViewModel sharedViewModel;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainFunctionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sp = this.getPreferences(Context.MODE_PRIVATE);

        //R.id.quizPageFragment // to be included in Iteration 3. DO NOT REMOVE!
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homePageFragment, R.id.alarmPageFragment, R.id.sunEduFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        if(getIntent()==null || getIntent().getExtras()==null){
            if(this.getPreferences(Context.MODE_PRIVATE).contains("lat")&&this.getPreferences(Context.MODE_PRIVATE).contains("lon")
            &&this.getPreferences(Context.MODE_PRIVATE).contains("suburb")){
                String suburb = sp.getString("suburb", defaultSuburb);
                String postcode = sp.getString("postcode", defaultPostcode);
                String latitude = sp.getString("lat", defaultLatitude);
                String longitude = sp.getString("lon", defaultLongitude);
                locationModel = new LocationModel(1,postcode, suburb, latitude, longitude);
                sharedViewModel.setLocation(locationModel);
                sharedViewModel.setLat(latitude.trim());
                sharedViewModel.setLon(longitude.trim());
                sharedViewModel.getWeatherInfor(view);
            }else{
                sharedViewModel.getWeatherInfor(view);
            }
            navController.navigate(R.id.sunEduFragment);
        } else if(getIntent().hasExtra("Alarm")){
            if(this.getPreferences(Context.MODE_PRIVATE).contains("lat")&&this.getPreferences(Context.MODE_PRIVATE).contains("lon")
                    &&this.getPreferences(Context.MODE_PRIVATE).contains("suburb")){
                String suburb = sp.getString("suburb", defaultSuburb);
                String postcode = sp.getString("postcode", defaultPostcode);
                String latitude = sp.getString("lat", defaultLatitude);
                String longitude = sp.getString("lon", defaultLongitude);
                locationModel = new LocationModel(1,postcode, suburb, latitude, longitude);
                sharedViewModel.setLocation(locationModel);
                sharedViewModel.setLat(latitude.trim());
                sharedViewModel.setLon(longitude.trim());
                sharedViewModel.getWeatherInfor(view);
            }else{
                sharedViewModel.getWeatherInfor(view);
            }
            navController.navigate(R.id.alarmPageFragment);
        }else if(getIntent().hasExtra("slide")){
            if(this.getPreferences(Context.MODE_PRIVATE).contains("lat")&&this.getPreferences(Context.MODE_PRIVATE).contains("lon")
                    &&this.getPreferences(Context.MODE_PRIVATE).contains("suburb")){
                String suburb = sp.getString("suburb", defaultSuburb);
                String postcode = sp.getString("postcode", defaultPostcode);
                String latitude = sp.getString("lat", defaultLatitude);
                String longitude = sp.getString("lon", defaultLongitude);
                locationModel = new LocationModel(1,postcode, suburb, latitude, longitude);
                sharedViewModel.setLocation(locationModel);
                sharedViewModel.setLat(latitude.trim());
                sharedViewModel.setLon(longitude.trim());
                sharedViewModel.getWeatherInfor(view);
            }else{
                sharedViewModel.getWeatherInfor(view);
            }
        } else{
            String suburb = sp.getString("suburb", defaultSuburb);
            String postcode = sp.getString("postcode", defaultPostcode);
            String latitude = sp.getString("lat", defaultLatitude);
            String longitude = sp.getString("lon", defaultLongitude);
            locationModel = new LocationModel(1,postcode, suburb, latitude, longitude);
            storelocation(suburb,postcode,latitude, longitude );
            sharedViewModel.setLocation(locationModel);
            sharedViewModel.setLat(latitude.trim());
            sharedViewModel.setLon(longitude.trim());
            sharedViewModel.getWeatherInfor(view);
        }
    }
    private void storelocation(String suburb,String postcode,String latitude, String longtitude) {
        editor = sp.edit();
        editor.putString ("suburb", suburb);
        editor.putString ("postcode", postcode);
        editor.putString ("lat", latitude);
        editor.putString ("lon", longtitude);
        editor.commit();
    }
}