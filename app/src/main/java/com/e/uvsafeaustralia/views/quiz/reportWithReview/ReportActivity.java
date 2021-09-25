package com.e.uvsafeaustralia.views.quiz.reportWithReview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityReportBinding;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;
import com.e.uvsafeaustralia.views.quiz.leaderboard.LeaderboardActivity;

public class ReportActivity extends AppCompatActivity {
    private ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.c1Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ReportActivity.this , Category1ReviewActivity.class);
                startActivity(intent);
            }
        });

        binding.c2Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ReportActivity.this , Category2ReviewActivity.class);
                startActivity(intent);
            }
        });

        binding.c3Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ReportActivity.this , Category3ReviewActivity.class);
                startActivity(intent);
            }
        });

        binding.c4Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ReportActivity.this , Category4ReviewActivity.class);
                startActivity(intent);
            }
        });

        binding.backMainQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ReportActivity.this , QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });
    }
}