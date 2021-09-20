package com.e.uvsafeaustralia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityMelanomaRiskFactorsBinding;

public class MelanomaRiskFactorsActivity extends AppCompatActivity {
    private ActivityMelanomaRiskFactorsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMelanomaRiskFactorsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}