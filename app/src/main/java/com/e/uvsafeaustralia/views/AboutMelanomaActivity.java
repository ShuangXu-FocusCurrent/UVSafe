package com.e.uvsafeaustralia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityAboutMelanomaBinding;
import com.e.uvsafeaustralia.databinding.ActivityQuizFourBlocksBinding;

public class AboutMelanomaActivity extends AppCompatActivity {

    private ActivityAboutMelanomaBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutMelanomaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.playvideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAboutMelanomaVideo1();
            }
        });

        binding.playvideo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAboutMelanomaVideo2();
            }
        });

    }
    public void playAboutMelanomaVideo1() {
        Uri uri = Uri.parse("android.resource://com.e.uvsafeaustralia/"+ R.raw.aboutmelanoma1);
        binding.aboutMelanomaVideo1.setVideoURI(uri);
        MediaController mediaController1 = new MediaController(this);
        binding.aboutMelanomaVideo1.setMediaController(mediaController1);
        mediaController1.setAnchorView(binding.aboutMelanomaVideo1);
        binding.aboutMelanomaVideo1.start();
    }

    public void playAboutMelanomaVideo2() {
        Uri uri = Uri.parse("android.resource://com.e.uvsafeaustralia/"+ R.raw.aboutmelonoma2);
        binding.aboutMelanomaVideo2.setVideoURI(uri);
        MediaController mediaController2 = new MediaController(this);
        binding.aboutMelanomaVideo2.setMediaController(mediaController2);
        mediaController2.setAnchorView(binding.aboutMelanomaVideo2);
        binding.aboutMelanomaVideo2.start();
    }
}