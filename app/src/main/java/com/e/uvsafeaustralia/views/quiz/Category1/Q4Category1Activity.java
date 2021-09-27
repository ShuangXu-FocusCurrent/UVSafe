package com.e.uvsafeaustralia.views.quiz.Category1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.databinding.ActivityQ1Category1Binding;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import java.util.ArrayList;

import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.NOT_SELECTED_BTN_COLOUR;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.SELECTED_BTN_COLOUR;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.getUserAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.recordAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory1;

public class Q4Category1Activity extends AppCompatActivity {
    private ActivityQ1Category1Binding binding;
    private QuestionModel question;
    private AnswerModel userAnswerC1Q4;
    private ArrayList<QuestionModel> questionsCategory1;
    private UserModel player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQ1Category1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle getBundle = getIntent().getExtras();
        player = getBundle.getParcelable("player");
        questionsCategory1 = getBundle.getParcelableArrayList("questionsCategory1");

        question = new QuestionModel();
        // Set question as default on the quiz category 1
        for (QuestionModel questionItem : questionsCategory1)
            if (questionItem.getqNumber() == 4)
                question = questionItem;

        binding.angrySun.setVisibility(View.INVISIBLE);
        binding.girlChild.setVisibility(View.VISIBLE);
        binding.buttonCat1Q1Incative.setVisibility(View.VISIBLE);
        binding.buttonCat1Q4Active.setVisibility(View.VISIBLE);
        binding.textViewCat1Q1.setText(question.getQuestion());
        binding.buttonOpt1Answer.setText(question.getAnswerOption1());
        binding.buttonOpt2Answer.setText(question.getAnswerOption2());

        userAnswerC1Q4 = getUserAnswer(player, question);
        if (userAnswerC1Q4.getId() != 0) {
            String selected = userAnswerC1Q4.getSelected();
            if (binding.buttonOpt1Answer.getText().equals(selected)) {
                binding.buttonOpt1Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                showFeedback("right", question);
            }
            if (binding.buttonOpt2Answer.getText().equals(selected)) {
                binding.buttonOpt2Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                showFeedback("wrong", question);
            }
            disableAnswerOptions();
        }

        binding.buttonOpt1Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                binding.buttonOpt2Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                // record answer
                userAnswerC1Q4.setUser(player);
                userAnswerC1Q4.setQuestion(question);
                userAnswerC1Q4.setSelected(question.getAnswerOption1());
                userAnswerC1Q4.setStatus(1);
                recordAnswer(userAnswersCategory1, userAnswerC1Q4);
                disableAnswerOptions();
                showFeedback("wrong", question);
            }
        });

        binding.buttonOpt2Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                binding.buttonOpt2Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                // add answer to answermodel instance
                userAnswerC1Q4.setUser(player);
                userAnswerC1Q4.setQuestion(question);
                userAnswerC1Q4.setSelected(question.getAnswerOption2());
                userAnswerC1Q4.setStatus(1);
                recordAnswer(userAnswersCategory1, userAnswerC1Q4);
                disableAnswerOptions();
                showFeedback("right", question);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putParcelable("player", player);
        bundle.putParcelableArrayList("questionsCategory1", questionsCategory1);

        binding.buttonCat1Q1Incative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q4Category1Activity.this, Q1Category1Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat1Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q4Category1Activity.this, Q2Category1Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat1Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q4Category1Activity.this, Q3Category1Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat1End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Q4Category1Activity.this, "You'll be redirected to the Quiz Homepage. Thanks for attempting the quiz.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( Q4Category1Activity.this, QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });

    }

    public void showFeedback(String status, QuestionModel question) {
        if (status.equals("wrong")) {
            binding.sadImg.setVisibility(View.VISIBLE);
            binding.wrongText1.setVisibility(View.VISIBLE);
            binding.wrongText2.setVisibility(View.VISIBLE);
        }
        if (status.equals("right")) {
            binding.balnImg.setVisibility(View.VISIBLE);
            binding.correctText1.setVisibility(View.VISIBLE);
            binding.correctText2.setVisibility(View.VISIBLE);
        }
        binding.waitingImg.setVisibility(View.INVISIBLE);
        binding.feedbackWrong.setVisibility(View.VISIBLE);
        binding.feedbackConstraintLayout.setVisibility(View.VISIBLE);
        binding.correctText4.setText(question.getCorrect());
        binding.infoText.setText(question.getAnswerExplain());
    }

    private void disableAnswerOptions() {
        binding.buttonOpt1Answer.setEnabled(false);
        binding.buttonOpt2Answer.setEnabled(false);
    }
}
