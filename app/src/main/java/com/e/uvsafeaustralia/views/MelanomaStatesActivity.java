package com.e.uvsafeaustralia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityMelanomaStatesBinding;
import com.e.uvsafeaustralia.databinding.ActivityQuizFourBlocksBinding;

public class MelanomaStatesActivity extends AppCompatActivity {
    private ActivityMelanomaStatesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMelanomaStatesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.melanomaOrgLinkId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.melanoma.org.au/understanding-melanoma/"));
                startActivity(browserIntent);
            }
        });

        binding.cancerCouncilLinkId1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cancercouncil.com.au/melanoma/about-melanoma"));
                startActivity(browserIntent);
            }
        });

        binding.cancerCouncilLinkId2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cancervic.org.au/cancer-information/types-of-cancer/melanoma/melanoma-overview.html"));
                startActivity(browserIntent);
            }
        });
    }
}