package com.dicoding.intifada.favorite.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.intifada.favorite.R;
import com.dicoding.intifada.favorite.activity.interfaces.LoadMoviesCallback;
import com.dicoding.intifada.favorite.db.DatabaseContract;
import com.dicoding.intifada.favorite.helper.MovieMappingHelper;
import com.dicoding.intifada.favorite.recyclerview.adapter.MoviesAdapter;
import com.dicoding.intifada.favorite.recyclerview.model.Film;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements LoadMoviesCallback {

    private MoviesAdapter adapter;
    private ProgressBar progressBarMoviesFavorit;

    private ArrayList<Film> items = new ArrayList<>();
    private RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.rc_movies_favorite);
        rvMovies.setHasFixedSize(true);

        progressBarMoviesFavorit = findViewById(R.id.progressbarMovieFavorite);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(DatabaseContract.MovieColoumns.CONTENT_URI, true, myObserver);

        showRecyclerView();

//        new FavoriteMovieFragment.LoadMoviesAsync(movieHelper, this).execute();
        new LoadMoviesAsync(this, this).execute();
    }

    private void showRecyclerView() {
        adapter = new MoviesAdapter();
        adapter.notifyDataSetChanged();
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(adapter);
    }

    @Override
    public void preExecute() {
        progressBarMoviesFavorit.setVisibility(View.VISIBLE);
    }

    @Override
    public void postExecute(ArrayList<Film> movies) {
        progressBarMoviesFavorit.setVisibility(View.INVISIBLE);
        adapter.setData(movies);
        items.addAll(movies);
    }

    @SuppressLint("StaticFieldLeak")
    private static class LoadMoviesAsync  extends AsyncTask<Void, Void, ArrayList<Film>> {

        private final WeakReference<LoadMoviesCallback> weakCallback;
        private final WeakReference<Context> weakContext;

        private LoadMoviesAsync(Context context, LoadMoviesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Film> doInBackground(Void... voids) {
            Context context = weakContext.get();
            Cursor dataCursor = context.getContentResolver().query(DatabaseContract.MovieColoumns.CONTENT_URI, null, null, null, null);
            return MovieMappingHelper.mapCursorToArrayList(Objects.requireNonNull(dataCursor));
        }

        @Override
        protected void onPostExecute(ArrayList<Film> films) {
            super.onPostExecute(films);
            weakCallback.get().postExecute(films);
        }
    }

    public static class DataObserver extends ContentObserver {
        final Context context;
        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadMoviesAsync(context, (LoadMoviesCallback) context).execute();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
