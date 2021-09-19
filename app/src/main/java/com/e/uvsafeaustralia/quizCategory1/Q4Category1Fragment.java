package com.e.uvsafeaustralia.quizCategory1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.FragmentQ4Category1Binding;


public class Q4Category1Fragment extends Fragment {
    private FragmentQ4Category1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQ4Category1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewCategory1);
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category1Fragment_to_q1Category1Fragment);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category1Fragment_to_q2Category1Fragment);
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category1Fragment_to_q3Category1Fragment);
            }
        });
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}