package com.e.uvsafeaustralia.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;

import com.e.uvsafeaustralia.db.DBManager;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static com.e.uvsafeaustralia.models.QuestionModel.EnumQCategory.CATEGORY1;
import static com.e.uvsafeaustralia.models.QuestionModel.EnumQCategory.CATEGORY2;

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
        openDbManager();
        try {
            if (dbManager.isQuestionDbEmpty() == true) {
                dbManager.insertQuestion(
                        CATEGORY1,
                        1,
                        "I’m a dangerous sun, what could be my UV level?",
                        "Below 3",
                        "3 or above",
                        null,
                        null,
                        "3 or above",
                        "When UV level is 3 or above it could damage your skin.");
                dbManager.insertQuestion(
                        CATEGORY1,
                        2,
                        "I’m a friendly sun, what is my UV level?",
                        "Below 3",
                        "3 or above",
                        null,
                        null,
                        "Below 3",
                        "When UV level is 3 or above it could damage your skin.");
                dbManager.insertQuestion(
                        CATEGORY2,
                        1,
                        "Which hat is the best option to protect us from sun?",
                        "Broad brimmed hat",
                        "Beret",
                        "Cap",
                        null,
                        "Broad brimmed hat",
                        "Broad brimmed hat covers your face and neck");
                dbManager.insertQuestion(
                        CATEGORY2,
                        2,
                        "Where should I apply sunscreen on my body?",
                        "Hands",
                        "Face and neck",
                        "All parts that are exposed to the sun",
                        null,
                        "All parts that are exposed to the sun",
                        "Every part of your body that are exposed to the sun must be protected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbManager.close();
    }
}
