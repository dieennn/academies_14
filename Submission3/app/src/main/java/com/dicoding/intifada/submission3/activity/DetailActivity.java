package com.dicoding.intifada.submission3.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dicoding.intifada.submission3.R;
import com.dicoding.intifada.submission3.model.Movie;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle, tvOverview, tvRelease, tvRate;
    ImageView ivPoster, ivBackdrop;
    public static final String EXTRA_DATA = "extra_data";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(getString(R.string.movie_desc));
        }

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvReleaseDate);
        tvRate = findViewById(R.id.tvRate);
        ivPoster = findViewById(R.id.ivPoster);
        ivBackdrop = findViewById(R.id.ivBackdrop);

        Movie movie = getIntent().getParcelableExtra(EXTRA_DATA);

        assert movie != null;
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvRelease.setText(movie.getReleaseDate());
        tvRate.setText(""+movie.getRate());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + movie.getPoster())
                .into(ivPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + movie.getBackdrop())
                .into(ivBackdrop);

    }
}
