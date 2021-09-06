package com.e.uvsafeaustralia;

//package com.e.uvsafeaustralia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityMainFunctionBinding;

public class MainFunction extends AppCompatActivity {
    private ActivityMainFunctionBinding binding;
    private LocationModel locationModel;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainFunctionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);


        if(getIntent()==null || getIntent().getExtras()==null){
            //sharedViewModel.setLat("-37.8136".trim());
            //sharedViewModel.setLon("144.9631".trim());
            sharedViewModel.getWeatherInfor(view);

        }else{
            Bundle location = getIntent().getExtras();
            String suburb = location.getString("suburb");
            String postcode = location.getString("postcode");
            String latitude = location.getString("latitude");
            String longitude = location.getString("longitude");

            locationModel = new LocationModel(1,postcode, suburb, latitude, longitude);


            sharedViewModel.setLocation(locationModel);
            sharedViewModel.setLat(latitude.trim());
            sharedViewModel.setLon(longitude.trim());

            sharedViewModel.getWeatherInfor(view);

        }

//        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
//        Bundle location = getIntent().getExtras();
//        String suburb = location.getString("suburb");
//        if(suburb.equals("1")){
//            sharedViewModel.setLat(UtilTools.DEFAULT_LATITUDE.trim());
//            sharedViewModel.setLon(UtilTools.DEFAULT_LONGITUDE.trim());
//
//        }else{
//            String postcode = location.getString("postcode");
//            String latitude = location.getString("latitude");
//            String longitude = location.getString("longitude");
//
//            locationModel = new LocationModel(1,postcode, suburb, latitude, longitude);
//
//
//            sharedViewModel.setLocation(locationModel);
//            sharedViewModel.setLat(latitude.trim());
//            sharedViewModel.setLon(longitude.trim());
//
//        }
//
//        sharedViewModel.getWeatherInfor(view);



        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homePageFragment, R.id.alarmPageFragment, R.id.sunEduFragment,R.id.quizPageFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);



    }
}