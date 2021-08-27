package com.e.uvsafeaustralia;

//package com.e.uvsafeaustralia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.e.uvsafeaustralia.databinding.ActivityMainFunctionBinding;

public class MainFunction extends AppCompatActivity {
    private ActivityMainFunctionBinding binding;
    private LocationModel locationModel;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle location = getIntent().getExtras();
        String suburb = location.getString("suburb");
        String postcode = location.getString("postcode");
        String state = location.getString("state");
        String latitude = location.getString("latitude");
        String longitude = location.getString("Longitude");

        locationModel = new LocationModel(1,postcode, suburb, state, latitude, longitude);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.setLocation(locationModel);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homePageFragment, R.id.alarmPageFragment, R.id.sunEduFragment,R.id.quizPageFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);



    }
}