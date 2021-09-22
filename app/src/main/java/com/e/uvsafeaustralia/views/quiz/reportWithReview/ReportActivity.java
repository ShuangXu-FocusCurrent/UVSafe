package com.e.uvsafeaustralia.views.quiz.reportWithReview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityReportBinding;
import com.e.uvsafeaustralia.views.quiz.leaderboard.LeaderboardActivity;

public class ReportActivity extends AppCompatActivity {
    private ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.goReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ReportActivity.this , ReviewActivity.class);
                startActivity(intent);
            }
        });
    }
}