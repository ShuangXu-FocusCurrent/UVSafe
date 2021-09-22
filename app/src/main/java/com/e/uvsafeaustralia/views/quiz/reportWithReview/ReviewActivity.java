package com.e.uvsafeaustralia.views.quiz.reportWithReview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityReportBinding;
import com.e.uvsafeaustralia.databinding.ActivityReviewBinding;

public class ReviewActivity extends AppCompatActivity {
    private ActivityReviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}