package com.e.uvsafeaustralia.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.helper.LocationDbUploader;
import com.e.uvsafeaustralia.helper.QuestionDbUploader;

public class MainActivity extends AppCompatActivity {
    private OneTimeWorkRequest uploadLocationDb, uploadQuestionDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadLocationDb = new OneTimeWorkRequest.Builder(LocationDbUploader.class)
                .addTag("uploadLocation")
                .build();

        WorkManager.getInstance(this).enqueue(uploadLocationDb);

        uploadQuestionDb = new OneTimeWorkRequest.Builder(QuestionDbUploader.class)
                .addTag("uploadQuestion")
                .build();

        WorkManager.getInstance(this).enqueue(uploadQuestionDb);

        Button startAppBtn = findViewById(R.id.startAppBtn);
        startAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent( MainActivity.this , SlideActivity.class);
                startActivity(homeIntent);
            }
        });
    }
}