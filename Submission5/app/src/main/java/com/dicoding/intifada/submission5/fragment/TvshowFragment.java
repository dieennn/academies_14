package com.dicoding.intifada.submission5.fragment;


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

import com.dicoding.intifada.submission5.R;
import com.dicoding.intifada.submission5.activity.DetailActivity;
import com.dicoding.intifada.submission5.adapter.MovieAdapter;
import com.dicoding.intifada.submission5.model.Movie;
import com.dicoding.intifada.submission5.model.TvshowViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private ProgressBar progressBar;
    public static final String TYPE = TvShowFragment.class.getSimpleName();

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_tv_show);
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

        adapter.setOnItemFavoriteCallback(new MovieAdapter.OnItemFavoriteCallback() {
            @Override
            public void onItemFavorited(Movie data, int position) {

                if(data.isFavorite()){
                    data.setFavorite(false);
                }else {
                    data.setFavorite(true);
                }

                adapter.updateItem(position, data);
            }
        });

        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        if(getActivity() != null){
            TvshowViewModel tvshowViewModel;
            tvshowViewModel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(TvshowViewModel.class);

            tvshowViewModel.setListMovie();
            showLoading(true);

            tvshowViewModel.getListMovies().observe(getActivity(), new Observer<ArrayList<Movie>>() {
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
