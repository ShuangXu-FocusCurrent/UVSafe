package com.e.uvsafeaustralia.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityMelanomaRiskFactorsBinding;

public class MelanomaRiskFactorsActivity extends AppCompatActivity {
    private ActivityMelanomaRiskFactorsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMelanomaRiskFactorsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ImageView hex1 = (ImageView) findViewById(R.id.hex1);
        ImageView hex2 = (ImageView) findViewById(R.id.hex2);
        ImageView hex3 = (ImageView) findViewById(R.id.hex3);
        ImageView hex4 = (ImageView) findViewById(R.id.hex4);
        ImageView hex5 = (ImageView) findViewById(R.id.hex5);

        ImageView indicator1 = (ImageView) findViewById(R.id.indicator1);
        ImageView indicator2 = (ImageView) findViewById(R.id.indicator2);
        ImageView indicator3 = (ImageView) findViewById(R.id.indicator3);
        ImageView indicator4 = (ImageView) findViewById(R.id.indicator4);
        ImageView indicator5 = (ImageView) findViewById(R.id.indicator5);

        TextView sign_desc1 = (TextView) findViewById(R.id.sign_desc_1);
        TextView sign_desc2 = (TextView) findViewById(R.id.sign_desc_2);
        TextView sign_desc3 = (TextView) findViewById(R.id.sign_desc_3);
        TextView sign_desc4 = (TextView) findViewById(R.id.sign_desc_4);
        TextView sign_desc5 = (TextView) findViewById(R.id.sign_desc_5);

        TextView miaLink = (TextView) findViewById(R.id.textViewMIALink);
        TextView nswLink = (TextView) findViewById(R.id.textViewNSWLink);
        TextView vicLink = (TextView) findViewById(R.id.textViewVicLink);


        hex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_desc1.setVisibility(View.VISIBLE);
                sign_desc2.setVisibility(View.INVISIBLE);
                sign_desc3.setVisibility(View.INVISIBLE);
                sign_desc4.setVisibility(View.INVISIBLE);
                sign_desc5.setVisibility(View.INVISIBLE);

                indicator1.setVisibility(View.VISIBLE);
                indicator2.setVisibility(View.INVISIBLE);
                indicator3.setVisibility(View.INVISIBLE);
                indicator4.setVisibility(View.INVISIBLE);
                indicator5.setVisibility(View.INVISIBLE);
            }
        });

        hex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_desc1.setVisibility(View.INVISIBLE);
                sign_desc2.setVisibility(View.VISIBLE);
                sign_desc3.setVisibility(View.INVISIBLE);
                sign_desc4.setVisibility(View.INVISIBLE);
                sign_desc5.setVisibility(View.INVISIBLE);

                indicator1.setVisibility(View.INVISIBLE);
                indicator2.setVisibility(View.VISIBLE);
                indicator3.setVisibility(View.INVISIBLE);
                indicator4.setVisibility(View.INVISIBLE);
                indicator5.setVisibility(View.INVISIBLE);
            }
        });

        hex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_desc1.setVisibility(View.INVISIBLE);
                sign_desc2.setVisibility(View.INVISIBLE);
                sign_desc3.setVisibility(View.VISIBLE);
                sign_desc4.setVisibility(View.INVISIBLE);
                sign_desc5.setVisibility(View.INVISIBLE);

                indicator1.setVisibility(View.INVISIBLE);
                indicator2.setVisibility(View.INVISIBLE);
                indicator3.setVisibility(View.VISIBLE);
                indicator4.setVisibility(View.INVISIBLE);
                indicator5.setVisibility(View.INVISIBLE);
            }
        });

        hex4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_desc1.setVisibility(View.INVISIBLE);
                sign_desc2.setVisibility(View.INVISIBLE);
                sign_desc3.setVisibility(View.INVISIBLE);
                sign_desc4.setVisibility(View.VISIBLE);
                sign_desc5.setVisibility(View.INVISIBLE);

                indicator1.setVisibility(View.INVISIBLE);
                indicator2.setVisibility(View.INVISIBLE);
                indicator3.setVisibility(View.INVISIBLE);
                indicator4.setVisibility(View.VISIBLE);
                indicator5.setVisibility(View.INVISIBLE);
            }
        });

        hex5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_desc1.setVisibility(View.INVISIBLE);
                sign_desc2.setVisibility(View.INVISIBLE);
                sign_desc3.setVisibility(View.INVISIBLE);
                sign_desc4.setVisibility(View.INVISIBLE);
                sign_desc5.setVisibility(View.VISIBLE);

                indicator1.setVisibility(View.INVISIBLE);
                indicator2.setVisibility(View.INVISIBLE);
                indicator3.setVisibility(View.INVISIBLE);
                indicator4.setVisibility(View.INVISIBLE);
                indicator5.setVisibility(View.VISIBLE);
            }
        });

        miaLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.melanoma.org.au/understanding-melanoma/"));
                startActivity(browserIntent);
            }
        });

        nswLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cancercouncil.com.au/melanoma/about-melanoma"));
                startActivity(browserIntent);
            }
        });

        vicLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cancervic.org.au/cancer-information/types-of-cancer/melanoma/melanoma-overview.html"));
                startActivity(browserIntent);
            }
        });
    }
}