package com.e.uvsafeaustralia.views.functionsFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.uvsafeaustralia.views.AboutSunscreensActivity;
import com.e.uvsafeaustralia.views.AboutUVActivity;
import com.e.uvsafeaustralia.views.MelanomaRiskFactorsActivity;
import com.e.uvsafeaustralia.views.SunProtectionActivity;
import com.e.uvsafeaustralia.databinding.FragmentSunEduBinding;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class SunEduFragment extends Fragment {
    private FragmentSunEduBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSunEduBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.aboutUVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutUVActivity.class);
                startActivity(intent );
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });

        binding.sunProtectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SunProtectionActivity.class);
                startActivity(intent );
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });

        binding.aboutSunscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutSunscreensActivity.class);
                startActivity(intent );
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });

        binding.melanomaRisksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MelanomaRiskFactorsActivity.class);
                startActivity(intent );
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}