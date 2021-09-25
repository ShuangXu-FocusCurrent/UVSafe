package com.e.uvsafeaustralia.views.quiz.Category1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.FragmentQ2Category1Binding;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import static com.e.uvsafeaustralia.views.functionsFragment.QuizPageFragment.player;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.getUserAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.questionsCategory1;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.recordAnswer;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory1;


public class Q2Category1Fragment extends Fragment {
    private FragmentQ2Category1Binding binding;
    private QuestionModel question;
    public static AnswerModel userAnswerC1Q2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQ2Category1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        question = new QuestionModel();
        for (QuestionModel questionItem : questionsCategory1) {
            if (questionItem.getqNumber() == 2)
                question = questionItem;
        }

        binding.textViewCat1Q2.setText(question.getQuestion());
        binding.buttonOpt1Answer.setText(question.getAnswerOption1());
        binding.buttonOpt2Answer.setText(question.getAnswerOption2());

        userAnswerC1Q2 = getUserAnswer(player, question);
        if (userAnswerC1Q2.getId() != 0) {
            String selected = userAnswerC1Q2.getSelected();
            if (binding.buttonOpt1Answer.getText().equals(selected))
                binding.buttonOpt1Answer.setBackgroundColor(Color.YELLOW);
            if (binding.buttonOpt2Answer.getText().equals(selected))
                binding.buttonOpt2Answer.setBackgroundColor(Color.YELLOW);
            disableAnswerOptions();
        }

        binding.buttonCat1Q2.setBackgroundColor(Color.MAGENTA);
        binding.buttonOpt1Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(Color.YELLOW);
                binding.buttonOpt2Answer.setBackgroundColor(Color.LTGRAY);
                // show wrong answer feedback
                Toast.makeText(getActivity(), "Right answer", Toast.LENGTH_LONG).show();
                // record answer
                userAnswerC1Q2.setUser(player);
                userAnswerC1Q2.setQuestion(question);
                userAnswerC1Q2.setSelected(question.getAnswerOption1());
                userAnswerC1Q2.setStatus(1);
                recordAnswer(userAnswersCategory1, userAnswerC1Q2);
                disableAnswerOptions();
            }
        });

        binding.buttonOpt2Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(Color.LTGRAY);
                binding.buttonOpt2Answer.setBackgroundColor(Color.YELLOW);
                // show right answer feedback
                Toast.makeText(getActivity(), "Wrong answer", Toast.LENGTH_LONG).show();
                // add answer to answermodel instance
                userAnswerC1Q2.setUser(player);
                userAnswerC1Q2.setQuestion(question);
                userAnswerC1Q2.setSelected(question.getAnswerOption2());
                userAnswerC1Q2.setStatus(0);
                recordAnswer(userAnswersCategory1, userAnswerC1Q2);
                disableAnswerOptions();
            }
        });

        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewCategory1);
        binding.buttonCat1Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q2Category1Fragment_to_q1Category1Fragment);
            }
        });

        binding.buttonCat1Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q2Category1Fragment_to_q3Category1Fragment);
            }
        });

        binding.buttonCat1Q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q2Category1Fragment_to_q4Category1Fragment);
            }
        });

        binding.buttonCat1End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( requireActivity(), QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void disableAnswerOptions() {
        binding.buttonOpt1Answer.setEnabled(false);
        binding.buttonOpt2Answer.setEnabled(false);
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}