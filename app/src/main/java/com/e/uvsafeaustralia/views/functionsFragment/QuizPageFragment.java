package com.e.uvsafeaustralia.views.functionsFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.uvsafeaustralia.databinding.FragmentQuizPageBinding;
import com.e.uvsafeaustralia.views.MainActivity;
import com.e.uvsafeaustralia.views.SlideActivity;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class QuizPageFragment extends Fragment {
    private FragmentQuizPageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( requireActivity() , QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}