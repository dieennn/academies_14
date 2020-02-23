package com.dicoding.intifada.submission4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.intifada.submission4.R;
import com.dicoding.intifada.submission4.activity.DetailActivity;
import com.dicoding.intifada.submission4.adapter.MovieAdapter;
import com.dicoding.intifada.submission4.model.Movie;
import com.dicoding.intifada.submission4.model.MovieViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ProgressBar progressBar;
    public static final String TYPE = MovieFragment.class.getSimpleName();

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final MovieAdapter adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA, data);
                intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        if(getActivity() != null){
            MovieViewModel moviesViewModel = new ViewModelProvider(getActivity(),
                    new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);

            moviesViewModel.setListMovie();
            showLoading(true);

            moviesViewModel.getListMovies().observe(getActivity(), new Observer<ArrayList<Movie>>() {
                @Override
                public void onChanged(ArrayList<Movie> movies) {
                    if(movies != null){
                        adapter.setData(movies);
                        showLoading(false);
                    }
                }
            });
        }

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
