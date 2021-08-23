package com.e.uvsafeaustralia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText locationTxt;
    private String location;
    private UtilTools storedLocation;
    private TextView textViewFeedbackToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFeedbackToast = findViewById(R.id.textViewFeedbackToast);
        Button setLocationBtn = findViewById(R.id.locationBtn);
        setLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove toast from view
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(textViewFeedbackToast.getWindowToken(), 0);

                storeLocation();
                locationTxt = (EditText) findViewById(R.id.editTextLocation);
                location = locationTxt.getText().toString();

                if (location.equals("")) {
                    Toast.makeText(MainActivity.this, "You need to give us a location.", Toast.LENGTH_LONG).show();
                }
                else {
                    startHomeActivity(location);
                }

            }
        });
//        if (!new UtilTools(this).isLocationEmpty()) {
//            startHomeActivity();
//        }
    }

    private void storeLocation() {
        if (new UtilTools(this).isLocationEmpty()) {
            locationTxt = (EditText) findViewById(R.id.editTextLocation);
            location = locationTxt.getText().toString();

            if (location.equals("")) {
                Toast.makeText(MainActivity.this, "You need to give us a location.", Toast.LENGTH_LONG).show();
            } else {
                new UtilTools(this).setLocation(location);
            }
        }
        else {
            locationTxt.setText(UtilTools.getLocation());
        }
    }

    private void startHomeActivity(String location) {
        Toast.makeText(MainActivity.this, "Thank you. Let's see the weather conditions outside...", Toast.LENGTH_LONG).show();
        Intent homeIntent = new Intent( MainActivity.this , MainFunction.class);
        homeIntent.putExtra("location", location);
        startActivity(homeIntent);
    }

}