package com.e.uvsafeaustralia.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.db.DBManager;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.e.uvsafeaustralia.models.QuestionModel.EnumQCategory;

public class DBMockActivity extends AppCompatActivity {
    protected DBManager dbManager;
    ArrayList<UserModel> userList = new ArrayList<>();
    ArrayList<QuestionModel> questionList = new ArrayList<>();
    ArrayList<AnswerModel> allUsersAnswersList = new ArrayList<>();
    ArrayList<QuestionModel> questionsCat1 = new ArrayList<>();
    ArrayList<QuestionModel> questionsCat2 = new ArrayList<>();
    QuestionModel question1Cat1 = new QuestionModel();
    QuestionModel question2Cat1 = new QuestionModel();
    QuestionModel question1Cat2 = new QuestionModel();
    QuestionModel question2Cat2 = new QuestionModel();

    UserModel user1 = new UserModel();
    UserModel user2 = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_mock);

        dbManager = new DBManager(this);

        // Answer DB Test scenarios
        // get all users
        userList = getUserList();
        // Retrieve all questions in the db at the start of the quiz
        testGetQuestionList();
        // sort questions into category and sequence
        // for testing purposes this only takes q1-2 of Category 1 and 2
        for (QuestionModel question : questionList)
            sortQuestions(question);

        // Sammy starts a quiz

        // check if Sammy exist
        // if not add to db and get user data
        user1 = getUser("Sammy");
        // update user list
        userList.add(user1);

        // Sammy answers all questions
        // Sammy answers Category 1
        // Sammy answers question1 right
        testAddAnswer(user1, question1Cat1, "3 or above", 1);
        // Sammy  answer question2 right
        testAddAnswer(user1, question2Cat1, "Below 3", 1);

        // Sammy answers Category 2
        // Sammy answers question 1 right
        testAddAnswer(user1, question2Cat1, "Broad brimmed hat",1);
        // Sammy answers question 2 wrong
        testAddAnswer(user1, question2Cat2, "Hands", 0);

        // Sammy clicks finish and go to Quiz homepage

        // Sammy views his report
        ArrayList<AnswerModel> user1AnswersList = getUserAnswersList(user1);
        if (user1AnswersList.size() != 0)
            for (AnswerModel answerItem : user1AnswersList)
                System.out.println(answerItem.toString());

        // Add and test Leaderboard functionality

        // Add another user - Oliver
        // check if Oliver exist
        // if not, add to db and get user data
        user2 = getUser("Oliver");
        // Add Oliver to user list
        userList.add(user2);

        // Oliver answers 2 questions from category 1
        // Oliver answers question1 right
        testAddAnswer(user2, question1Cat1, "3 or above", 1);
        // Oliver  answer question2 right
        testAddAnswer(user2, question2Cat1, "Below 3", 1);

        // Oliver clicks finish and go to Quiz homepage

        // Oliver views his report
        ArrayList<AnswerModel> user2AnswersList = getUserAnswersList(user2);
        if (user2AnswersList.size() != 0) {
            for (AnswerModel answerItem : user2AnswersList)
                System.out.println(answerItem.toString());
        }

        // Sammy or Oliver views the Leaderboard

        Map<String, Integer> mapCorrect = new HashMap<>();
        Map<String, Integer> mapAttempt = new HashMap<>();
        openDbManager();
        for (UserModel user : userList) {
            int countCorrect = 0;
            int countAttempt = 0;
            ArrayList<AnswerModel> userAnswers = getUserAnswersList(user);
            countAttempt = userAnswers.size();
            for (AnswerModel answerItem : userAnswers) {
                if (answerItem.getStatus() == 1) countCorrect++;
            }
            mapCorrect.put(user.getNickName(), countCorrect);
            mapAttempt.put(user.getNickName(), countAttempt);
        }
        // sort the users according to the one with most correct answers
        List<Map.Entry<String, Integer>> correctList = new ArrayList<>(mapCorrect.entrySet());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            correctList.sort(Map.Entry.comparingByValue());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            correctList.forEach(System.out::println);
        }
        // rank users and show total correct and total attempt
        for (UserModel user : userList)
            for (Map.Entry<String, Integer> item: correctList)
                for (String attempt : mapAttempt.keySet())
                    if (item.getKey().equals(user.getNickName()) && attempt.equals(user.getNickName()))
                        System.out.println(user.getNickName() + ", Total correct: " + item.getValue() + ", Total attempt: " + mapAttempt.get(attempt));

        // Oliver wants to try the quiz again
        // He clicks on the Try Again button

        // Delete Oliver's record from the Answer table
        System.out.println(getAllUsersAnswersList());
        deleteAnswers(user2.getUserId());
        System.out.println(getAllUsersAnswersList());


        Intent homeIntent = new Intent( DBMockActivity.this , SlideActivity.class);
        startActivity(homeIntent);
    }

    private UserModel getUser(String nickname) {
        UserModel thisUser = new UserModel();
        if (isUserTableEmpty())
            // add user record in db
            insertUser(nickname);
        else {
            thisUser = getUserDB(nickname);
            if (thisUser == null);
            insertUser(nickname);
        }
        // update userList
        thisUser = getUserDB(nickname);
        return thisUser;
    }

    // Test add new users
    private Boolean isUserTableEmpty () {
        boolean isTableEmpty = false;
        openDbManager();
        isTableEmpty = dbManager.isUserDbEmpty();
        dbManager.close();
        return isTableEmpty;
    }

    private ArrayList<UserModel> getUserList() {
        openDbManager();
        Cursor c = dbManager.getAllUsers();
        int userId = 0;
        String nickname = "";
        if (c.moveToFirst()) {
            do {
                userId = c.getInt(0);
                nickname = c.getString(1);
                userList.add(new UserModel(userId, nickname));
            } while (c.moveToNext());
        }
        dbManager.close();
        return userList;
    }

    private UserModel getUserDB(String newUser) {
        openDbManager();
        Cursor c = dbManager.getUserByNickname(newUser);
        UserModel user = new UserModel();
        int userId = 0;
        String nickname = "";
        if (c.moveToFirst()) {
            do {
                userId = c.getInt(0);
                nickname = c.getString(1);
                user.setUserId(userId);
                user.setNickName(nickname);
            } while (c.moveToNext());
        }
        dbManager.close();
        return user;
    }

    private void insertUser(String user) {
        openDbManager();
        dbManager.insertUser(user);
        dbManager.close();
    }

    // Test get all quiz questions at the start of Quiz

    private void testGetQuestionList() {
        openDbManager();
        Cursor c = dbManager.getAllQuestions();
        int questionId = 0;
        EnumQCategory qCategory;
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
                qCategory = EnumQCategory.valueOf(c.getString(1));
                qNumber = c.getInt(2);
                question = c.getString(3);
                answer1 = c.getString(4);
                answer2 = c.getString(5);
                answer3 = c.getString(6);
                answer4 = c.getString(7);
                correct = c.getString(8);
                explain = c.getString(9);
                questionList.add(new QuestionModel(questionId, qCategory, qNumber, question,
                        answer1, answer2, answer3, answer4, correct, explain));
            } while (c.moveToNext());
        }
        dbManager.close();
    }

    private Boolean testAddAnswer(UserModel userModel, QuestionModel questionModel, String selected,int status) {
        boolean isSuccess = false;
        openDbManager();
        dbManager.insertAnswer(userModel, questionModel, selected, status);
        dbManager.close();
        return isSuccess;
    }

    private void sortQuestions(QuestionModel question) {
        if (question.getqCategory() == EnumQCategory.CATEGORY1) {
            if (question.getqNumber() == 1)
                question1Cat1 = question;
            if (question.getqNumber() == 2)
                question2Cat1 = question;
        }
        if (question.getqCategory() == EnumQCategory.CATEGORY2) {
            if (question.getqNumber() == 1)
                question1Cat2 = question;
            if (question.getqNumber() == 2)
                question2Cat2 = question;
        }
    }

    private ArrayList<AnswerModel> getUserAnswersList(UserModel user) {
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
                for (QuestionModel questionItem : questionList)
                    if (questionItem.getqId() == questionId)
                        thisQuestion = questionItem;
                AnswerModel answer = new AnswerModel(answerId, thisUser, thisQuestion, selected, status);
                userAnswersList.add(answer);
            } while (c.moveToNext());
        }
        dbManager.close();
        return userAnswersList;
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
                for (QuestionModel questionItem : questionList)
                    if (questionItem.getqId() == questionId)
                        thisQuestion = questionItem;
                AnswerModel answer = new AnswerModel(answerId, thisUser, thisQuestion, selected, status);
                answersList.add(answer);
            } while (c.moveToNext());
        }
        dbManager.close();
        return answersList;
    }

    private void deleteAnswers(int userId) {
        openDbManager();
        dbManager.deleteUserAnswers(userId);
        dbManager.close();
    }

    private void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
