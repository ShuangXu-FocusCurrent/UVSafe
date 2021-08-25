package com.e.uvsafeaustralia.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.uvsafeaustralia.R;

import com.e.uvsafeaustralia.databinding.FragmentHomePageBinding;
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
        // Inflate the layout for this fragment

        binding = FragmentSunEduBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}