package com.e.uvsafeaustralia;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LocationActivity extends AppCompatActivity {
    private EditText editTextSetLocation;
    private LinearLayoutManager linearLayoutManager;
    private LocationAdapter locationACAdapter;
    ImageView clearSearch;
    private RecyclerView locationrv;

    protected DBManager dbManager;
    private Cursor locations;
    List<LocationModel> locationList = new ArrayList<LocationModel>();
    List<LocationModel> searchedLocation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        dbManager = new DBManager(LocationActivity.this);

        editTextSetLocation = findViewById(R.id.editTextSetLocation);

        String locationliststr = getLocationList();

        String[] locationListAry = locationliststr.split("\n");
        int count = locationListAry.length;
        System.out.println(count);
        for (String locationraw : locationListAry) {
            String[] locationAry = locationraw.split(",");
            String postcode = locationAry[0];
            String suburb = locationAry[1];
            String state = locationAry[2];
            String latitude = locationAry[3];
            String longitude = locationAry[4];
            LocationModel locationModel = new LocationModel(-1, postcode, suburb, state, latitude, longitude);
            locationList.add(locationModel);
        }
        locationrv = findViewById(R.id.rvLocations);
        locationrv.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(LocationActivity.this);
        locationrv.setLayoutManager(linearLayoutManager);
        locationACAdapter = new LocationAdapter(locationList, LocationActivity.this);
        locationrv.setAdapter(locationACAdapter);


        Button searchLocation = findViewById(R.id.searchBtn);
        searchLocation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = editTextSetLocation.getText().toString();
                editTextSetLocation.onEditorAction(EditorInfo.IME_ACTION_DONE);
                for (LocationModel locationItem : locationList) {
                    location = location.replace("\"", "");
                    if (locationItem.getSuburb().toLowerCase().contains(location.toLowerCase())) {
                        searchedLocation.add(locationItem);
                    }
                }
                if (searchedLocation.isEmpty()) {
                    Toast.makeText(LocationActivity.this, "We cannot find location that match your input.", Toast.LENGTH_LONG).show();
                }
                linearLayoutManager = new LinearLayoutManager(LocationActivity.this);
                locationrv.setLayoutManager(linearLayoutManager);

                locationACAdapter = new LocationAdapter(searchedLocation, LocationActivity.this);
                locationrv.setAdapter(locationACAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.location_list, menu);
        return true;
    }

    public String getLocationList() {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = dbManager.getAllLocations();
        StringBuilder s = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                s.append(c.getString(0)).append(",").append(c.getString(1)).append(",")
                        .append(c.getString(2)).append(",").append(c.getString(3)).append(",")
                        .append(c.getString(4)).append("\n");
            } while (c.moveToNext());
        }
        dbManager.close();
        return s.toString();
    }

    public String getSearchedLocation(String location) {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor c = dbManager.getSearchedLocations(location);
        StringBuilder s = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                s.append(c.getString(0)).append(",").append(c.getString(1)).append(",")
                        .append(c.getString(2)).append(",").append(c.getString(3)).append(",")
                        .append(c.getString(4)).append("\n");
            } while (c.moveToNext());
        }
        dbManager.close();
        return s.toString();
    }
}
