package com.e.uvsafeaustralia;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ProtectionActivity extends AppCompatActivity {
    private TextView popUpTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protection_measures);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        popUpTxt = (TextView) findViewById(R.id.sun_protection_message);

        ImageView slip = (ImageView) findViewById(R.id.imageViewSlip);
        slip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpTxt.setText("Slip on Sun Protective clothing. Cover your skin.");
            }
        });

        ImageView slop = (ImageView) findViewById(R.id.imageViewSlop);
        slop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpTxt.setText("Slop on SPF 30+, broad-spectrum, water resistant sunscreen.");
            }
        });

        ImageView slap = (ImageView) findViewById(R.id.imageViewSlap);
        slap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpTxt.setText("Slap on a broad-brimmed hat that shades your face, ears and neck.");
            }
        });

        ImageView seek = (ImageView) findViewById(R.id.imageViewSeek);
        seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpTxt.setText("Seek Shade and take breaks from sun.");
            }
        });

        ImageView shade = (ImageView) findViewById(R.id.imageViewShade);
        shade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpTxt.setText("Slide on a pair of sunglasses that have a good UV protection");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
