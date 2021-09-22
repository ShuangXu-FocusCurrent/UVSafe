package com.e.uvsafeaustralia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityAboutMelanomaBinding;
import com.e.uvsafeaustralia.databinding.ActivityQuizFourBlocksBinding;

public class AboutMelanomaActivity extends AppCompatActivity {

    private ActivityAboutMelanomaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutMelanomaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}