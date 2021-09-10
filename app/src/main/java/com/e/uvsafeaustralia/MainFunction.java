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

public class MainFunction extends AppCompatActivity {
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
                String suburb = sp.getString("suburb", "Melbourne");
                String postcode = sp.getString("postcode", "1234");
                String latitude = sp.getString("lat", "144.9631");
                String longitude = sp.getString("lon", "-37.8136");
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
                String suburb = sp.getString("suburb", "Melbourne");
                String postcode = sp.getString("postcode", "1234");
                String latitude = sp.getString("lat", "144.9631");
                String longitude = sp.getString("lon", "-37.8136");
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
                String suburb = sp.getString("suburb", "Melbourne");
                String postcode = sp.getString("postcode", "1234");
                String latitude = sp.getString("lat", "144.9631");
                String longitude = sp.getString("lon", "-37.8136");
                locationModel = new LocationModel(1,postcode, suburb, latitude, longitude);
                sharedViewModel.setLocation(locationModel);
                sharedViewModel.setLat(latitude.trim());
                sharedViewModel.setLon(longitude.trim());
                sharedViewModel.getWeatherInfor(view);
            }else{
                sharedViewModel.getWeatherInfor(view);
            }
        } else{
            Bundle location = getIntent().getExtras();
            String suburb = location.getString("suburb");
            String postcode = location.getString("postcode");
            String latitude = location.getString("latitude");
            String longitude = location.getString("longitude");
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