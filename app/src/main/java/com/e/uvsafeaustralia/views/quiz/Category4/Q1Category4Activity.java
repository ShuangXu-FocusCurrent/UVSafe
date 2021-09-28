package com.e.uvsafeaustralia.views.quiz.Category4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityQ2Category2Binding;
import com.e.uvsafeaustralia.databinding.ActivityQ1Category4Binding;

public class Q1Category4Activity extends AppCompatActivity {
    private ActivityQ1Category4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQ1Category4Binding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);
    }
}