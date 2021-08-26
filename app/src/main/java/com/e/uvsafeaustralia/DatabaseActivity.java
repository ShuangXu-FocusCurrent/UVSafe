package com.e.uvsafeaustralia;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DatabaseActivity extends AppCompatActivity {
    protected static DBManager dbManager;
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

        textViewDb.setText(readData());

        Intent slideIntent = new Intent( DatabaseActivity.this , LocationActivity.class);
        startActivity(slideIntent);
    }

    public static void openDbManager() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(){
        openDbManager();
        try {
            dbManager.insertLocation("200", "Australian National University", "ACT", "-35.280", "149.120");
            dbManager.insertLocation("221", "Barton", "ACT", "-35.200", "149.100");
            dbManager.insertLocation("800", "Darwin", "NT", "-12.800", "130.960");
            dbManager.insertLocation("801", "Darwin", "NT", "-12.800", "130.960");
            dbManager.insertLocation("804", "Parap", "NT", "-12.430", "130.840");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbManager.close();
    }

    public static String readData() {
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
