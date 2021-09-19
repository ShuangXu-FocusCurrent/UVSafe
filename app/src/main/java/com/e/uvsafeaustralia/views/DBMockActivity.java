package com.e.uvsafeaustralia.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.db.DBManager;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;

import java.util.ArrayList;

import static com.e.uvsafeaustralia.models.QuestionModel.EnumQCategory;

public class DBMockActivity extends AppCompatActivity {
    protected DBManager dbManager;
    ArrayList<UserModel> userList = new ArrayList<>();
    ArrayList<QuestionModel> questionList = new ArrayList<>();
    QuestionModel question1Cat1 = new QuestionModel();
    QuestionModel question2Cat1 = new QuestionModel();
    QuestionModel question1Cat2 = new QuestionModel();
    QuestionModel question2Cat2 = new QuestionModel();

    UserModel user1 = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_mock);

        dbManager = new DBManager(this);

        Boolean isSuccess;

        // User db Test scenarios

//        // Add 1st user when User table is empty
//        // new user data must be inserted
//        isSuccess = testAddUser("Sammy");
//        System.out.println("Add 1st user " + isSuccess);
//
//        // Try add the 1st user again
//        // user data must not be inserted
//        isSuccess = testAddUser("Sammy");
//        System.out.println("Add the same user " + isSuccess);
//
//        // Try add the 2nd user
//        // new user data must be inserted
//        isSuccess = testAddUser("Oliver");
//        System.out.println("Add 2nd user " + isSuccess);

        // Answer DB Test scenarios
        // get all users
        userList = getUserList();
        // Retrieve all questions in the db at the start of the quiz
        getQuestions();
        // sort questions into category and sequence
        for (QuestionModel question : questionList)
            sortQuestions(question);

        // Sammy starts a quiz

        // check if Sammy exist
        // scenario 1 - Sammy is a new user
        if (isUserTableEmpty() || !userList.contains("Sammy")) {
            // add user record in db
            insertUser("Sammy");
            // update userList
            userList = getUserList();
        }
        // add Sammy as a player
        for (UserModel user : userList) {
            if (user.getNickName().equals("Sammy"))
                user1 = user;
        }
        // Sammy answers all questions
        // Sammy answers Category 1
        // Sammy answers question1 wrong
        testAddAnswer(user1, question1Cat1, "Below 3", 0);
        // Sammy  answer question2 right
        testAddAnswer(user1, question2Cat1, "Below 3", 1);

        // Sammy answers Category 2
        // Sammy answers question 1 right
        testAddAnswer(user1, question2Cat1, "Broad brimmed hat",1);
        // Sammy answers question 2 wrong
        testAddAnswer(user1, question2Cat2, "Hands", 0);

        // Sammy click finish and go to Quiz homepage



        Intent homeIntent = new Intent( DBMockActivity.this , SlideActivity.class);
        startActivity(homeIntent);
    }

//    private Boolean testAddUser(String user) {
//        boolean success = false;
//
//        // test scenarios
//        // New user opens the Quiz page
//        // Add name - Sammy, then click Confirm
//        // in the app logic, this method will be called to check if db insert is successful/not
//        if (isUserTableEmpty()) {
//            insertUser(user);
//            success = true;
//        }
//        else {
//            if (getUserList().contains(user)) {
//                success = false;
//            }
//            if (!getUserList().contains(user)) {
//                insertUser(user);
//                success = true;
//            }
//        }
//        return success;
//    }

    private Boolean isUserAdded(String nickname) {
        boolean isAdded = false;
        // if user is a new user
        if (isUserTableEmpty() || !userList.contains(nickname)) {
            insertUser(nickname);
            isAdded = true;
        }
        else {
            for (UserModel user : userList) {
                if (user.getNickName().equals(nickname))
                    System.out.println("user already exist");
            }
        }
        return isAdded;
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
        StringBuilder s = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                userList.add(new UserModel(c.getInt(0), c.getString(1)));
            } while (c.moveToNext());
        }
        dbManager.close();
        return userList;
    }

    private void insertUser(String user) {
        openDbManager();
        dbManager.insertUser(user);
        dbManager.close();
    }

    // Test get all quiz questions at the start of Quiz

    private void getQuestions() {
        String[] questionListAry = testGetQuestionList().split("\n");
        for (String question : questionListAry) {
            String[] questionListRaw = question.split(";");
            QuestionModel questionModel = new QuestionModel(
                    Integer.parseInt(questionListRaw[0]),
                    EnumQCategory.valueOf(questionListRaw[1]),
                    Integer.parseInt(questionListRaw[2]),
                    questionListRaw[3],
                    questionListRaw[4],
                    questionListRaw[5],
                    questionListRaw[6],
                    questionListRaw[7],
                    questionListRaw[8],
                    questionListRaw[9]
            );
            questionList.add(questionModel);
        }
    }

    private String testGetQuestionList() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = dbManager.getAllQuestions();
        StringBuilder s = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                s.append(c.getInt(0)).append(";").append(c.getString(1)).append(";")
                        .append(c.getInt(2)).append(";").append(c.getString(3)).append(";")
                        .append(c.getString(4)).append(";").append(c.getString(5)).append(";")
                        .append(c.getString(6)).append(";").append(c.getString(7)).append(";")
                        .append(c.getString(8)).append(";").append(c.getString(9)).append("\n");
            } while (c.moveToNext());
        }
        dbManager.close();
        return s.toString();
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

    private void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
