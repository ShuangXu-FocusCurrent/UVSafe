package com.e.uvsafeaustralia;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ProtectionActivity extends AppCompatActivity {
    private ImageView slip;
    private ImageView slop;
    private ImageView slap;
    private ImageView seek;
    private ImageView shade;
    private ImageView slipTxtImgView;
    private ImageView slopTxtImgView;
    private ImageView slapTxtImgView;
    private ImageView seekTxtImgView;
    private ImageView shadeTxtImgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protection_measures);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        slipTxtImgView = (ImageView) findViewById(R.id.imageViewMessageSlip);
        slopTxtImgView = (ImageView) findViewById(R.id.imageViewMessageSlop);
        slapTxtImgView = (ImageView) findViewById(R.id.imageViewMessageSlap);
        seekTxtImgView = (ImageView) findViewById(R.id.imageViewMessageSeek);
        shadeTxtImgView = (ImageView) findViewById(R.id.imageViewMessageShade);

        slipTxtImgView.setVisibility(View.INVISIBLE);
        slopTxtImgView.setVisibility(View.INVISIBLE);
        slapTxtImgView.setVisibility(View.INVISIBLE);
        seekTxtImgView.setVisibility(View.INVISIBLE);
        shadeTxtImgView.setVisibility(View.INVISIBLE);

        slip = (ImageView) findViewById(R.id.imageViewSlip);
        slip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtImgView.setVisibility(v.VISIBLE);
                slopTxtImgView.setVisibility(v.INVISIBLE);
                slapTxtImgView.setVisibility(v.INVISIBLE);
                seekTxtImgView.setVisibility(v.INVISIBLE);
                shadeTxtImgView.setVisibility(v.INVISIBLE);
            }
        });

        slop = (ImageView) findViewById(R.id.imageViewSlop);
        slop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtImgView.setVisibility(v.INVISIBLE);
                slopTxtImgView.setVisibility(v.VISIBLE);
                slapTxtImgView.setVisibility(v.INVISIBLE);
                seekTxtImgView.setVisibility(v.INVISIBLE);
                shadeTxtImgView.setVisibility(v.INVISIBLE);
            }
        });

        slap = (ImageView) findViewById(R.id.imageViewSlap);
        slap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtImgView.setVisibility(v.INVISIBLE);
                slopTxtImgView.setVisibility(v.INVISIBLE);
                slapTxtImgView.setVisibility(v.VISIBLE);
                seekTxtImgView.setVisibility(v.INVISIBLE);
                shadeTxtImgView.setVisibility(v.INVISIBLE);
            }
        });

        seek = (ImageView) findViewById(R.id.imageViewSeek);
        seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtImgView.setVisibility(v.INVISIBLE);
                slopTxtImgView.setVisibility(v.INVISIBLE);
                slapTxtImgView.setVisibility(v.INVISIBLE);
                seekTxtImgView.setVisibility(v.VISIBLE);
                shadeTxtImgView.setVisibility(v.INVISIBLE);
            }
        });

        shade = (ImageView) findViewById(R.id.imageViewShade);
        shade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtImgView.setVisibility(v.INVISIBLE);
                slopTxtImgView.setVisibility(v.INVISIBLE);
                slapTxtImgView.setVisibility(v.INVISIBLE);
                seekTxtImgView.setVisibility(v.INVISIBLE);
                shadeTxtImgView.setVisibility(v.VISIBLE);
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
