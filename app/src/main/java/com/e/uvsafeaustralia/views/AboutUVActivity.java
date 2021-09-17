package com.e.uvsafeaustralia.views;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.e.uvsafeaustralia.R;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUVActivity extends AppCompatActivity {
    private VideoView aboutUVVid;
    private Button aboutUVVidBtn;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_uv);

        aboutUVVid = (VideoView) findViewById(R.id.about_uv_video_view);
        aboutUVVidBtn = (Button) findViewById(R.id.buttonAboutUVVideo);
        mediaController = new MediaController(this);

        aboutUVVidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAboutUVVid();
            }
        });
    }

    public void playAboutUVVid() {
        Uri uri = Uri.parse("android.resource://com.e.uvsafeaustralia/"+R.raw.aboutuv);
        aboutUVVid.setVideoURI(uri);
        aboutUVVid.setMediaController(mediaController);
        mediaController.setAnchorView(aboutUVVid);
        aboutUVVid.start();
    }
}
