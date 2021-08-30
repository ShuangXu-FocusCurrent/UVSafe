package com.e.uvsafeaustralia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class SlideActivity extends AppCompatActivity {
    public static ViewPager viewPager;
    SlideViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_main);

        viewPager = findViewById(R.id.viewpager);
        adapter = new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
//        if(isOpenAlread()){
//
//            Intent locationIntent = new Intent(SlideActivity.this, MainFunction.class);
//            locationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(locationIntent);
//
//        }else{
//            SharedPreferences.Editor editor = getSharedPreferences("slide",MODE_PRIVATE).edit();
//            editor.putBoolean("slide",true);
//            editor.commit();
//        }
    }

    private boolean isOpenAlread() {
        SharedPreferences sharedPreferences = getSharedPreferences("slide",MODE_PRIVATE);
        boolean result = sharedPreferences.getBoolean("slide",false);
        return result;
    }
}



















