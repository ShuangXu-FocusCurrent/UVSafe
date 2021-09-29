package com.e.uvsafeaustralia.views.quiz.reportWithReview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityReportBinding;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.questionsList;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory1;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory2;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory3;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory4;


public class ReportActivity extends AppCompatActivity {
    private ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        int totalQuestions = questionsList.size();
        int totalAttempted = userAnswersCategory1.size() + userAnswersCategory2.size() + userAnswersCategory3.size() + userAnswersCategory4.size();
        int totalCorrect = 0;

        for (AnswerModel answer : userAnswersCategory1)
            totalCorrect = totalCorrect + answer.getStatus();
        for (AnswerModel answer : userAnswersCategory2)
            totalCorrect = totalCorrect + answer.getStatus();
        for (AnswerModel answer : userAnswersCategory3)
            totalCorrect = totalCorrect + answer.getStatus();
        for (AnswerModel answer : userAnswersCategory4)
            totalCorrect = totalCorrect + answer.getStatus();

        int totalWrong = totalAttempted - totalCorrect;

        StringBuilder sAttempted = new StringBuilder();
        sAttempted.append("/").append(totalAttempted);
        StringBuilder sQuestion = new StringBuilder();
        sQuestion.append("/").append(totalQuestions);
        binding.textViewAttempted.setText(String.valueOf(totalAttempted));
        binding.textViewTotalAttempted1.setText(sAttempted);
        binding.textViewTotalAttempted2.setText(sAttempted);
        binding.textViewQuestions.setText(sQuestion);
        binding.textViewTotalCorrect.setText(String.valueOf(totalCorrect));
        binding.textViewTotalWrong.setText(String.valueOf(totalWrong));

        if (userAnswersCategory1.size() == 0) {
            binding.c1Review.setEnabled(false);
            binding.c1Review.setAlpha(0.5f);
        }
        if (userAnswersCategory2.size() == 0) {
            binding.c2Review.setEnabled(false);
            binding.c2Review.setAlpha(0.5f);
        }
        if (userAnswersCategory3.size() == 0) {
            binding.c3Review.setEnabled(false);
            binding.c3Review.setAlpha(0.5f);
        }
        if (userAnswersCategory4.size() == 0) {
            binding.c4Review.setEnabled(false);
            binding.c4Review.setAlpha(0.5f);
        }

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