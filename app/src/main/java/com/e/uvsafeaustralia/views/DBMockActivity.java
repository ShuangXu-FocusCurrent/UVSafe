package com.e.uvsafeaustralia.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.db.DBManager;

public class DBMockActivity extends AppCompatActivity {
    protected DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_mock);

        dbManager = new DBManager(this);

        Boolean isSuccess;
        // Add 1st user when User table is empty
        // new user data must be inserted
        isSuccess = testAddUser("Sammy");
        System.out.println("Add 1st user " + isSuccess);

        // Try add the 1st user again
        // user data must not be inserted
        isSuccess = testAddUser("Sammy");
        System.out.println("Add the same user " + isSuccess);

        // Try add the 2nd user
        // new user data must be inserted
        isSuccess = testAddUser("Oliver");
        System.out.println("Add 2nd user " + isSuccess);

        Intent homeIntent = new Intent( DBMockActivity.this , SlideActivity.class);
        startActivity(homeIntent);
    }

    private Boolean testAddUser(String user) {
        Boolean success = false;

        // test scenarios
        // New user opens the Quiz page
        // Add name - Sammy, then click Confirm
        // in the app logic, this method will be called to check if db insert is successful/not
        if (isUserTableEmpty()) {
            insertUser(user);
            success = true;
        }
        else {
            if (getUserList().contains(user)) {
                success = false;
            }
            if (!getUserList().contains(user)) {
                insertUser(user);
                success = true;
            }
        }
        return success;
    }

    private Boolean isUserTableEmpty () {
        Boolean isTableEmpty = false;
        openDbManager();
        isTableEmpty = dbManager.isUserDbEmpty();
        dbManager.close();
        return isTableEmpty;
    }

    private String getUserList() {
        openDbManager();
        Cursor c = dbManager.getAllUsers();
        StringBuilder s = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                s.append(c.getString(0)).append("\n");
            } while (c.moveToNext());
        }
        dbManager.close();
        return s.toString();
    }

    private void insertUser(String user) {
        openDbManager();
        dbManager.insertUser(user);
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
