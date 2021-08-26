package com.e.uvsafeaustralia;

import android.content.Intent;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    protected static DBManager dbManager;
    private Cursor locations;
    List<LocationModel> locationList = new ArrayList<LocationModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        fillLocationList();

        dbManager = new DBManager(LocationActivity.this);

        editTextSetLocation = findViewById(R.id.editTextSetLocation);

        Button searchLocation = findViewById(R.id.searchBtn);
        searchLocation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String locationText = editTextSetLocation.getText().toString();
                editTextSetLocation.onEditorAction(EditorInfo.IME_ACTION_DONE);

                locationrv = findViewById(R.id.rvLocations);
                locationrv.setHasFixedSize(true);

                linearLayoutManager = new LinearLayoutManager(LocationActivity.this);
                locationrv.setLayoutManager(linearLayoutManager);

                locationACAdapter = new LocationAdapter(locationList, LocationActivity.this);
                locationrv.setAdapter(locationACAdapter);


            }
        });
    }

    private void fillLocationList() {
        LocationModel l0 = new LocationModel(0, "200", "Australian National University", "ACT", "-35.280", "149.120");
        LocationModel l1 = new LocationModel(1, "221", "Barton", "ACT", "-35.200", "149.100");
        LocationModel l2 = new LocationModel(2, "800", "Darwin", "NT", "-12.800", "130.960");
        LocationModel l3 = new LocationModel(3, "801", "Darwin", "NT", "-12.800", "130.960");
        LocationModel l4 = new LocationModel(4, "804", "Parap", "NT", "-12.430", "130.840");

        locationList.addAll(Arrays.asList( new LocationModel[] {l0, l1, l2, l3, l4}));
    }


//        clearSearch = findViewById(R.id.imageViewClearIcn);
////        locationACAdapter = new LocationAdapter(this, R.layout.search_row_location, location,null)
//          locationrv.setLayoutManager(linearLayoutManager);


//        locationrv.setAdapter(locationACAdapter);
//        clearSearch.setOnClickListener(this);

//        editTextSetLocation.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });



    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.location_list, menu);
        return true;
    }

    public String getLocationList(String location) {
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        locations = dbManager.getLocationsBySuburb(location);
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
}
