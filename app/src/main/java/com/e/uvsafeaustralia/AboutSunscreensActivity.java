package com.e.uvsafeaustralia;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutSunscreensActivity extends AppCompatActivity {
    private VideoView aboutSunscreenVid;
    private Button aboutSunscreenVidBtn;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sunscreens);

        aboutSunscreenVid = (VideoView) findViewById(R.id.about_sunscreen_video_view);
        aboutSunscreenVidBtn = (Button) findViewById(R.id.buttonAboutSunscreenVid);
        mediaController = new MediaController(this);

        aboutSunscreenVidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAboutSunscreenVid();
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
