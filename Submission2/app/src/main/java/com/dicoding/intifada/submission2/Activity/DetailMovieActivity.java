package com.dicoding.intifada.submission2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.intifada.submission2.R;
import com.dicoding.intifada.submission2.model.Movie;

public class DetailMovieActivity extends AppCompatActivity {

    ImageView imgPoster, imgBackground;
    TextView txtTitle, txtYear, txtOverview;

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        imgBackground = findViewById(R.id.img_imageBGMovie);
        imgPoster = findViewById(R.id.img_photoPosterMovie);
        txtTitle = findViewById(R.id.tv_titleMovie);
        txtYear = findViewById(R.id.tv_yearMovie);
        txtOverview = findViewById(R.id.tv_overview_movie);

        Movie moveMovie = this.getIntent().getParcelableExtra(EXTRA_MOVIE);

        assert moveMovie != null;
        Glide.with(this)
                .load(moveMovie.getPhoto())
                .into(imgBackground);

        Glide.with(this)
                .load(moveMovie.getPhotoPoster())
                .into(imgPoster);

        String title = moveMovie.getNameFilm();
        txtTitle.setText(title);
        String year = moveMovie.getYearFilm();
        txtYear.setText(year);
        String overview = moveMovie.getOverview();
        txtOverview.setText(overview);

    }
}
