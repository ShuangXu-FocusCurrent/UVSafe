package com.e.uvsafeaustralia.views.quiz.Category4;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.FragmentQ1Category4Binding;
import com.e.uvsafeaustralia.databinding.FragmentQ3Category4Binding;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;


public class Q3Category4Fragment extends Fragment {
    private FragmentQ3Category4Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQ3Category4Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewCategory);
        binding.buttonCat4Q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q3Category4Fragment_to_q1Category4Fragment);
            }
        });

        binding.buttonCat4Q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q3Category4Fragment_to_q2Category4Fragment);
            }
        });

        binding.buttonCat4Q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_q3Category4Fragment_to_q4Category4Fragment);
            }
        });

        binding.buttonCat4End.setOnClickListener(new View.OnClickListener() {
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