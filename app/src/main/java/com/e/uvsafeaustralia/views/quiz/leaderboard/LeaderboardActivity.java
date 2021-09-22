package com.e.uvsafeaustralia.views.quiz.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.uvsafeaustralia.databinding.ActivityLeaderboardBinding;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;
import com.e.uvsafeaustralia.views.quiz.reportWithReview.ReportActivity;

import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private ActivityLeaderboardBinding binding;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.goReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LeaderboardActivity.this , ReportActivity.class);
                startActivity(intent);
            }
        });
    }



}