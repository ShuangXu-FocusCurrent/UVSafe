package com.e.uvsafeaustralia.views;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityAboutUvBinding;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUVActivity extends AppCompatActivity {
    private VideoView aboutUVVid;
    private Button aboutUVVidBtn;
    private MediaController mediaController;
    private ActivityAboutUvBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutUvBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        aboutUVVid = (VideoView) findViewById(R.id.about_uv_video_view);
        aboutUVVidBtn = (Button) findViewById(R.id.buttonAboutUVVideo);
        mediaController = new MediaController(this);

        aboutUVVidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAboutUVVid();
            }
        });

        binding.perthMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_perth);
            }
        });

        binding.adelaideMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_adelaide);
            }
        });

        binding.perthMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_perth);
            }
        });

        binding.darwinMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_darwin);
            }
        });

        binding.actMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_);
            }
        });

        binding.sydenyMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_sydney);
            }
        });

        binding.melbourneMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_melbourne);
            }
        });

        binding.melbourneMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_melbourne);
            }
        });

        binding.hobartMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_);
            }
        });

        binding.brisbaneMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.uvDetailsCapital.setImageResource(R.drawable.uv_index_brisbane);
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
