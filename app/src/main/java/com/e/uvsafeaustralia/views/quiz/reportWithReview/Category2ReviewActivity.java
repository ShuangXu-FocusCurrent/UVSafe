package com.e.uvsafeaustralia.views.quiz.reportWithReview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityCategory1ReviewBinding;
import com.e.uvsafeaustralia.databinding.ActivityCategory2ReviewBinding;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

public class Category2ReviewActivity extends AppCompatActivity {

    private ActivityCategory2ReviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategory2ReviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.backMainQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Category2ReviewActivity.this , QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });
    }
}