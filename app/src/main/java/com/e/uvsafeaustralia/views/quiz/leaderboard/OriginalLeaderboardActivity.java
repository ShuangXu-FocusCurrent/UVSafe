package com.e.uvsafeaustralia.views.quiz.leaderboard;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.databinding.ActivityOriginalLeardernoardBinding;

public class OriginalLeaderboardActivity extends AppCompatActivity {
    private ActivityOriginalLeardernoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOriginalLeardernoardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}