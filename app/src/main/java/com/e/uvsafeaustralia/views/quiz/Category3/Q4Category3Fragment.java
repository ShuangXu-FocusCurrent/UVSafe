package com.e.uvsafeaustralia.views.quiz.Category3;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.FragmentQ1Category3Binding;
import com.e.uvsafeaustralia.databinding.FragmentQ4Category3Binding;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

public class Q4Category3Fragment extends Fragment {
    private FragmentQ4Category3Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQ4Category3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewCategory);
        binding.buttonCat3Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category3Fragment_to_q1Category3Fragment);
            }
        });

        binding.buttonCat3Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category3Fragment_to_q2Category3Fragment);
            }
        });

        binding.buttonCat3Q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q4Category3Fragment_to_q3Category3Fragment);
            }
        });

        binding.buttonCat3End.setOnClickListener(new View.OnClickListener() {
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