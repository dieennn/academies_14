package com.dicoding.intifada.submission1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicoding.intifada.submission1.Model.Film;
import com.dicoding.intifada.submission1.R;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity{
    public static final String EXTRA_FILMS = "extra_film";
    TextView tvTitle, tvRelease, tvVote, tvRuntime, tvBudget, tvRevenue, tvDescription;
    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgPhoto = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        tvRelease = findViewById(R.id.tv_release);
        tvVote = findViewById(R.id.tv_vote);
        tvRuntime = findViewById(R.id.tv_runtime);
        tvBudget = findViewById(R.id.tv_budget);
        tvRevenue = findViewById(R.id.tv_revenue);
        tvDescription = findViewById(R.id.tv_description);

        final ArrayList<Film> films = getIntent().getParcelableArrayListExtra(EXTRA_FILMS);

        TextView title = findViewById(R.id.title);
        title.setText(films.get(0).getName());

        imgPhoto.setImageResource(films.get(0).getPhoto());
        String name = "Judul : "+films.get(0).getName();
        tvTitle.setText(name);
        String release ="Release : "+films.get(0).getRelease();
        tvRelease.setText(release);
        String vote = "Vote : "+films.get(0).getVote()+" %";
        tvVote.setText(vote);
        String runtime = "Runtime : "+films.get(0).getRuntime();
        tvRuntime.setText(runtime);
        String budget = "Budget : "+films.get(0).getBudget();
        tvBudget.setText(budget);
        String revenue = "Revenue : "+films.get(0).getRevenue();
        tvRevenue.setText(revenue);
        String description = "Deskripsi : "+ films.get(0).getDescription();
        tvDescription.setText(description);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
