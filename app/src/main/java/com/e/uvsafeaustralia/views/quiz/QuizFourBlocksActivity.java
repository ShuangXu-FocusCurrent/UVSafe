package com.e.uvsafeaustralia.views.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityQuizFourBlocksBinding;
import com.e.uvsafeaustralia.views.quiz.Category1.QuizCategory1Activity;
import com.e.uvsafeaustralia.views.quiz.Category2.QuizCategory2Activity;
import com.e.uvsafeaustralia.views.quiz.Category3.QuizCategory3Activity;
import com.e.uvsafeaustralia.views.quiz.Category4.QuizCategory4Activity;

public class QuizFourBlocksActivity extends AppCompatActivity {
    private ActivityQuizFourBlocksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizFourBlocksBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
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
        setContentView(view);
    }
}