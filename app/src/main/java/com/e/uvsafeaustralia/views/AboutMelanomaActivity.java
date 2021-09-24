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
    private MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutMelanomaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mediaController = new MediaController(this);
        Uri uri = Uri.parse("android.resource://com.e.uvsafeaustralia/"+ R.raw.aboutmelonoma2);
        binding.aboutMelanomaVideo2.setVideoURI(uri);
        binding.aboutMelanomaVideo2.setMediaController(mediaController);
        mediaController.setAnchorView(binding.aboutMelanomaVideo1);
    }
    public void playAboutMelanomaVideo1() {
        Uri uri = Uri.parse("android.resource://com.e.uvsafeaustralia/"+ R.raw.aboutsunscreen);
        binding.aboutMelanomaVideo1.setVideoURI(uri);
        binding.aboutMelanomaVideo1.setMediaController(mediaController);
        mediaController.setAnchorView(binding.aboutMelanomaVideo1);
        binding.aboutMelanomaVideo1.start();
    }
}