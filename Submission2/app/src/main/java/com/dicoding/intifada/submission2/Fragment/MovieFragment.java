package com.dicoding.intifada.submission2.fragment;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.intifada.submission2.R;
import com.dicoding.intifada.submission2.adapter.MovieAdapter;
import com.dicoding.intifada.submission2.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();

    private String[] dataName;
    private String[] dataDate;
    private String[] dataYear;
    private String[] dataOverview;
    private TypedArray dataPoster;
    private TypedArray dataBackground;

    private String[] dataScore;
    private String[] dataLanguage;
    private String[] dataRuntime;
    private String[] dataDescription;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        list.addAll(getListMovies());
        showRecyclerList();


    }

    private void prepare() {
        dataBackground = getResources().obtainTypedArray(R.array.data_movie_bg);
        dataPoster = getResources().obtainTypedArray(R.array.data_movie_photo);
        dataName = getResources().getStringArray(R.array.data_movie_name);
        dataYear = getResources().getStringArray(R.array.data_movie_year);
        dataDate = getResources().getStringArray(R.array.data_movie_date);
        dataOverview = getResources().getStringArray(R.array.data_movie_overview);

        dataScore = getResources().getStringArray(R.array.movies_score);
        dataLanguage = getResources().getStringArray(R.array.movies_languange);
        dataRuntime = getResources().getStringArray(R.array.movies_runtime);
        dataDescription = getResources().getStringArray(R.array.data_movie_overview);

    }

    private void addItem(){
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i<dataName.length; i++) {
            Movie movie = new Movie();
            movie.setPhoto(dataBackground.getResourceId(i, -1));
            movie.setPhotoPoster(dataPoster.getResourceId(i, -1));
            movie.setNameFilm(dataName[i]);
            movie.setDateFilm(dataDate[i]);
            movie.setYearFilm(dataYear[i]);
            movie.setOverview(dataOverview[i]);

            movie.setScore(dataScore[i]);
            movie.setLanguage(dataLanguage[i]);
            movie.setRuntime(dataRuntime[i]);
            movie.setDescription(dataDescription[i]);

            movies.add(movie);
        }
    }

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter movieAdapter = new MovieAdapter(list);
        rvMovies.setAdapter(movieAdapter);
        movieAdapter.getItemCount();
        movieAdapter.notifyDataSetChanged();
    }

    private ArrayList<Movie> getListMovies() {
        String[] dataNameMovie = getResources().getStringArray(R.array.data_movie_name);
        String[] dataDateMovie = getResources().getStringArray(R.array.data_movie_date);
        String[] dataYearMovie = getResources().getStringArray(R.array.data_movie_year);
        String[] dataOverviewMovie = getResources().getStringArray(R.array.data_movie_overview);
        @SuppressLint("Recycle") TypedArray dataPhotoMovie =
                getResources().obtainTypedArray(R.array.data_movie_bg);
        @SuppressLint("Recycle") TypedArray dataPosterMovie =
                getResources().obtainTypedArray(R.array.data_movie_photo);

        String[] dataScoreMovie = getResources().getStringArray(R.array.movies_score);
        String[] dataLanguageMovie = getResources().getStringArray(R.array.movies_languange);
        String[] dataRuntimeMovie = getResources().getStringArray(R.array.movies_runtime);
        String[] dataDescriptionMovie = getResources().getStringArray(R.array.data_movie_overview);

        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataNameMovie.length; i++) {
            Movie movie = new Movie();
            movie.setNameFilm(dataNameMovie[i]);
            movie.setDateFilm(dataDateMovie[i]);
            movie.setYearFilm(dataYearMovie[i]);
            movie.setOverview(dataOverviewMovie[i]);
            movie.setPhoto(dataPhotoMovie.getResourceId(i, -1));
            movie.setPhotoPoster(dataPosterMovie.getResourceId(i, -1));

            movie.setScore(dataScoreMovie[i]);
            movie.setLanguage(dataLanguageMovie[i]);
            movie.setRuntime(dataRuntimeMovie[i]);
            movie.setDescription(dataDescriptionMovie[i]);
            
            listMovie.add(movie);
        }
        return listMovie;
    }

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        rvMovies = view.findViewById(R.id.rv_movies);

        showRecyclerList();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepare();
        addItem();
    }
}
