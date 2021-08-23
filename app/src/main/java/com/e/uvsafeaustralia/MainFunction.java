package com.e.uvsafeaustralia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.e.uvsafeaustralia.databinding.ActivityMainFunctionBinding;

public class MainFunction extends AppCompatActivity {
    private ActivityMainFunctionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homePageFragment, R.id.alarmPageFragment, R.id.sunEduFragment,R.id.quizPageFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);



    }
}