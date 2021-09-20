package com.e.uvsafeaustralia.views.quiz.Category2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityQuizCategory2Binding;
import com.e.uvsafeaustralia.databinding.ActivityQuziCategory1Binding;


public class QuizCategory2Activity extends AppCompatActivity {
    private ActivityQuizCategory2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityQuizCategory2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerViewCategory);
    }
}