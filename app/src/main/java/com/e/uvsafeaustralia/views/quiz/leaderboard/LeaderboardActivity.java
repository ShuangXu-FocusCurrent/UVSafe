package com.e.uvsafeaustralia.views.quiz.leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.uvsafeaustralia.databinding.ActivityLeaderboardBinding;
import com.e.uvsafeaustralia.databinding.LeaderboardRvLayoutBinding;
import com.e.uvsafeaustralia.helper.LeaderboardRvAdapter;
import com.e.uvsafeaustralia.models.LeaderboardModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;
import com.e.uvsafeaustralia.views.quiz.reportWithReview.ReportActivity;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private ActivityLeaderboardBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private LeaderboardRvAdapter adapter;
    private List<LeaderboardModel> models;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        models = new ArrayList<LeaderboardModel>();
        LeaderboardModel model = new LeaderboardModel("Jade","04/16","02/04","https://cdn.pixabay.com/photo/2013/07/12/18/29/trophy-153395_1280.png");
        models.add(model);
        adapter = new LeaderboardRvAdapter(models,LeaderboardActivity.this);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        binding.goReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LeaderboardActivity.this , ReportActivity.class);
                startActivity(intent);
            }
        });
    }



}