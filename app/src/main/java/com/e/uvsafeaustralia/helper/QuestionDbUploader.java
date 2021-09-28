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
import static com.e.uvsafeaustralia.models.QuestionModel.EnumQCategory.CATEGORY3;
import static com.e.uvsafeaustralia.models.QuestionModel.EnumQCategory.CATEGORY4;

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
                        CATEGORY1,
                        3,
                        "I’m a friendly sun, what is my UV level?",
                        "Nov - Jan",
                        "Feb - Apr",
                        "May - Jul",
                        "Aug - Oct",
                        "May - Jul",
                        "Wingter is when the UV level is at its lowest daily average.");
                dbManager.insertQuestion(
                        CATEGORY1,
                        4,
                        "Can I feel UV radiation?",
                        "Yes",
                        "No",
                        null,
                        null,
                        "No",
                        "UV radiation cannot be felt. The weather condition doesn’t represent UV level.");
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

                dbManager.insertQuestion(
                        CATEGORY2,
                        3,
                        "Which one I should avoid when playing outside?",
                        "play under a tree",
                        "Play on an open area/directly under the sun",
                        "Play under a shelter/shady area",
                        null,
                        "Play on an open area/directly under the sun",
                        "Playing under sun will serious hurt the skin by sunrays.");
                dbManager.insertQuestion(
                        CATEGORY2,
                        4,
                        "What skin protection measure should I take when I’m outside?",
                        "slip, slop",
                        "slop,shade,seek",
                        "Both a and b",
                        null,
                        "Both a and b",
                        "The best practice of skin protection is to take all the measures.");
                dbManager.insertQuestion(
                        CATEGORY3,
                        1,
                        "I dont need to reapply SPF50 Sunscreen too often ?",
                        "True",
                        "False",
                        null,
                        null,
                        "False",
                        "Added later");
                dbManager.insertQuestion(
                        CATEGORY3,
                        2,
                        "Which type of Sunscreen is absorbed into the Skin ?",
                        "Physical Sunscreen",
                        "Chemical Sunscreen",
                        null,
                        null,
                        "Chemical Sunscreen",
                        "Added later");
                dbManager.insertQuestion(
                        CATEGORY3,
                        3,
                        "What is the recommended Sunscreen level in Australia ?",
                        "SPF 15",
                        "SPF 30",
                        "SPF 50",
                        null,
                        "SPF 30",
                        "Added later");
                dbManager.insertQuestion(
                        CATEGORY3,
                        4,
                        "What are the Active ingredients in Physical Sunscreen that block sunrays ?",
                        "Titanium dioxide",
                        "Zinc oxide",
                        "Both A&B",
                        null,
                        "Both A&B",
                        "Added later");
                dbManager.insertQuestion(
                        CATEGORY4,
                        1,
                        "What is true about melanoma?",
                        "It is a serious form of skin cancer",
                        "It may develop from a mole",
                        "Both A & B",
                        null,
                        "Both A & B",
                        "Both answers are correct");
                dbManager.insertQuestion(
                        CATEGORY4,
                        2,
                        "Melanoma often develops from which of these skin features?",
                        "Wart",
                        "Mole",
                        "Scar",
                        null,
                        "Mole",
                        "Mole is the sign of melanoma");
                dbManager.insertQuestion(
                        CATEGORY4,
                        3,
                        "Which of these may be a warning sign of melanoma?",
                        "A mole that is new or growing",
                        "Varied colors in a mole",
                        "Both A & B",
                        null,
                        "Both A & B",
                        "Both these are sign of melanoma");
                dbManager.insertQuestion(
                        CATEGORY4,
                        4,
                        "What's an important part of the body to protect with sunscreen?",
                        "Face",
                        "Ears",
                        "Arms and Hands",
                        "All the above",
                        "All the above",
                        "Sunscreen should be put everywhere that exposured.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbManager.close();
    }
}
