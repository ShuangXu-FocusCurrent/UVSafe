package com.e.uvsafeaustralia.views.quiz.Category1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityQuziCategory1Binding;

public class QuizCategory1Activity extends AppCompatActivity {
    private ActivityQuziCategory1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityQuziCategory1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerViewCategory1);

    }
}