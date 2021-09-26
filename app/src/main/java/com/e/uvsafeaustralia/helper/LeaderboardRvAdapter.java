package com.e.uvsafeaustralia.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.LeaderboardRvLayoutBinding;
import com.e.uvsafeaustralia.models.LeaderboardModel;

import java.util.List;

public class LeaderboardRvAdapter extends RecyclerView.Adapter<LeaderboardRvAdapter.ViewHolder>{
    List<LeaderboardModel> models;
    Context context;

    public LeaderboardRvAdapter(List<LeaderboardModel> models,Context context){
        this.models = models;
        this.context = context;
    }

    @Override
    public LeaderboardRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LeaderboardRvLayoutBinding binding = LeaderboardRvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardRvAdapter.ViewHolder holder, int position) {
        final LeaderboardModel model = models.get(position);
        holder.binding.name.setText(model.getName());
        holder.binding.attemptedNumber.setText(model.getAttemptedNumber());
        holder.binding.correctAnswerNumber.setText(model.getCorrectAnswerNumber());
        Glide.with(this.context).load(model.getTrophy()).into(holder.binding.trophy);
    }

    @Override
    public int getItemCount() {
        return models.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private LeaderboardRvLayoutBinding binding;
        public ViewHolder(LeaderboardRvLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
