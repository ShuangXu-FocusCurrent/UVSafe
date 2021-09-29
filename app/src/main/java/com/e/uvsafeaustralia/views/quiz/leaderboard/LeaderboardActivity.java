package com.e.uvsafeaustralia.views.quiz.leaderboard;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.uvsafeaustralia.R;
import com.e.uvsafeaustralia.databinding.ActivityLeaderboardBinding;
import com.e.uvsafeaustralia.helper.LeaderboardRvAdapter;
import com.e.uvsafeaustralia.models.AnswerModel;
import com.e.uvsafeaustralia.models.LeaderboardModel;
import com.e.uvsafeaustralia.models.QuestionModel;
import com.e.uvsafeaustralia.models.UserModel;
import com.e.uvsafeaustralia.views.quiz.reportWithReview.ReportActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.e.uvsafeaustralia.views.functionsFragment.QuizPageFragment.player;
import static com.e.uvsafeaustralia.views.functionsFragment.QuizPageFragment.userList;

public class LeaderboardActivity extends AppCompatActivity {
    private ActivityLeaderboardBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private LeaderboardRvAdapter adapter;
    private List<LeaderboardModel> models;
    private ArrayList<AnswerModel> allUsersAnswers;
    private ArrayList<QuestionModel> questionsList;
    public static final String FIRST_PLACE = "gold_med.png";
    public static final String SECOND_PLACE = "silver_med.png";
    public static final String THIRD_PLACE = "bronze_med.png";
    public static final String RUNNER_UP = "waiting_img.png";
    MediaPlayer bcg_music;
    public static Map<String, Integer> mapCorrect;
    public static Map<String, Integer> mapAttempt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        bcg_music = MediaPlayer.create(getApplicationContext(), R.raw.lb_bcg_music);
        bcg_music.start();

        Bundle bundle = getIntent().getExtras();
        allUsersAnswers = bundle.getParcelableArrayList("allUsersAnswers");
        questionsList = bundle.getParcelableArrayList("questionsList");

        // create initial leaderboard list without any ranking
        models = new ArrayList<LeaderboardModel>();
        mapCorrect = new HashMap<>();
        mapAttempt = new HashMap<>();
        String name = "";
        for (UserModel user : userList) {
            int attempted = 0;
            int correct = 0;
            name = user.getNickName();
            for (AnswerModel answer : allUsersAnswers) {
                if (answer.getUser().getUserId() == user.getUserId()) {
                    attempted++;
                    if (answer.getStatus() == 1)
                        correct++;
                }
            }
            mapCorrect.put(name, correct);
            mapAttempt.put(name, attempted);
        }
        // sort the users according to the one with most correct answers
        List<Map.Entry<String, Integer>> correctList = new ArrayList<>(mapCorrect.entrySet());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            correctList.sort(Map.Entry.comparingByValue());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            correctList.forEach(System.out::println);
        }
        // get unique correct numbers in the leaderboard
        List<Integer> uniqueCorrect = new ArrayList<>();
        for (Map.Entry<String, Integer> itemOuter : correctList)
            for (Map.Entry<String, Integer> itemInner : correctList)
                if (itemOuter.getValue() >= itemInner.getValue()) {
                    if (uniqueCorrect.isEmpty())
                        uniqueCorrect.add(itemOuter.getValue());
                    else {
                        if (!uniqueCorrect.contains(itemOuter.getValue()))
                            uniqueCorrect.add(itemOuter.getValue());
                    }
                }
        // rank users and show total correct and total attempt
        AssetManager assetManager = getApplicationContext().getAssets();
        for (UserModel user : userList) {
            name = user.getNickName();
            for (Map.Entry<String, Integer> item : correctList) {
                for (String attempt : mapAttempt.keySet()) {
                    if (user.equals(player) && mapAttempt.get(attempt) == 0) {
                        binding.goReport.setEnabled(false);
                        binding.goReport.setAlpha(0.25f);
                    }
                    if (mapAttempt.get(attempt) != 0) {
                        if (item.getKey().equals(user.getNickName()) && attempt.equals(user.getNickName())) {
                            StringBuilder sAttemptedNumber = new StringBuilder();
                            sAttemptedNumber.append(mapAttempt.get(attempt)).append("/").append(questionsList.size());
                            StringBuilder sCorrectNumber = new StringBuilder();
                            sCorrectNumber.append(item.getValue()).append("/").append(mapAttempt.get(attempt));
                            System.out.println(user.getNickName() + ", Total correct: " + item.getValue() + ", Total attempt: " + mapAttempt.get(attempt));
                            int level = uniqueCorrect.size();
                            // if there are more then 3 levels, the players with level above 3 will be runner ups
                            if (level > 3 && item.getValue() != 0) {
                                for (Integer i : uniqueCorrect) {
                                    // runner up
                                    if (i == item.getValue() && level == 1) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), FIRST_PLACE);
                                        models.add(model);
                                    }
                                    if (i == item.getValue() && level == 2) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), SECOND_PLACE);
                                        models.add(model);
                                    }
                                    if (i == item.getValue() && level == 3) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), THIRD_PLACE);
                                        models.add(model);
                                    }
                                    if (i == item.getValue() && level > 3) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), RUNNER_UP);
                                        models.add(model);
                                    }
                                    level--;
                                }
                            }
                            // if only 3 levels, everyone get a trophy
                            if (level == 3 && item.getValue() != 0) {
                                for (Integer i : uniqueCorrect) {
                                    if (i == item.getValue() && level == 1) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), FIRST_PLACE);
                                        models.add(model);
                                    }
                                    if (i == item.getValue() && level == 2) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), SECOND_PLACE);
                                        models.add(model);
                                    }
                                    if (i == item.getValue() && level == 3) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), THIRD_PLACE);
                                        models.add(model);
                                    }
                                    level--;
                                }
                            }
                            // if only 2 levels, only Gold and Bronze trophies are given
                            if (level == 2 && item.getValue() != 0) {
                                for (Integer i : uniqueCorrect) {
                                    if (i == item.getValue() && level == 1) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), FIRST_PLACE);
                                        models.add(model);
                                    }
                                    if (i == item.getValue() && level == 2) {
                                        LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), SECOND_PLACE);
                                        models.add(model);
                                    }
                                    level--;
                                }
                            }
                            // if only one level, only Gold trophy is given
                            if (level == 1 && item.getValue() != 0) {
                                LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), FIRST_PLACE);
                                models.add(model);
                            }
                            // if user has no correct answer
                            if (item.getValue() == 0) {
                                LeaderboardModel model = new LeaderboardModel(name, sAttemptedNumber.toString(), sCorrectNumber.toString(), RUNNER_UP);
                                models.add(model);
                            }
                        }
                    }
                }
            }
        }
        // sort rank by trophy
        ArrayList<LeaderboardModel> sortedModels = new ArrayList<>();
        String[] ranks = {FIRST_PLACE, SECOND_PLACE, THIRD_PLACE, RUNNER_UP};
        for (String rank : ranks)
        for (LeaderboardModel model : models)
            if (model.getTrophy().equals(rank))
                sortedModels.add(model);

        // set adapter
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new LeaderboardRvAdapter(sortedModels,LeaderboardActivity.this);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);

        binding.goReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LeaderboardActivity.this , ReportActivity.class);
                startActivity(intent);
                bcg_music.release();
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaderboardActivity.this, ClearLeaderboardActivity.class);
                startActivity(intent);
                bcg_music.release();
            }
        });

    }
}