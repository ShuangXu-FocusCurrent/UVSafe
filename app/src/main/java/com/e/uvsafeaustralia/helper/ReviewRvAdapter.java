package com.e.uvsafeaustralia.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.OnelineReviewRvLayoutBinding;
import com.e.uvsafeaustralia.models.AnswerModel;

import java.util.List;

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
        final AnswerModel model = models.get(position);

        holder.binding.quetionNumber.setText(String.valueOf(model.getQuestion().getqId()));
        holder.binding.question.setText(model.getQuestion().getQuestion());
        holder.binding.option1.setText(model.getQuestion().getAnswerOption1());
        holder.binding.option2.setText(model.getQuestion().getAnswerOption2());
        holder.binding.option3.setText(model.getQuestion().getAnswerOption3());
        holder.binding.option4.setText(model.getQuestion().getAnswerOption4());
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
