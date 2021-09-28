package com.e.uvsafeaustralia.views.quiz.Category2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityQ2Category2Binding;
import com.e.uvsafeaustralia.databinding.ActivityQ1Category3Binding;

public class Q1Category2Activity extends AppCompatActivity {
    private ActivityQ2Category2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQ2Category2Binding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

    }
}