package com.e.uvsafeaustralia.views;

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

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.viewmodels.SharedViewModel;
import com.e.uvsafeaustralia.databinding.ActivityMainFunctionBinding;
import com.e.uvsafeaustralia.models.LocationModel;

public class MainFunction extends AppCompatActivity {
    private static String defaultSuburb = "Melbourne";
    private static  String defaultPostcode = "3000";
    private static String defaultLatitude = "-37.8136";
    private static String defaultLongitude = "144.9631";
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

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homePageFragment, R.id.alarmPageFragment, R.id.sunEduFragment,R.id.quizPageFragment,R.id.moreInforFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        if(getIntent()==null || getIntent().getExtras()==null){
            navController.navigate(R.id.sunEduFragment);
        } else if(getIntent().hasExtra("Alarm")){
            navController.navigate(R.id.alarmPageFragment);
        }
    }
}