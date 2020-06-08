package com.dicoding.intifada.sm5.myactivity;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.dicoding.intifada.sm5.adapter.FollowersItemsAdapter;
import com.dicoding.intifada.sm5.adapter.FollowingItemsAdapter;
import com.dicoding.intifada.sm5.follow.TabsPagerAdapter;
import com.dicoding.intifada.sm5.myapi.APIClientMovieTv;
import com.dicoding.intifada.sm5.myapi.APIMovieTv;
import com.dicoding.intifada.sm5.mymodel.DetailItemsParcelable;
import com.dicoding.intifada.sm5.mymodel.FollowItems;
import com.dicoding.intifada.sm5.mymodel.FollowItemsParcelable;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.dicoding.intifada.sm5.R;
import com.dicoding.intifada.sm5.adapter.MovieItemsAdapter;
import com.dicoding.intifada.sm5.adapter.TvShowItemsAdapter;
import com.dicoding.intifada.sm5.addingmethod.AllOtherMethod;
import com.dicoding.intifada.sm5.mydbadapter.FavMoviesAdapter;
import com.dicoding.intifada.sm5.mydbentity.MoviesModel;
import com.dicoding.intifada.sm5.myfragment.FavMoviesFragment;
import com.dicoding.intifada.sm5.mymodel.MovieItems;
import com.dicoding.intifada.sm5.mymodel.TvShowItems;
import com.dicoding.intifada.sm5.mystackwidget.ImageBannerWidget;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.dicoding.intifada.sm5.addingmethod.Constant.API_KEY_GITHUB;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_AVATAR_URL;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_LOGIN;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_BACK_PATH;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_OVERVIEW;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_POSTER_PATH;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_RELEASE_DATE;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_TITLE;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_VOTE_AVERAGE;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_VOTE_COUNT;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.CONTENT_URI;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.ID;

public class DetailsMovieActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = DetailsMovieActivity.class.getSimpleName();
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_FOLLOWERS = "extra_followers";
    public static final String EXTRA_WHERE_FROM = "extra_where_from";
    public static final String EXTRA_POSITION = "extra_position";
    public static final int REQUEST_FAV = 100;
    public static final int RESULT_DELETE = 301;
    public static final int RESULT_FAV = 101;

    private Toolbar toolbarDetails;
    private TextView tvMovieTitle, tvMovieDesc, tvMovieRelease, tvMovieRating, tvMovieVoteCount, tvName;
    private ImageView imgViewFromUrl, imgViewBg;
    private CardView cardViewDetails;
    private FloatingActionButton fabFavoriteFalse;
    private CoordinatorLayout containerCoord;
    private boolean isCheckFavorite;
    private String keyFavorite = "my_key"; //savepreference
    private String mySavePref = "my_savepref_favorite";
    private String strMsgSuccessInsert;
    private String strMsgSuccessDelete;

    private String movieTitle, movieDesc, movieRelease, movieRating, movieVoteCount, movieUrlPhoto, movieUrlBg, movieLogin, movieAvatar;
    private int moviesId;
    private String tvShowTitle, tvShowDesc, tvShowRelease, tvShowRating, tvShowVoteCount, tvShowUrlPhoto, tvShowUrlBg;
    private String githubUser, githubName, getGithubFollowing;
    private String userLogin;

    private MoviesModel moviesModel;
    private int position;
    private boolean isEdit = false;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        //inisial
        containerCoord = findViewById(R.id.container_coordinator_detail);
        toolbarDetails = findViewById(R.id.toolbar_detail);
        CollapsingToolbarLayout collapse = findViewById(R.id.collapse_toolbar_detail);
        tvMovieTitle = findViewById(R.id.tv_title_detail);
        tvName = findViewById(R.id.tv_title_name);
        //tvMovieDesc = findViewById(R.id.tv_desc_detail);
        tvMovieRelease = findViewById(R.id.tv_release_date_detail);
        tvMovieRating = findViewById(R.id.tv_rating_detail);
        tvMovieVoteCount = findViewById(R.id.tv_vote_count_detail);
        imgViewFromUrl = findViewById(R.id.img_movie_photo_detail);
        imgViewBg = findViewById(R.id.img_bg_detail);
        cardViewDetails = findViewById(R.id.card_view_img_detail);
        fabFavoriteFalse = findViewById(R.id.fab_favorite_false);
        fabFavoriteFalse.setOnClickListener(this);
        strMsgSuccessInsert = getResources().getString(R.string.str_msg_add_fav);
        strMsgSuccessDelete = getResources().getString(R.string.str_msg_delete_fav);
        collapse.setExpandedTitleColor(Color.argb(0, 0, 0, 0));

        //callmethod
        setActionBarToolbar();
        getDataParceable();
        checkingFavorite();
        //getMyData();
        getIntentFollowers();
        getIntentFollowing();

        String whereFrom = getIntent().getStringExtra(EXTRA_WHERE_FROM);

        if (whereFrom.equals(FavMoviesAdapter.TAG) || (whereFrom.equals(FavMoviesFragment.TAG))) {
            moviesModel = getIntent().getParcelableExtra(EXTRA_MOVIE);
            if (moviesModel != null) {
                position = getIntent().getIntExtra(EXTRA_POSITION, 0);
                isEdit = true;
            } else {
                moviesModel = new MoviesModel();
            }
        }

        uri = getIntent().getData();
        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) moviesModel = new MoviesModel(cursor);
                cursor.close();
            }
        }

        /*Fragment*/
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public String getMyData() {
        String whereFrom = getIntent().getStringExtra(EXTRA_WHERE_FROM);
        if (whereFrom.equals(MovieItemsAdapter.TAG)) {
            MovieItems movieItems = getIntent().getParcelableExtra(EXTRA_MOVIE);
            if (movieItems != null) {
                userLogin = movieItems.getLogin();
            }
        } else if (whereFrom.equals(FavMoviesAdapter.TAG) || (whereFrom.equals(FavMoviesFragment.TAG))) {
            MoviesModel moviesModel = getIntent().getParcelableExtra(EXTRA_MOVIE);
            if (moviesModel != null) {
                //Timber.d("Data%s", moviesModel.getTitle());
                userLogin = moviesModel.getTitle();
            }
        }

        return userLogin;
    }

    private void getIntentFollowers() {
        String whereFrom = getIntent().getStringExtra(EXTRA_WHERE_FROM);
        /*Log.e("Intent", whereFrom);
        Log.e("FollowersItemsAdapter", FollowersItemsAdapter.TAG);
        Log.e("MovieItemsAdapter", MovieItemsAdapter.TAG);*/
        if (whereFrom.equals(FollowersItemsAdapter.TAG)) { //for details TabMoviesFragment
            //Log.d("FollowersItemsAdapter", "userLogin");
            FollowItemsParcelable githubFollow = getIntent().getParcelableExtra(EXTRA_FOLLOWERS);
            if (githubFollow != null) {
                userLogin = githubFollow.getLogin();
                //Log.d("Intent", userLogin);
                movieTitle = githubFollow.getLogin();
                keyFavorite = movieTitle; //key
                moviesId = githubFollow.getId();
                tvMovieTitle.setText(movieTitle);
                movieUrlPhoto = githubFollow.getAvatar_url();
                movieUrlBg = githubFollow.getAvatar_url();
                if (movieUrlPhoto != null)
                    Glide.with(getApplicationContext()).load(githubFollow.getAvatar_url()).into(imgViewFromUrl);
                if (movieUrlBg != null)
                    Glide.with(getApplicationContext()).load(githubFollow.getAvatar_url()).into(imgViewBg);
            }
        }
    }

    private void getIntentFollowing() {
        String whereFrom = getIntent().getStringExtra(EXTRA_WHERE_FROM);
        /*Log.e("Intent", whereFrom);
        Log.e("FollowersItemsAdapter", FollowersItemsAdapter.TAG);
        Log.e("MovieItemsAdapter", MovieItemsAdapter.TAG);*/
        if (whereFrom.equals(FollowingItemsAdapter.TAG)) { //for details TabMoviesFragment
            //Log.d("FollowersItemsAdapter", "userLogin");
            FollowItemsParcelable githubFollow = getIntent().getParcelableExtra(EXTRA_FOLLOWERS);
            if (githubFollow != null) {
                userLogin = githubFollow.getLogin();
                //Log.d("Intent", userLogin);
                movieTitle = githubFollow.getLogin();
                keyFavorite = movieTitle; //key
                moviesId = githubFollow.getId();
                tvMovieTitle.setText(movieTitle);
                movieUrlPhoto = githubFollow.getAvatar_url();
                movieUrlBg = githubFollow.getAvatar_url();
                if (movieUrlPhoto != null)
                    Glide.with(getApplicationContext()).load(githubFollow.getAvatar_url()).into(imgViewFromUrl);
                if (movieUrlBg != null)
                    Glide.with(getApplicationContext()).load(githubFollow.getAvatar_url()).into(imgViewBg);
            }
        }
    }

    private void setTextDetail(String login){
        APIMovieTv apiMovieTv = APIClientMovieTv.getClientGithub().create(APIMovieTv.class);
        Call<DetailItemsParcelable> call = apiMovieTv.getDetailParcelable(API_KEY_GITHUB, login);
        call.enqueue(new Callback<DetailItemsParcelable>() {
            @SuppressLint("TimberArgCount")
            @Override
            public void onResponse(@NotNull Call<DetailItemsParcelable> call, @NotNull Response<DetailItemsParcelable> response) {

                assert response.body() != null;
                if(response.body().getName() != null) {
                    Timber.tag("Users").d(response.body().getName());
                    tvName.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(@NotNull Call<DetailItemsParcelable> users, @NotNull Throwable t) {
                Timber.d(t);
            }
        });
    }


    private void getDataParceable() { //this is get DATA when click
        String whereFrom = getIntent().getStringExtra(EXTRA_WHERE_FROM);
        String pathImg = "https://image.tmdb.org/t/p/w500";
        cardViewDetails.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_scale_animation));

        if (whereFrom.equals(MovieItemsAdapter.TAG)) { //for details TabMoviesFragment
            MovieItems movieItems = getIntent().getParcelableExtra(EXTRA_MOVIE);
            if (movieItems != null) {
                //Timber.d("Login%s", movieItems.getLogin());
                /**/
                githubUser = movieItems.getLogin();
                setTextDetail(githubUser);

                /**/
                //Log.d("username", movieItems.getLogin());
                movieTitle = movieItems.getLogin();
                keyFavorite = movieTitle; //key
                tvMovieTitle.setText(movieTitle);
                moviesId = movieItems.getId();
                //tvMovieTitle.setText(movieTitle);
                movieUrlPhoto = movieItems.getAvatar_url();
                movieUrlBg = movieItems.getAvatar_url();
                /*movieTitle = movieItems.getTitle();
                keyFavorite = movieTitle; //key
                movieDesc = movieItems.getOverview();
                movieRelease = movieItems.getRelease_date();
                movieRating = movieItems.getVote_average().toString();
                movieVoteCount = movieItems.getVote_count();
                movieUrlPhoto = movieItems.getPoster_path();
                movieUrlBg = movieItems.getBackdrop_path();*/

                /*AllOtherMethod allOtherMethod = new AllOtherMethod();
                String movieDate = allOtherMethod.changeFormatDate(movieRelease);
                String movieYearRelease = allOtherMethod.getLastYear(movieDate);

                tvMovieTitle.setText(String.format(movieTitle + " (%s)", movieYearRelease));
                tvMovieDesc.setText(movieDesc);
                tvMovieRelease.setText(movieDate);
                tvMovieRating.setText(movieRating);
                tvMovieVoteCount.setText(movieVoteCount);*/
                if (movieUrlPhoto != null)
                    Glide.with(getApplicationContext()).load(movieItems.getAvatar_url()).into(imgViewFromUrl);
                if (movieUrlBg != null)
                    Glide.with(getApplicationContext()).load(movieItems.getAvatar_url()).into(imgViewBg);
            }
        } else if (whereFrom.equals(TvShowItemsAdapter.TAG)) { //for details TabTvShowFragment
            TvShowItems tvShowItems = getIntent().getParcelableExtra(EXTRA_MOVIE);
            if (tvShowItems != null) {
                tvShowTitle = tvShowItems.getName();
                keyFavorite = tvShowTitle; //key
                tvShowDesc = tvShowItems.getOverview();
                tvShowRelease = tvShowItems.getFirst_air_date();
                tvShowRating = tvShowItems.getVote_average().toString();
                tvShowVoteCount = tvShowItems.getVote_count();
                tvShowUrlPhoto = tvShowItems.getPoster_path();
                tvShowUrlBg = tvShowItems.getBackdrop_path();

                AllOtherMethod allOtherMethod = new AllOtherMethod();
                String tvShowDate = allOtherMethod.changeFormatDate(tvShowRelease);
                String tvShowYearRelease = allOtherMethod.getLastYear(tvShowDate);

                tvMovieTitle.setText(String.format(tvShowTitle + " (%s)", tvShowYearRelease));
                tvMovieDesc.setText(tvShowDesc);
                tvMovieRelease.setText(tvShowDate);
                tvMovieRating.setText(tvShowRating);
                tvMovieVoteCount.setText(tvShowVoteCount);
                if (tvShowUrlPhoto != null)
                    Glide.with(getApplicationContext()).load(pathImg + tvShowUrlPhoto).into(imgViewFromUrl);
                if (tvShowUrlBg != null)
                    Glide.with(getApplicationContext()).load(pathImg + tvShowUrlBg).into(imgViewBg);
            }
        } else if (whereFrom.equals(FavMoviesAdapter.TAG) || (whereFrom.equals(FavMoviesFragment.TAG))) { //for details MoviesAdapter from dbroom
            MoviesModel moviesModel = getIntent().getParcelableExtra(EXTRA_MOVIE);
            if (moviesModel != null) {
                moviesId = moviesModel.getId();
                movieTitle = moviesModel.getTitle();
                keyFavorite = movieTitle; //key
                movieDesc = moviesModel.getOverview();
                movieRelease = moviesModel.getRelease_date();
                movieRating = moviesModel.getVote_average().toString();
                movieVoteCount = moviesModel.getVote_count();
                movieUrlPhoto = moviesModel.getPoster_path();
                movieUrlBg = moviesModel.getBackdrop_path();

                /*AllOtherMethod allOtherMethod = new AllOtherMethod();
                String movieDate = allOtherMethod.changeFormatDate(movieRelease);
                String movieYearRelease = allOtherMethod.getLastYear(movieDate);*/

                /*tvMovieTitle.setText(String.format(movieTitle + " (%s)", movieYearRelease));
                tvMovieDesc.setText(movieDesc);
                tvMovieRelease.setText(movieDate);
                tvMovieRating.setText(movieRating);
                tvMovieVoteCount.setText(movieVoteCount);*/
                Timber.d("getFavfromFav : %s", new Gson().toJson(moviesModel));
                tvMovieTitle.setText(movieTitle);
                //tvMovieDesc.setText("movieDescmovieDe\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nscmovi\neDescmovieDescmo\nvieDescmovieDes\ncmovieDescmovieDesc\nmovieDescmovieD4\nescmovieDescmo3\nvieDescmovieDesc2\nmovieDescmovieDescmovieDescmovieDescmovieDescmovieDescmovieDesc1\nmovieDescmovieDescmovieDescmovieDescmovieDescmovieDesc Dien");
                if (movieUrlPhoto != null)
                    Glide.with(getApplicationContext()).load(movieUrlPhoto).into(imgViewFromUrl);
                if (movieUrlBg != null)
                    Glide.with(getApplicationContext()).load(movieUrlBg).into(imgViewBg);
            }
        }
    }

    private void setActionBarToolbar() {
        setSupportActionBar(toolbarDetails);
        toolbarDetails.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbarDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setMovies() { //Insert
        Intent intent = new Intent();
        intent.putExtra(EXTRA_MOVIE, moviesModel);
        intent.putExtra(EXTRA_POSITION, position);

        MoviesModel moviesModel = new MoviesModel();
        moviesModel.setId(moviesId);
        moviesModel.setTitle(movieTitle);
        moviesModel.setRelease_date(movieRelease);
        //moviesModel.setVote_average(Double.parseDouble(movieRating));
        moviesModel.setVote_count(movieVoteCount);
        moviesModel.setOverview(movieDesc);
        moviesModel.setPoster_path(movieUrlPhoto);
        moviesModel.setBackdrop_path(movieUrlBg);

        moviesModel.setLogin(movieLogin);
        moviesModel.setAvatar_url(movieAvatar);

        ContentValues values = new ContentValues();
        values.put(ID, moviesId);
        values.put(COLUMN_TITLE, movieTitle);
        values.put(COLUMN_RELEASE_DATE, movieRelease);
        values.put(COLUMN_VOTE_AVERAGE, movieRating);
        values.put(COLUMN_VOTE_COUNT, movieVoteCount);
        values.put(COLUMN_OVERVIEW, movieDesc);
        values.put(COLUMN_POSTER_PATH, movieUrlPhoto);
        values.put(COLUMN_BACK_PATH, movieUrlBg);

        values.put(COLUMN_LOGIN, movieLogin);
        values.put(COLUMN_AVATAR_URL, movieAvatar);

        getContentResolver().insert(CONTENT_URI, values);
        showSnackBar(movieTitle + " " + strMsgSuccessInsert);
        setResult(RESULT_FAV, intent);
        updateMyfavmoviesWidget();
    }

    private void deleteMovies() { //delete
        Intent intent = new Intent();
        intent.putExtra(EXTRA_POSITION, 0);
        getContentResolver().delete(uri, null, null);
        showSnackBar(movieTitle + " " + strMsgSuccessDelete);
        setResult(RESULT_DELETE, intent);
        updateMyfavmoviesWidget();
    }

    private void updateMyfavmoviesWidget() {
        Context context = getApplicationContext();
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, ImageBannerWidget.class);
        int[] idAppWidget = widgetManager.getAppWidgetIds(componentName);
        widgetManager.notifyAppWidgetViewDataChanged(idAppWidget, R.id.stack_view);
    }

    private void showSnackBar(String msg) {
        @SuppressLint("WrongConstant") Snackbar snackbar = Snackbar.make(containerCoord, msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fab_favorite_false) {
            Timber.d("ClickedFav");
            setIsFavorite();
        }
    }

    private void tesPref(boolean isFavor) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(mySavePref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(keyFavorite, isFavor);
        editor.apply();
    }

    private boolean radRef() {
        SharedPreferences mSharedPreferences = this.getSharedPreferences(mySavePref, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(keyFavorite, false);
    }

    private void setIsFavorite() {
        String whereFrom = getIntent().getStringExtra(EXTRA_WHERE_FROM);
        if (isCheckFavorite) {
            boolean isFavorite = false;
            tesPref(isFavorite);
            checkingFavorite();
            //delete
            if ((whereFrom.equals(FavMoviesFragment.TAG) || (whereFrom.equals(FavMoviesAdapter.TAG))
                    || (whereFrom.equals(FollowersItemsAdapter.TAG)) || (whereFrom.equals(FollowingItemsAdapter.TAG))
                    || (whereFrom.equals(MovieItemsAdapter.TAG)))) {
                deleteMovies();
            }

        } else {
            boolean isFavorite = true;
            tesPref(isFavorite);
            checkingFavorite();
            //insert
            if ((whereFrom.equals(FavMoviesFragment.TAG) || (whereFrom.equals(FavMoviesAdapter.TAG))
                    || (whereFrom.equals(FollowersItemsAdapter.TAG)) || (whereFrom.equals(FollowingItemsAdapter.TAG))
                    || (whereFrom.equals(MovieItemsAdapter.TAG)))) {
                setMovies();
            }
        }
    }

    private void checkingFavorite() {
        boolean isFavorite = radRef();
        if (isFavorite) {
            fabFavoriteFalse.setImageResource(R.drawable.ic_favorite_true_24dp);
            isCheckFavorite = true;
        } else {
            fabFavoriteFalse.setImageResource(R.drawable.ic_favorite_border_before);
            isCheckFavorite = false;
        }
    }
}
