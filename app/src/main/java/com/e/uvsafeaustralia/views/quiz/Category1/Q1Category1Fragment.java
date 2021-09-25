package com.e.uvsafeaustralia.views.quiz.Category1;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
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
import com.e.uvsafeaustralia.databinding.FragmentQ1Category1Binding;
import com.e.uvsafeaustralia.db.DBManager;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import java.util.ArrayList;

import static com.e.uvsafeaustralia.views.functionsFragment.QuizPageFragment.player;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.questionsList;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory1;


public class Q1Category1Fragment extends Fragment {
    private FragmentQ1Category1Binding binding;
    protected DBManager dbManager;
    public static ArrayList<QuestionModel> questionsCategory1;
    private QuestionModel question;
    public static AnswerModel userAnswerC1Q1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQ1Category1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        dbManager = new DBManager(getActivity());
        questionsCategory1 = new ArrayList<>();
        question = new QuestionModel();
        for (QuestionModel questionItem : questionsList) {
            if (questionItem.getqCategory().equals(QuestionModel.EnumQCategory.CATEGORY1)) {
                questionsCategory1.add(questionItem);
                if (questionItem.getqNumber() == 1)
                    question = questionItem;
            }
        }

        binding.textViewCat1Q1.setText(question.getQuestion());
        binding.buttonOpt1Answer.setText(question.getAnswerOption1());
        binding.buttonOpt2Answer.setText(question.getAnswerOption2());

        userAnswerC1Q1 = getUserAnswer(player, question);
        if (userAnswerC1Q1.getId() != 0) {
            String selected = userAnswerC1Q1.getSelected();
            if (binding.buttonOpt1Answer.getText().equals(selected))
                binding.buttonOpt1Answer.setBackgroundColor(Color.YELLOW);
            if (binding.buttonOpt2Answer.getText().equals(selected))
                binding.buttonOpt2Answer.setBackgroundColor(Color.YELLOW);
        }

        binding.buttonCat1Q1.setBackgroundColor(Color.MAGENTA);
        binding.buttonOpt1Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(Color.YELLOW);
                binding.buttonOpt2Answer.setBackgroundColor(Color.LTGRAY);
                // show wrong answer feedback
//                Toast.makeText(getActivity(), "Wrong answer", Toast.LENGTH_LONG).show();
                // record answer
                userAnswerC1Q1.setUser(player);
                userAnswerC1Q1.setQuestion(question);
                userAnswerC1Q1.setSelected(question.getAnswerOption1());
                userAnswerC1Q1.setStatus(0);
                recordAnswer(userAnswerC1Q1);
            }
        });

        binding.buttonOpt2Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buttonOpt1Answer.setBackgroundColor(Color.LTGRAY);
                binding.buttonOpt2Answer.setBackgroundColor(Color.YELLOW);
                // show right answer feedback
//                Toast.makeText(getActivity(), "Right answer", Toast.LENGTH_LONG).show();
                // add answer to answermodel instance
                userAnswerC1Q1.setUser(player);
                userAnswerC1Q1.setQuestion(question);
                userAnswerC1Q1.setSelected(question.getAnswerOption2());
                userAnswerC1Q1.setStatus(1);
                recordAnswer(userAnswerC1Q1);
            }
        });

        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewCategory1);

        binding.buttonCat1Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q1Category1Fragment_to_q2Category1Fragment);
            }
        });

        binding.buttonCat1Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q1Category1Fragment_to_q3Category1Fragment);
            }
        });

        binding.buttonCat1Q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q1Category1Fragment_to_q4Category1Fragment);
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

    private void updateAnswer(AnswerModel answer) {
        openDbManager();
        dbManager.updateAnswer(answer.getUser(), answer.getQuestion(), answer.getSelected(), answer.getStatus());
        dbManager.close();
    }

    private void insertAnswer(AnswerModel answer) {
        openDbManager();
        dbManager.insertAnswer(answer.getUser(), answer.getQuestion(), answer.getSelected(), answer.getStatus());
        dbManager.close();
    }

    private void recordAnswer(AnswerModel answer) {
        if (userAnswersCategory1.size() != 0) {
            for (AnswerModel answerItem : userAnswersCategory1) {
                if (answerItem.getQuestion().equals(answer.getQuestion())) {
                    updateAnswer(userAnswerC1Q1);
                    userAnswersCategory1.set(userAnswersCategory1.indexOf(answerItem), answer);

                }
            }
        }
        if (userAnswersCategory1.size() == 0) {
            insertAnswer(userAnswerC1Q1);
            userAnswersCategory1.add(userAnswerC1Q1);
        }
    }

    private AnswerModel getUserAnswer(UserModel user, QuestionModel question) {
        AnswerModel answer = new AnswerModel();
        openDbManager();
        Cursor c = dbManager.getUserAnswerByQuestion(user.getUserId(), question.getqId());
        int answerId = 0;
        String selected = "";
        int status = 0;
        if (c.moveToFirst()) {
            do {
                answerId = c.getInt(0);
                selected = c.getString(3);
                status = c.getInt(4);
                answer = new AnswerModel(answerId, user, question, selected, status);
            } while (c.moveToNext());
        }
        return answer;
    }

    private void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}