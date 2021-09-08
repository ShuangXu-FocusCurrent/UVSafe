package com.e.uvsafeaustralia;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;

public class DBUploadActivity extends AppCompatActivity {
    protected DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        dbManager = new DBManager(this);

        insertData();

        Bundle bundle = new Bundle();
        bundle.putString("suburb","1");
        Intent slideIntent = new Intent( DBUploadActivity.this ,SlideActivity.class);
        slideIntent.putExtras(bundle);
        startActivity(slideIntent);
    }

    private void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertData(){
        AssetManager assetManager = this.getAssets();
        openDbManager();
        try {
            if (dbManager.isDbEmpty() == true) {
                InputStream inputStream = assetManager.open("postcodevictoria.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] locationAry = line.split(",");
                    String postcode = locationAry[0];
                    String suburb = locationAry[1];
                    String latitude = locationAry[2];
                    String longitude = locationAry[3];
                    dbManager.insertLocation(postcode, suburb, latitude, longitude);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbManager.close();
    }
}
