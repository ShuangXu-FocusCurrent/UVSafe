package com.e.uvsafeaustralia.views;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityAboutSunscreensBinding;

public class AboutSunscreensActivity extends AppCompatActivity {
    private ActivityAboutSunscreensBinding binding;
    private VideoView aboutSunscreenVid;
    private Button aboutSunscreenVidBtn;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityAboutSunscreensBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        aboutSunscreenVid = (VideoView) findViewById(R.id.about_sunscreen_video_view);
        aboutSunscreenVidBtn = (Button) findViewById(R.id.buttonAboutSunscreenVid);
        mediaController = new MediaController(this);

        aboutSunscreenVidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAboutSunscreenVid();
            }
        });

        binding.buttonMyth1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myth.setImageResource(R.drawable.gif_myth1);
            }
        });

        binding.buttonMyth2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myth.setImageResource(R.drawable.gif_myth2);
            }
        });
        binding.buttonMyth3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myth.setImageResource(R.drawable.gif_myth3);
            }
        });
        binding.buttonMyth4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myth.setImageResource(R.drawable.gif_myth4);
            }
        });
        binding.buttonMyth5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myth.setImageResource(R.drawable.gif_myth5);
            }
        });
        binding.buttonMyth6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myth.setImageResource(R.drawable.gif_myth6);
            }
        });
    }

    public void playAboutSunscreenVid() {
        Uri uri = Uri.parse("android.resource://com.e.uvsafeaustralia/"+R.raw.aboutsunscreen);
        aboutSunscreenVid.setVideoURI(uri);
        aboutSunscreenVid.setMediaController(mediaController);
        mediaController.setAnchorView(aboutSunscreenVid);
        aboutSunscreenVid.start();
    }
}
