package com.e.uvsafeaustralia.views.quiz.leaderboard;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.databinding.ActivityOriginalLearderboardBinding;

public class OriginalLeaderboardActivity extends AppCompatActivity {
    private ActivityOriginalLearderboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOriginalLearderboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}