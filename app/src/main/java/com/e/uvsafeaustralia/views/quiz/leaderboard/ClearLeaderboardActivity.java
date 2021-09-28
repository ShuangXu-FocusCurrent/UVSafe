package com.e.uvsafeaustralia.views.quiz.leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.e.uvsafeaustralia.databinding.ActivityClearLeaderboardBinding;
import com.e.uvsafeaustralia.db.DBManager;
import com.e.uvsafeaustralia.views.MainFunction;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

public class ClearLeaderboardActivity extends AppCompatActivity {
    private ActivityClearLeaderboardBinding binding;
    protected DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClearLeaderboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dbManager = new DBManager(this);

        binding.btnCancelClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(ClearLeaderboardActivity.this, QuizFourBlocksActivity.class);
                startActivity(cancelIntent);
            }
        });

        binding.btnClearLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllUsersAnswers();
                deleteAllUsers();
                Toast.makeText(ClearLeaderboardActivity.this, "Leaderboard has been cleared successfully", Toast.LENGTH_LONG).show();
                Intent cancelIntent = new Intent(ClearLeaderboardActivity.this, MainFunction.class);
                startActivity(cancelIntent);
            }
        });
    }

    private void deleteAllUsersAnswers() {
        openDbManager();
        dbManager.deleteAllUsersAnswers();
        dbManager.close();
    }

    private void deleteAllUsers() {
        openDbManager();
        dbManager.deleteAllUsers();
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