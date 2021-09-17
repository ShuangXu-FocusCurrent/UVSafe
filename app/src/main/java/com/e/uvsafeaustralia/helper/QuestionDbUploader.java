package com.e.uvsafeaustralia.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;

import com.e.uvsafeaustralia.db.DBManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static com.e.uvsafeaustralia.models.QuestionModel.enumQCategory.CATEGORY1;

public class QuestionDbUploader extends Worker {
    protected DBManager dbManager;

    public QuestionDbUploader(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        dbManager = new DBManager(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        insertQuestionData();
        return Result.success();
    }

    private void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertQuestionData(){
        AssetManager assetManager = getApplicationContext().getAssets();
        openDbManager();
        try {
            dbManager.insertQuestion(
                    CATEGORY1,
                    "I’m a dangerous sun, what could be my UV level?",
                    "Below 3",
                    "3 or above",
                    null,
                    null,
                    "3 or above",
                    "UV level from 3 and above are considered dangerous for your skin.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbManager.close();
    }
}
