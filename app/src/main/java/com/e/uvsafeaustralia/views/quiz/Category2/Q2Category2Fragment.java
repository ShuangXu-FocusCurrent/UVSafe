package com.e.uvsafeaustralia.views.quiz.Category2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.FragmentQ2Category2Binding;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;


public class Q2Category2Fragment extends Fragment {
    private FragmentQ2Category2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQ2Category2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewCategory2);
        binding.buttonCat2Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q2Category2Fragment_to_q1Category2Fragment);
            }
        });

        binding.buttonCat2Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q2Category2Fragment_to_q3Category2Fragment);
            }
        });

        binding.buttonCat2Q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q2Category2Fragment_to_q4Category2Fragment);
            }
        });

        binding.buttonCat2End.setOnClickListener(new View.OnClickListener() {
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