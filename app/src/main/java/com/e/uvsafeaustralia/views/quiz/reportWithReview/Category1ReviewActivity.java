package com.e.uvsafeaustralia.views.quiz.reportWithReview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.e.uvsafeaustralia.databinding.ActivityCategory1ReviewBinding;
import com.e.uvsafeaustralia.db.DBManager;
import com.e.uvsafeaustralia.helper.ReviewRvAdapter;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;
import com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity;

import java.util.ArrayList;
import java.util.List;

import static com.e.uvsafeaustralia.models.QuestionModel.EnumQCategory.CATEGORY1;
import static com.e.uvsafeaustralia.views.quiz.QuizFourBlocksActivity.userAnswersCategory1;

public class Category1ReviewActivity extends AppCompatActivity {
    private ActivityCategory1ReviewBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private ReviewRvAdapter adapter;
    private List<AnswerModel> models;
    protected DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategory1ReviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dbManager = new DBManager(this);

        Bundle bundle = new Bundle();
        models = new ArrayList<>();
//        models = bundle.getParcelableArrayList("userAnswersCategory1");
        models = userAnswersCategory1;
        adapter = new ReviewRvAdapter(models, Category1ReviewActivity.this);
        binding.reviewRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.reviewRecyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        binding.reviewRecyclerView.setLayoutManager(layoutManager);

        binding.backMainQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Category1ReviewActivity.this , QuizFourBlocksActivity.class);
                startActivity(intent);
            }
        });
    }
}