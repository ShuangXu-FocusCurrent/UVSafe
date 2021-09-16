package com.e.uvsafeaustralia.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.FragmentMoreInforBinding;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;


public class MoreInforFragment extends Fragment {
    private FragmentMoreInforBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoreInforBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId  = Integer.valueOf(binding.userId.getText().toString());
                String nickname = binding.nickname.getText().toString();
                int queationId = Integer.valueOf(binding.questionId.getText().toString());
                String question = binding.question.getText().toString();
                String answerOption1=binding.answer1.getText().toString();
                String answerOption2=binding.answer2.getText().toString();
                String answerOption3=binding.answer3.getText().toString();
                String answerOption4=binding.answer4.getText().toString();
                String correct=binding.correct.getText().toString();
                String answerExplain=binding.answerExplain.getText().toString();
                //String a = binding.qCategory.getText().toString();
                QuestionModel.enumQCategory qCategory= QuestionModel.enumQCategory.CATEGORY1;

                UserModel userModel=new UserModel(userId,nickname);
                QuestionModel questionModel = new QuestionModel(queationId,qCategory,question,answerOption1,answerOption2,answerOption3,answerOption4,correct,answerExplain);
                String output = userModel.toString() +"/n"+" "+questionModel.toString();
                //binding.output.setText(output);
                Log.i("Output value:",output);
            }
        });


        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}