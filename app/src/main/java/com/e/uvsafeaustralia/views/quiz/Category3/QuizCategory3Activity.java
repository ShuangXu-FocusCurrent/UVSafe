package com.e.uvsafeaustralia.views.quiz.Category3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityQuizCategory3Binding;

public class QuizCategory3Activity extends AppCompatActivity {
    private ActivityQuizCategory3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityQuizCategory3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerViewCategory4);
    }
}