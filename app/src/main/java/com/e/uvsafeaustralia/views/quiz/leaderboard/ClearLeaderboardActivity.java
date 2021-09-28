package com.e.uvsafeaustralia.views.quiz.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityClearLeaderboardBinding;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

public class ClearLeaderboardActivity extends AppCompatActivity {
    private ActivityClearLeaderboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClearLeaderboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnCancelClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(ClearLeaderboardActivity.this, QuizFourBlocksActivity.class);
                startActivity(cancelIntent);
            }
        });

        binding.btnClearLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}