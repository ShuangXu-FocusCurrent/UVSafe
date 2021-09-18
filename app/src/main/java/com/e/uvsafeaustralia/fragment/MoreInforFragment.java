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

        QuestionModel.enumQCategory qCategory= QuestionModel.enumQCategory.CATEGORY1;
        UserModel userModel=new UserModel(1,"Jack");
        QuestionModel questionModel = new QuestionModel(1,qCategory,1,"question","answerOption1","answerOption2","answerOption3","answerOption4","correct","answerExplain");
        String output = userModel.toString() +"/n"+" "+questionModel.toString();
        //binding.output.setText(output);
        Log.i("Output value:",output);
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}