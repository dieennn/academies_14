package com.dicoding.intifada.submission5.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dicoding.intifada.submission5.R;
import com.dicoding.intifada.submission5.fragment.MovieFragment;
import com.dicoding.intifada.submission5.fragment.TvShowFragment;
import com.dicoding.intifada.submission5.helper.FavoriteMovieHelper;
import com.dicoding.intifada.submission5.helper.FavoriteTvShowHelper;
import com.dicoding.intifada.submission5.model.Movie;

import java.util.Objects;

import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteMovieColumns.MOVIE_ID;
import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteMovieColumns.OVERVIEW;
import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteMovieColumns.POSTER;
import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteMovieColumns.RATE;
import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteMovieColumns.RELEASE;
import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteMovieColumns.TITLE;
import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteTvShowColumns.TVSHOW_ID;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle, tvOverview, tvRelease, tvRate;
    ImageView ivPoster, ivBackdrop;
    Button btnFavorite;
    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_TYPE = "extra_type";

    private FavoriteMovieHelper favMovieHelper;
    private FavoriteTvShowHelper favTVShowHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /*if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(getString(R.string.movie_desc));
        }*/

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvReleaseDate);
        tvRate = findViewById(R.id.tvRate);
        ivPoster = findViewById(R.id.ivPoster);
        ivBackdrop = findViewById(R.id.ivBackdrop);
        btnFavorite = findViewById(R.id.btnFavorite);

        final Movie movie = getIntent().getParcelableExtra(EXTRA_DATA);

        assert movie != null;
        Objects.requireNonNull(getSupportActionBar()).setTitle(movie.getTitle());
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvRelease.setText(movie.getReleaseDate());
        tvRate.setText("" + movie.getRate());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + movie.getPoster())
                .into(ivPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + movie.getBackdrop())
                .into(ivBackdrop);


        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(TITLE, movie.getTitle());
                values.put(OVERVIEW, movie.getOverview());
                values.put(POSTER, movie.getPoster());
                values.put(RELEASE, movie.getReleaseDate());
                values.put(RATE, movie.getRate());
                String type = getIntent().getStringExtra(EXTRA_TYPE);

                if (type != null) {
                    if (type.equals(MovieFragment.TYPE)) {
                        try {
                            values.put(MOVIE_ID, movie.getId());
                            favMovieHelper = FavoriteMovieHelper.getInstance(getApplicationContext());
                            favMovieHelper.open();
                            favMovieHelper.insert(values);
                            Toast.makeText(DetailActivity.this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            // This will catch any exception, because they are all descended from Exception
                            Log.d("das", Objects.requireNonNull(e.getMessage()));
                            Toast.makeText(DetailActivity.this,"gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (type.equals(TvShowFragment.TYPE)) {
                        values.put(TVSHOW_ID, movie.getId());
                        favTVShowHelper = FavoriteTvShowHelper.getInstance(getApplicationContext());
                        favTVShowHelper.open();
                        favTVShowHelper.insert(values);
                        Toast.makeText(DetailActivity.this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
