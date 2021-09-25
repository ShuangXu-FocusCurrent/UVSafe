package com.e.uvsafeaustralia.views.quiz.Category1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.FragmentQ4Category1Binding;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;


public class Q4Category1Fragment extends Fragment {
    private FragmentQ4Category1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQ4Category1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewCategory1);
        binding.buttonCat1Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category1Fragment_to_q1Category1Fragment);
            }
        });

        binding.buttonCat1Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category1Fragment_to_q2Category1Fragment);
            }
        });

        binding.buttonCat1Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category1Fragment_to_q3Category1Fragment);
            }
        });

        binding.buttonCat1End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( requireActivity(), QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}