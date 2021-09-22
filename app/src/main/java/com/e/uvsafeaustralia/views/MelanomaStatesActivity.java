package com.e.uvsafeaustralia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityMelanomaStatesBinding;
import com.e.uvsafeaustralia.databinding.ActivityQuizFourBlocksBinding;

public class MelanomaStatesActivity extends AppCompatActivity {
    private ActivityMelanomaStatesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMelanomaStatesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}