package com.e.uvsafeaustralia.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.e.uvsafeaustralia.db.DBManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LocationDbUploader extends Worker {
    protected DBManager dbManager;

    public LocationDbUploader(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        dbManager = new DBManager(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        insertLocationData();
        return Result.success();
    }

    private void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertLocationData(){
        AssetManager assetManager = getApplicationContext().getAssets();
        openDbManager();
        try {
            if (dbManager.isLocationDbEmpty() == true) {
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
