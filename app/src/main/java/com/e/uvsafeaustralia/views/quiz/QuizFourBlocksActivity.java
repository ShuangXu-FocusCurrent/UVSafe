package com.e.uvsafeaustralia.views.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityQuizFourBlocksBinding;
import com.e.uvsafeaustralia.views.quiz.Category1.QuizCategory1Activity;
import com.e.uvsafeaustralia.views.quiz.Category2.QuizCategory2Activity;
import com.e.uvsafeaustralia.views.quiz.Category3.QuizCategory3Activity;
import com.e.uvsafeaustralia.views.quiz.Category4.QuizCategory4Activity;
import com.e.uvsafeaustralia.views.quiz.leaderboard.OriginalLeadernoardActivity;

import static com.e.uvsafeaustralia.views.functionsFragment.QuizPageFragment.player;


public class QuizFourBlocksActivity extends AppCompatActivity {
    private ActivityQuizFourBlocksBinding binding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizFourBlocksBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.textViewPlayer.setText(player.getNickName());
        binding.category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QuizFourBlocksActivity.this , QuizCategory1Activity.class);
                startActivity(intent);
            }
        });

        binding.category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QuizFourBlocksActivity.this , QuizCategory2Activity.class);
                startActivity(intent);
            }
        });

        binding.category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QuizFourBlocksActivity.this , QuizCategory3Activity.class);
                startActivity(intent);
            }
        });

        binding.category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QuizFourBlocksActivity.this , QuizCategory4Activity.class);
                startActivity(intent);
            }
        });

        binding.checkLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( QuizFourBlocksActivity.this , OriginalLeadernoardActivity.class);
                startActivity(intent);
            }
        });
    }
}