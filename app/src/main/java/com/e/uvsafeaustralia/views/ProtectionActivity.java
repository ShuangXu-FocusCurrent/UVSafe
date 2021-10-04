package com.e.uvsafeaustralia.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.uvsafeaustralia.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ProtectionActivity extends AppCompatActivity {
    private ImageView slip;
    private ImageView slop;
    private ImageView slap;
    private ImageView seek;
    private ImageView shade;
    private ImageView slip_indicator;
    private ImageView slop_indicator;
    private ImageView slap_indicator;
    private ImageView seek_indicator;
    private ImageView shade_indicator;
    private ImageView instruct_bcg;
    private TextView slipTxtView;
    private TextView slopTxtView;
    private TextView slapTxtView;
    private TextView seekTxtView;
    private TextView shadeTxtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protection_measures);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        slipTxtView = (TextView) findViewById(R.id.textSlip);
        slopTxtView = (TextView) findViewById(R.id.textSlop);
        slapTxtView = (TextView) findViewById(R.id.textSlap);
        seekTxtView = (TextView) findViewById(R.id.textSeek);
        shadeTxtView = (TextView) findViewById(R.id.textShade);

        instruct_bcg = (ImageView) findViewById(R.id.instruct_bcg);
        slip_indicator = (ImageView) findViewById(R.id.slip_indicator);
        slop_indicator = (ImageView) findViewById(R.id.slop_indicator);
        slap_indicator = (ImageView) findViewById(R.id.slap_indicator);
        seek_indicator = (ImageView) findViewById(R.id.seek_indicator);
        shade_indicator = (ImageView) findViewById(R.id.shade_indicator);

        instruct_bcg.setVisibility(View.VISIBLE);

        slipTxtView.setVisibility(View.VISIBLE);
        slip_indicator.setVisibility(View.VISIBLE);
        slopTxtView.setVisibility(View.INVISIBLE);
        slop_indicator.setVisibility(View.INVISIBLE);
        slapTxtView.setVisibility(View.INVISIBLE);
        slap_indicator.setVisibility(View.INVISIBLE);
        seekTxtView.setVisibility(View.INVISIBLE);
        seek_indicator.setVisibility(View.INVISIBLE);
        shadeTxtView.setVisibility(View.INVISIBLE);
        shade_indicator.setVisibility(View.INVISIBLE);

        slip = (ImageView) findViewById(R.id.imageViewSlip);
        slip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtView.setVisibility(v.VISIBLE);
                slip_indicator.setVisibility(View.VISIBLE);
                slopTxtView.setVisibility(View.INVISIBLE);
                slop_indicator.setVisibility(View.INVISIBLE);
                slapTxtView.setVisibility(View.INVISIBLE);
                slap_indicator.setVisibility(View.INVISIBLE);
                seekTxtView.setVisibility(View.INVISIBLE);
                seek_indicator.setVisibility(View.INVISIBLE);
                shadeTxtView.setVisibility(View.INVISIBLE);
                shade_indicator.setVisibility(View.INVISIBLE);
            }
        });

        slop = (ImageView) findViewById(R.id.imageViewSlop);
        slop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtView.setVisibility(v.INVISIBLE);
                slip_indicator.setVisibility(View.INVISIBLE);
                slopTxtView.setVisibility(v.VISIBLE);
                slop_indicator.setVisibility(View.VISIBLE);
                slapTxtView.setVisibility(View.INVISIBLE);
                slap_indicator.setVisibility(View.INVISIBLE);
                seekTxtView.setVisibility(View.INVISIBLE);
                seek_indicator.setVisibility(View.INVISIBLE);
                shadeTxtView.setVisibility(View.INVISIBLE);
                shade_indicator.setVisibility(View.INVISIBLE);
            }
        });

        slap = (ImageView) findViewById(R.id.imageViewSlap);
        slap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtView.setVisibility(v.INVISIBLE);
                slip_indicator.setVisibility(View.INVISIBLE);
                slopTxtView.setVisibility(View.INVISIBLE);
                slop_indicator.setVisibility(View.INVISIBLE);
                slapTxtView.setVisibility(v.VISIBLE);
                slap_indicator.setVisibility(View.VISIBLE);
                seekTxtView.setVisibility(View.INVISIBLE);
                seek_indicator.setVisibility(View.INVISIBLE);
                shadeTxtView.setVisibility(View.INVISIBLE);
                shade_indicator.setVisibility(View.INVISIBLE);
            }
        });

        seek = (ImageView) findViewById(R.id.imageViewSeek);
        seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtView.setVisibility(v.INVISIBLE);
                slip_indicator.setVisibility(View.INVISIBLE);
                slopTxtView.setVisibility(View.INVISIBLE);
                slop_indicator.setVisibility(View.INVISIBLE);
                slapTxtView.setVisibility(View.INVISIBLE);
                slap_indicator.setVisibility(View.INVISIBLE);
                seekTxtView.setVisibility(View.VISIBLE);
                seek_indicator.setVisibility(View.VISIBLE);
                shadeTxtView.setVisibility(View.INVISIBLE);
                shade_indicator.setVisibility(View.INVISIBLE);
            }
        });

        shade = (ImageView) findViewById(R.id.imageViewShade);
        shade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slipTxtView.setVisibility(v.INVISIBLE);
                slip_indicator.setVisibility(View.INVISIBLE);
                slopTxtView.setVisibility(View.INVISIBLE);
                slop_indicator.setVisibility(View.INVISIBLE);
                slapTxtView.setVisibility(View.INVISIBLE);
                slap_indicator.setVisibility(View.INVISIBLE);
                seekTxtView.setVisibility(View.INVISIBLE);
                seek_indicator.setVisibility(View.INVISIBLE);
                shadeTxtView.setVisibility(View.VISIBLE);
                shade_indicator.setVisibility(View.VISIBLE);
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
