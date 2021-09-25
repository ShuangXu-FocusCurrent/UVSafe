package com.e.uvsafeaustralia.views.quiz.Category2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.FragmentQ1Category2Binding;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import static com.e.uvsafeaustralia.views.functionsFragment.QuizPageFragment.player;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.getUserAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.questionsCategory2;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.recordAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.showFeedback;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory2;


public class Q1Category2Fragment extends Fragment {
    private FragmentQ1Category2Binding binding;
    private QuestionModel question;
    public static AnswerModel userAnswerC2Q1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQ1Category2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        question = new QuestionModel();
        for (QuestionModel questionItem : questionsCategory2) {
            if (questionItem.getqNumber() == 1)
                question = questionItem;
        }

        binding.textViewCat2Q1.setText(question.getQuestion());
        binding.buttonOpt1Answer.setText(question.getAnswerOption1());
        binding.buttonOpt2Answer.setText(question.getAnswerOption2());
        binding.buttonOpt3Answer.setText(question.getAnswerOption3());

        userAnswerC2Q1 = getUserAnswer(player, question);
        if (userAnswerC2Q1.getId() != 0) {
            String selected = userAnswerC2Q1.getSelected();
            if (binding.buttonOpt1Answer.getText().equals(selected))
                binding.buttonOpt1Answer.setBackgroundColor(Color.YELLOW);
            if (binding.buttonOpt2Answer.getText().equals(selected))
                binding.buttonOpt2Answer.setBackgroundColor(Color.YELLOW);
            if (binding.buttonOpt3Answer.getText().equals(selected))
                binding.buttonOpt3Answer.setBackgroundColor(Color.YELLOW);
            disableAnswerOptions();
        }

        binding.buttonCat2Q1.setBackgroundColor(Color.MAGENTA);
        binding.buttonOpt1Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(Color.YELLOW);
                binding.buttonOpt2Answer.setBackgroundColor(Color.LTGRAY);
                binding.buttonOpt3Answer.setBackgroundColor(Color.LTGRAY);
                showFeedback("Right answer", question.getCorrect(), question.getAnswerExplain(), getActivity());
                // record answer
                userAnswerC2Q1.setUser(player);
                userAnswerC2Q1.setQuestion(question);
                userAnswerC2Q1.setSelected(question.getAnswerOption1());
                userAnswerC2Q1.setStatus(1);
                recordAnswer(userAnswersCategory2, userAnswerC2Q1);
                disableAnswerOptions();
            }
        });

        binding.buttonOpt2Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(Color.LTGRAY);
                binding.buttonOpt2Answer.setBackgroundColor(Color.YELLOW);
                binding.buttonOpt3Answer.setBackgroundColor(Color.LTGRAY);
                showFeedback("Wrong answer", question.getCorrect(), question.getAnswerExplain(), getActivity());
                // add answer to answermodel instance
                userAnswerC2Q1.setUser(player);
                userAnswerC2Q1.setQuestion(question);
                userAnswerC2Q1.setSelected(question.getAnswerOption2());
                userAnswerC2Q1.setStatus(0);
                recordAnswer(userAnswersCategory2, userAnswerC2Q1);
                disableAnswerOptions();
            }
        });

        binding.buttonOpt3Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(Color.LTGRAY);
                binding.buttonOpt2Answer.setBackgroundColor(Color.LTGRAY);
                binding.buttonOpt3Answer.setBackgroundColor(Color.YELLOW);
                showFeedback("Wrong answer", question.getCorrect(), question.getAnswerExplain(), getActivity());
                // add answer to answermodel instance
                userAnswerC2Q1.setUser(player);
                userAnswerC2Q1.setQuestion(question);
                userAnswerC2Q1.setSelected(question.getAnswerOption3());
                userAnswerC2Q1.setStatus(0);
                recordAnswer(userAnswersCategory2, userAnswerC2Q1);
                disableAnswerOptions();
            }
        });

        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewCategory2);
        binding.buttonCat2Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q1Category2Fragment_to_q2Category2Fragment);
            }
        });

        binding.buttonCat2Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q1Category2Fragment_to_q3Category2Fragment);
            }
        });

        binding.buttonCat2Q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q1Category2Fragment_to_q4Category2Fragment);
            }
        });

        binding.buttonCat2End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "You'll be redirected to the Quiz Homepage. Thanks for attempting the quiz.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent( requireActivity(), QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void disableAnswerOptions() {
        binding.buttonOpt1Answer.setEnabled(false);
        binding.buttonOpt2Answer.setEnabled(false);
        binding.buttonOpt3Answer.setEnabled(false);
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}