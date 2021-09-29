package com.e.uvsafeaustralia.views.quiz.Category2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.databinding.ActivityQ2Category2Binding;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import java.util.ArrayList;

import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.NOT_SELECTED_BTN_COLOUR;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.SELECTED_BTN_COLOUR;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.getUserAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.recordAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory2;

public class Q4Category2Activity extends AppCompatActivity {
    private ActivityQ2Category2Binding binding;
    private QuestionModel question;
    private AnswerModel userAnswerC2Q4;
    private ArrayList<QuestionModel> questionsCategory2;
    private UserModel player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQ2Category2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle getBundle = getIntent().getExtras();
        player = getBundle.getParcelable("player");
        questionsCategory2 = getBundle.getParcelableArrayList("questionsCategory2");

        question = new QuestionModel();
        // Set question as default on the quiz category 2

        for (QuestionModel questionItem : questionsCategory2)
            if (questionItem.getqNumber() == 4)
                question = questionItem;

        binding.textViewCat2Q1.setText(question.getQuestion());
        binding.buttonCat2Q4.setVisibility(View.INVISIBLE);
        binding.buttonCat2Q4Active.setVisibility(View.VISIBLE);
        binding.buttonOpt1Answer.setText(question.getAnswerOption1());
        binding.buttonOpt2Answer.setText(question.getAnswerOption2());
        binding.buttonOpt3Answer.setText(question.getAnswerOption3());

        // check if user already selected an answer
        // if they have, show the selected answer and feedback
        userAnswerC2Q4 = getUserAnswer(player, question);
        if (userAnswerC2Q4.getId() != 0) {
            String selected = userAnswerC2Q4.getSelected();
            if (binding.buttonOpt1Answer.getText().equals(selected)) {
                binding.buttonOpt1Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                showFeedback("wrong", question);
            }
            if (binding.buttonOpt2Answer.getText().equals(selected)) {
                binding.buttonOpt2Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                showFeedback("wrong", question);
            }
            if (binding.buttonOpt3Answer.getText().equals(selected)) {
                binding.buttonOpt3Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                showFeedback("right", question);
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
                userAnswerC2Q4.setUser(player);
                userAnswerC2Q4.setQuestion(question);
                userAnswerC2Q4.setSelected(question.getAnswerOption1());
                userAnswerC2Q4.setStatus(0);
                recordAnswer(userAnswersCategory2, userAnswerC2Q4);
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
                userAnswerC2Q4.setUser(player);
                userAnswerC2Q4.setQuestion(question);
                userAnswerC2Q4.setSelected(question.getAnswerOption2());
                userAnswerC2Q4.setStatus(0);
                recordAnswer(userAnswersCategory2, userAnswerC2Q4);
                disableAnswerOptions();
                showFeedback("wrong", question);
            }
        });

        binding.buttonOpt3Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                binding.buttonOpt2Answer.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
                binding.buttonOpt3Answer.setBackgroundColor(SELECTED_BTN_COLOUR);
                // add answer to answermodel instance
                userAnswerC2Q4.setUser(player);
                userAnswerC2Q4.setQuestion(question);
                userAnswerC2Q4.setSelected(question.getAnswerOption3());
                userAnswerC2Q4.setStatus(1);
                recordAnswer(userAnswersCategory2, userAnswerC2Q4);
                disableAnswerOptions();
                showFeedback("right", question);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putParcelable("player", player);
        bundle.putParcelableArrayList("questionsCategory2", questionsCategory2);

        binding.buttonCat2Q1Incative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q4Category2Activity.this, Q1Category2Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat2Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q4Category2Activity.this, Q2Category2Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat2Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Q4Category2Activity.this, Q3Category2Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.buttonCat2End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Q4Category2Activity.this, "You'll be redirected to the Quiz Homepage. Thanks for attempting the quiz.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( Q4Category2Activity.this, QuizFourBlocksActivity.class);
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
