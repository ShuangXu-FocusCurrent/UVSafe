package com.e.uvsafeaustralia.views.quiz.Category3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityQ1Category3Binding;
import com.e.uvsafeaustralia.databinding.FragmentQ1Category1Binding;
import com.e.uvsafeaustralia.databinding.FragmentQ1Category3Binding;

public class Q1Category3Activity extends AppCompatActivity {
    private ActivityQ1Category3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQ1Category3Binding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

    }
}