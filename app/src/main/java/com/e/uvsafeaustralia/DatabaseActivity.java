package com.e.uvsafeaustralia;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DatabaseActivity extends AppCompatActivity {
    protected DBManager dbManager;
    protected TextView textViewDb;
    protected RecyclerView rv_Locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        textViewDb = findViewById(R.id.textViewDb);
        rv_Locations = findViewById(R.id.recyclerViewLocations);

        dbManager = new DBManager(this);

        insertData();

        Bundle bundle = new Bundle();
        bundle.putString("suburb","1");
        Intent slideIntent = new Intent( DatabaseActivity.this ,SlideActivity.class);
        slideIntent.putExtras(bundle);
        startActivity(slideIntent);
    }

    public void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(){
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
                    String state = locationAry[2];
                    String latitude = locationAry[3];
                    String longitude = locationAry[4];
                    dbManager.insertLocation(postcode, suburb, state, latitude, longitude);
                }
                inputStream.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        dbManager.close();
    }

    public String readData() {
        openDbManager();
        Cursor c = dbManager.getAllLocations();
        StringBuilder s = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                s.append("postcode: ").append(c.getString(0)).append("\t").append(", suburb: ").append(c.getString(1)).append("\t").append(", state: ").append(c.getString(2)).append("\t").append(", latitude: ").append(c.getString(3)).append("\t").append(", longitude: ").append(c.getString(4)).append("\n");
            } while (c.moveToNext());
        }
        dbManager.close();

        return s.toString();

    }

    public void deleteData(String id) {
        openDbManager();
        dbManager.deleteLocation(id);
        textViewDb.setText(readData());
        dbManager.close();
    }
}
