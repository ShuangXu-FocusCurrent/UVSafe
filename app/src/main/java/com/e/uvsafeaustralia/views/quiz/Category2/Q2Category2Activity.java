package com.e.uvsafeaustralia.views.quiz.Category2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.databinding.ActivityQ2Category2Binding;

public class Q2Category2Activity extends AppCompatActivity {
    private ActivityQ2Category2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQ2Category2Binding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

    }
}
