package com.e.uvsafeaustralia.views.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityQuizFourBlocksBinding;
import com.e.uvsafeaustralia.db.DBManager;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;
import com.e.uvsafeaustralia.views.quiz.Category1.QuizCategory1Activity;
import com.e.uvsafeaustralia.views.quiz.Category2.QuizCategory2Activity;
import com.e.uvsafeaustralia.views.quiz.Category3.QuizCategory3Activity;
import com.e.uvsafeaustralia.views.quiz.Category4.QuizCategory4Activity;
import com.e.uvsafeaustralia.views.quiz.leaderboard.LeaderboardActivity;
import com.e.uvsafeaustralia.views.quiz.leaderboard.OriginalLeaderboardActivity;

import java.util.ArrayList;

import static com.e.uvsafeaustralia.views.functionsFragment.QuizPageFragment.player;
import static com.e.uvsafeaustralia.views.functionsFragment.QuizPageFragment.userList;


public class QuizFourBlocksActivity extends AppCompatActivity {
    private ActivityQuizFourBlocksBinding binding;
    protected DBManager dbManager;
    public static ArrayList<QuestionModel> questionsList;
    public static ArrayList<AnswerModel> allUserAnswers;
    public static ArrayList<AnswerModel> userAnswersCategory1;
    public static ArrayList<AnswerModel> userAnswersCategory2;
    public static ArrayList<AnswerModel> userAnswersCategory3;
    public static ArrayList<AnswerModel> userAnswersCategory4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizFourBlocksBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbManager = new DBManager(this);

        questionsList = getQuestionList();
        allUserAnswers = getUserAnswersList(player);
        if (!allUserAnswers.isEmpty()) {
            binding.tryAgainBtn.setVisibility(View.VISIBLE);
            binding.tryAgainBtnStroke.setVisibility(View.VISIBLE);
        }

        userAnswersCategory1 = new ArrayList<>();
        userAnswersCategory2 = new ArrayList<>();
        userAnswersCategory3 = new ArrayList<>();
        userAnswersCategory4 = new ArrayList<>();

        for (AnswerModel answer : allUserAnswers) {
            if (answer.getQuestion().getqCategory() == QuestionModel.EnumQCategory.CATEGORY1)
                userAnswersCategory1.add(answer);
            if (answer.getQuestion().getqCategory() == QuestionModel.EnumQCategory.CATEGORY2)
                userAnswersCategory2.add(answer);
            if (answer.getQuestion().getqCategory() == QuestionModel.EnumQCategory.CATEGORY3)
                userAnswersCategory3.add(answer);
            if (answer.getQuestion().getqCategory() == QuestionModel.EnumQCategory.CATEGORY4)
                userAnswersCategory4.add(answer);
        }

        if (!userAnswersCategory1.isEmpty()) {
            binding.category1.setEnabled(false);
            binding.category1.setAlpha(0.25f);
        }
        if (!userAnswersCategory2.isEmpty()) {
            binding.category2.setEnabled(false);
            binding.category2.setAlpha(0.25f);
        }
        if (!userAnswersCategory3.isEmpty()) {
            binding.category3.setEnabled(false);
            binding.category3.setAlpha(0.25f);
        }
        if (!userAnswersCategory4.isEmpty()) {
            binding.category4.setEnabled(false);
            binding.category4.setAlpha(0.25f);
        }

            binding.category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizFourBlocksActivity.this, QuizCategory1Activity.class);
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
                ArrayList<AnswerModel> allUsersAnswers = getAllUsersAnswersList();
                if (allUsersAnswers.isEmpty()) {
                    Intent intent = new Intent(QuizFourBlocksActivity.this, OriginalLeaderboardActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(QuizFourBlocksActivity.this, LeaderboardActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAnswers(player.getUserId());
                if (userAnswersCategory1.size() != 0)
                    userAnswersCategory1.clear();
                if (userAnswersCategory2.size() != 0)
                    userAnswersCategory2.clear();
                if (userAnswersCategory3.size() != 0)
                    userAnswersCategory3.clear();
                if (userAnswersCategory4.size() != 0)
                    userAnswersCategory4.clear();
                binding.category1.setEnabled(true);
                binding.category1.setAlpha(1f);
                binding.category2.setEnabled(true);
                binding.category2.setAlpha(1f);
                binding.category3.setEnabled(true);
                binding.category3.setAlpha(1f);
                binding.category4.setEnabled(true);
                binding.category4.setAlpha(1f);
                binding.tryAgainBtn.setVisibility(View.INVISIBLE);
                binding.tryAgainBtnStroke.setVisibility(View.INVISIBLE);
            }
        });
    }

    private ArrayList<QuestionModel> getQuestionList() {
        ArrayList<QuestionModel> questions = new ArrayList<>();
        openDbManager();
        Cursor c = dbManager.getAllQuestions();
        int questionId = 0;
        QuestionModel.EnumQCategory qCategory;
        int qNumber = 0;
        String question = "";
        String answer1 = "";
        String answer2 = "";
        String answer3 = "";
        String answer4 = "";
        String correct = "";
        String explain = "";
        if (c.moveToFirst()) {
            do {
                questionId = c.getInt(0);
                qCategory = QuestionModel.EnumQCategory.valueOf(c.getString(1));
                qNumber = c.getInt(2);
                question = c.getString(3);
                answer1 = c.getString(4);
                answer2 = c.getString(5);
                answer3 = c.getString(6);
                answer4 = c.getString(7);
                correct = c.getString(8);
                explain = c.getString(9);
                questions.add(new QuestionModel(questionId, qCategory, qNumber, question,
                        answer1, answer2, answer3, answer4, correct, explain));
            } while (c.moveToNext());
        }
        dbManager.close();
        return questions;
    }

    public ArrayList<AnswerModel> getUserAnswersList(UserModel user) {
        ArrayList<AnswerModel> userAnswersList = new ArrayList<>();
        openDbManager();
        Cursor c = dbManager.getUserAnswers(user.getUserId());
        if (c.moveToFirst()) {
            do {
                int answerId = c.getInt(0);
                int userId = c.getInt(1);
                UserModel thisUser = new UserModel();
                int questionId = c.getInt(2);
                QuestionModel thisQuestion = new QuestionModel();
                String selected = c.getString(3);
                int status = c.getInt(4);
                for (UserModel userItem: userList)
                    if (userItem.getUserId() == userId)
                        thisUser = userItem;
                for (QuestionModel questionItem : questionsList)
                    if (questionItem.getqId() == questionId)
                        thisQuestion = questionItem;
                AnswerModel answer = new AnswerModel(answerId, thisUser, thisQuestion, selected, status);
                userAnswersList.add(answer);
            } while (c.moveToNext());
        }
        dbManager.close();
        return userAnswersList;
    }

    public void insertAnswer(AnswerModel answer) {
        openDbManager();
        dbManager.insertAnswer(answer.getUser(), answer.getQuestion(), answer.getSelected(), answer.getStatus());
        dbManager.close();
    }

    private void deleteAnswers(int userId) {
        openDbManager();
        dbManager.deleteUserAnswers(userId);
        dbManager.close();
    }

    private ArrayList<AnswerModel> getAllUsersAnswersList() {
        ArrayList<AnswerModel> answersList = new ArrayList<>();
        openDbManager();
        Cursor c = dbManager.getAllAnswers();
        if (c.moveToFirst()) {
            do {
                int answerId = c.getInt(0);
                int userId = c.getInt(1);
                UserModel thisUser = new UserModel();
                int questionId = c.getInt(2);
                QuestionModel thisQuestion = new QuestionModel();
                String selected = c.getString(3);
                int status = c.getInt(4);
                for (UserModel userItem: userList)
                    if (userItem.getUserId() == userId)
                        thisUser = userItem;
                for (QuestionModel questionItem : questionsList)
                    if (questionItem.getqId() == questionId)
                        thisQuestion = questionItem;
                AnswerModel answer = new AnswerModel(answerId, thisUser, thisQuestion, selected, status);
                answersList.add(answer);
            } while (c.moveToNext());
        }
        dbManager.close();
        return answersList;
    }

    private void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}