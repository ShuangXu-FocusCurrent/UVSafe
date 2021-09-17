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

public class MainActivity extends AppCompatActivity {
    private OneTimeWorkRequest uploadLocationdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadLocationdb = new OneTimeWorkRequest.Builder(LocationDbUploader.class)
                .addTag("uploadLocation")
                .build();

        WorkManager.getInstance(this).enqueue(uploadLocationdb);

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