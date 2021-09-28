package com.e.uvsafeaustralia.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.LeaderboardRvLayoutBinding;
import com.e.uvsafeaustralia.models.LeaderboardModel;

import java.util.List;

import static com.e.uvsafeaustralia.views.quiz.leaderboard.LeaderboardActivity.FIRST_PLACE;
import static com.e.uvsafeaustralia.views.quiz.leaderboard.LeaderboardActivity.RUNNER_UP;
import static com.e.uvsafeaustralia.views.quiz.leaderboard.LeaderboardActivity.SECOND_PLACE;
import static com.e.uvsafeaustralia.views.quiz.leaderboard.LeaderboardActivity.THIRD_PLACE;

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
        LeaderboardModel model = models.get(position);
        holder.binding.name.setText(model.getName());
        holder.binding.attemptedNumber.setText(model.getAttemptedNumber());
        holder.binding.correctAnswerNumber.setText(model.getCorrectAnswerNumber());
        if (model.getTrophy().equals(FIRST_PLACE))
            holder.binding.trophy.setImageResource(R.drawable.gold);
        if (model.getTrophy().equals(SECOND_PLACE))
            holder.binding.trophy.setImageResource(R.drawable.silver);
        if (model.getTrophy().equals(THIRD_PLACE))
            holder.binding.trophy.setImageResource(R.drawable.bronze);
        if (model.getTrophy().equals(RUNNER_UP))
            holder.binding.trophy.setImageResource(R.drawable.waiting_img);
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

    public int getImage(String imageName) {

        int drawableResourceId = this.context.getResources().getIdentifier(imageName, "drawable", this.context.getPackageName());

        return drawableResourceId;
    }
}
