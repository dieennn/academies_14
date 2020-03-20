package com.dicoding.intifada.submission5.fragment.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.intifada.submission5.R;
import com.dicoding.intifada.submission5.recyclerview.adapter.MoviesAdapter;
import com.dicoding.intifada.submission5.recyclerview.model.Film;
import com.dicoding.intifada.submission5.recyclerview.viewmodel.FilmViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragmentMovies extends Fragment {

    private MoviesAdapter adapter;
    private ProgressBar progressBarMovies;


    public SearchFragmentMovies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rcMovies = view.findViewById(R.id.rc_movies);
        rcMovies.setHasFixedSize(true);
        progressBarMovies = view.findViewById(R.id.progressBarMovie);

        rcMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));

        String query = getArguments().getString("query");

        adapter = new MoviesAdapter();
        adapter.notifyDataSetChanged();
        rcMovies.setAdapter(adapter);

        FilmViewModel moviesSearch = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(FilmViewModel.class);
        moviesSearch.setSearchMovies(query);

        showLoading(true);

        moviesSearch.getSearchMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Film>>() {
            @Override
            public void onChanged(ArrayList<Film> moviesItems) {
                if (moviesItems != null && moviesItems.size() > 0) {
                    adapter.setData(moviesItems);
                    showLoading(false);
                } else {
                    Toast.makeText(getContext(), R.string.movies_notFound, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBarMovies.setVisibility(View.VISIBLE);
        } else {
            progressBarMovies.setVisibility(View.GONE);
        }
    }
}
