package com.e.uvsafeaustralia.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.OnelineReviewRvLayoutBinding;
import com.e.uvsafeaustralia.models.AnswerModel;

import java.util.List;

import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.NOT_SELECTED_BTN_COLOUR;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.SELECTED_BTN_COLOUR;

public class ReviewRvAdapter extends RecyclerView.Adapter
        <ReviewRvAdapter.ViewHolder> {

    List<AnswerModel> models;
    Context context;
    public ReviewRvAdapter(List<AnswerModel> models, Context context){
        this.models = models;
        this.context = context;
    }

    @Override
    public ReviewRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        OnelineReviewRvLayoutBinding binding = OnelineReviewRvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ReviewRvAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewRvAdapter.ViewHolder holder, int position) {
        AnswerModel model = models.get(position);

        holder.binding.quetionNumber.setText(String.valueOf(model.getQuestion().getqId()));
        holder.binding.question.setText(model.getQuestion().getQuestion());
        holder.binding.buttonAnswerOpt1.setText(model.getQuestion().getAnswerOption1());
        holder.binding.buttonAnswerOpt1.setEnabled(false);
        if (model.getQuestion().getCorrect().equals(model.getQuestion().getAnswerOption1()))
            holder.binding.buttonAnswerOpt1.setBackgroundColor(SELECTED_BTN_COLOUR);
        else
            holder.binding.buttonAnswerOpt1.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
        holder.binding.buttonAnswerOpt2.setText(model.getQuestion().getAnswerOption2());
        holder.binding.buttonAnswerOpt2.setEnabled(false);
        if (model.getQuestion().getCorrect().equals(model.getQuestion().getAnswerOption2()))
            holder.binding.buttonAnswerOpt2.setBackgroundColor(SELECTED_BTN_COLOUR);
        else
            holder.binding.buttonAnswerOpt2.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
        if (model.getQuestion().getAnswerOption3() == null)
            holder.binding.buttonAnswerOpt3.setVisibility(View.INVISIBLE);
        else {
            holder.binding.buttonAnswerOpt3.setText(model.getQuestion().getAnswerOption3());
            holder.binding.buttonAnswerOpt3.setEnabled(false);
            if (model.getQuestion().getCorrect().equals(model.getQuestion().getAnswerOption3()))
                holder.binding.buttonAnswerOpt3.setBackgroundColor(SELECTED_BTN_COLOUR);
            else
                holder.binding.buttonAnswerOpt3.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
        }
        if (model.getQuestion().getAnswerOption4() == null)
            holder.binding.buttonAnswerOpt4.setVisibility(View.INVISIBLE);
        else {
            holder.binding.buttonAnswerOpt4.setText(model.getQuestion().getAnswerOption4());
            holder.binding.buttonAnswerOpt4.setEnabled(false);
            if (model.getQuestion().getCorrect().equals(model.getQuestion().getAnswerOption4()))
                holder.binding.buttonAnswerOpt4.setBackgroundColor(SELECTED_BTN_COLOUR);
            else
                holder.binding.buttonAnswerOpt4.setBackgroundColor(NOT_SELECTED_BTN_COLOUR);
        }
        holder.binding.explain.setText(model.getQuestion().getAnswerExplain());
        if(model.getStatus()==1){
            holder.binding.status.setImageResource(R.drawable.right);
        }else{
            holder.binding.status.setImageResource(R.drawable.wrong);
        }
    }

    @Override
    public int getItemCount() {
        return models.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private OnelineReviewRvLayoutBinding binding;
        public ViewHolder(OnelineReviewRvLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
