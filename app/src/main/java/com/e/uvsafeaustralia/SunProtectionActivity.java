package com.e.uvsafeaustralia;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class SunProtectionActivity extends AppCompatActivity {
    private VideoView sunProtectionVid;
    private Button sunProtectionVidBtn;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun_protection);

        sunProtectionVid = (VideoView) findViewById(R.id.edu_sun_main_video_view);
        sunProtectionVidBtn = (Button) findViewById(R.id.buttonSunProtectionVid);
        mediaController = new MediaController(this);

        sunProtectionVidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSunProtectionVid();
            }
        });
    }

    public void playSunProtectionVid() {
        Uri uri = Uri.parse("android.resource://com.e.uvsafeaustralia/"+R.raw.sunprotection);
        sunProtectionVid.setVideoURI(uri);
        sunProtectionVid.setMediaController(mediaController);
        mediaController.setAnchorView(sunProtectionVid);
        sunProtectionVid.start();
    }
}
