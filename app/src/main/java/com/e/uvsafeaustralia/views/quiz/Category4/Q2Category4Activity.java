package com.e.uvsafeaustralia.views.quiz.Category4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.databinding.ActivityQ1Category4Binding;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import java.util.ArrayList;

import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.NOT_SELECTED_BTN_COLOUR;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.SELECTED_BTN_COLOUR;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.getUserAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.recordAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory4;

public class Q2Category4Activity extends AppCompatActivity {
    private ActivityQ1Category4Binding binding;
    private QuestionModel question;
    private AnswerModel userAnswerC4Q2;
    private ArrayList<QuestionModel> questionsCategory4;
    private UserModel player;
    Bundle getBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQ1Category4Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getBundle = getIntent().getExtras();
        player = getBundle.getParcelable("player");
        questionsCategory4 = getBundle.getParcelableArrayList("questionsCategory4");
        question = new QuestionModel();

        for (QuestionModel questionItem : questionsCategory4)
            if (questionItem.getqNumber() == 2)
                question = questionItem;

        binding.textViewCat3Q1.setText(question.getQuestion());
        binding.buttonOpt1Answer.setText(question.getAnswerOption1());
        binding.buttonOpt2Answer.setText(question.getAnswerOption2());
        binding.buttonOpt3Answer.setText(question.getAnswerOption3());

        userAnswerC4Q2 = getUserAnswer(player, question);
        if (userAnswerC4Q2.getId() != 0) {
            String selected = userAnswerC4Q2.getSelected();
            if (binding.buttonOpt1Answer.getText().equals(selected)) {
                binding.buttonOpt1Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                showFeedback("wrong", question);
            }
            if (binding.buttonOpt2Answer.getText().equals(selected)) {
                binding.buttonOpt2Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                showFeedback("right", question);
            }
            if (binding.buttonOpt3Answer.getText().equals(selected)) {
                binding.buttonOpt3Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                showFeedback("wrong", question);
            }
            disableAnswerOptions();
        }

        binding.buttonOpt1Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                binding.buttonOpt2Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                binding.buttonOpt3Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                // record answer
                userAnswerC4Q2.setUser(player);
                userAnswerC4Q2.setQuestion(question);
                userAnswerC4Q2.setSelected(question.getAnswerOption1());
                userAnswerC4Q2.setStatus(0);
                recordAnswer(userAnswersCategory4, userAnswerC4Q2);
                disableAnswerOptions();
                showFeedback("wrong", question);
            }
        });

        binding.buttonOpt2Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                binding.buttonOpt2Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                binding.buttonOpt3Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                // add answer to answermodel instance
                userAnswerC4Q2.setUser(player);
                userAnswerC4Q2.setQuestion(question);
                userAnswerC4Q2.setSelected(question.getAnswerOption2());
                userAnswerC4Q2.setStatus(1);
                recordAnswer(userAnswersCategory4, userAnswerC4Q2);
                disableAnswerOptions();
                showFeedback("right", question);
            }
        });

        binding.buttonOpt3Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                binding.buttonOpt2Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                binding.buttonOpt3Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                // add answer to answermodel instance
                userAnswerC4Q2.setUser(player);
                userAnswerC4Q2.setQuestion(question);
                userAnswerC4Q2.setSelected(question.getAnswerOption3());
                userAnswerC4Q2.setStatus(0);
                recordAnswer(userAnswersCategory4, userAnswerC4Q2);
                disableAnswerOptions();
                showFeedback("wrong", question);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putParcelable("player", player);
        bundle.putParcelableArrayList("questionsCategory4", questionsCategory4);

        binding.buttonCat4Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q2Category4Activity.this, Q1Category4Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat4Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q2Category4Activity.this, Q3Category4Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat4Q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q2Category4Activity.this, Q4Category4Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat4End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Q2Category4Activity.this, "You'll be redirected to the Quiz Homepage. Thanks for attempting the quiz.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( Q2Category4Activity.this, QuizFourBlocksActivity.class);
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
        binding.buttonOpt3Answer.setEnabled(false);
    }


}
