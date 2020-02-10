package com.dicoding.intifada.submission2.fragment;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.intifada.submission2.R;
import com.dicoding.intifada.submission2.adapter.TvShowAdapter;
import com.dicoding.intifada.submission2.model.TvShow;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {
    private RecyclerView rvTvShow;
    private ArrayList<TvShow> list = new ArrayList<>();

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

        rvTvShow = view.findViewById(R.id.rv_tvshow);
        rvTvShow.setHasFixedSize(true);

        list.addAll(getListTv());
        showRecyclerList();
    }

    private void prepare() {
        dataBackground = getResources().obtainTypedArray(R.array.data_tv_bg);
        dataPoster = getResources().obtainTypedArray(R.array.data_tv_photo);
        dataName = getResources().getStringArray(R.array.data_tv_name);
        dataYear = getResources().getStringArray(R.array.data_tv_year);
        dataDate = getResources().getStringArray(R.array.data_tv_date);
        dataOverview = getResources().getStringArray(R.array.data_tv_overview);

        dataScore = getResources().getStringArray(R.array.movies_score);
        dataLanguage = getResources().getStringArray(R.array.movies_languange);
        dataRuntime = getResources().getStringArray(R.array.movies_runtime);
        dataDescription = getResources().getStringArray(R.array.data_movie_overview);

    }

    private void addItem(){
        ArrayList<TvShow> tvShows = new ArrayList<>();

        for (int i = 0; i<dataName.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setPhotoTv(dataBackground.getResourceId(i, -1));
            tvShow.setPhotoPosterTv(dataPoster.getResourceId(i, -1));
            tvShow.setNameTv(dataName[i]);
            tvShow.setDateTv(dataDate[i]);
            tvShow.setYearTv(dataYear[i]);
            tvShow.setOverviewTv(dataOverview[i]);

            tvShow.setScore(dataScore[i]);
            tvShow.setLanguage(dataLanguage[i]);
            tvShow.setRuntime(dataRuntime[i]);
            tvShow.setDescription(dataDescription[i]);

            tvShows.add(tvShow);
        }
    }

    private void showRecyclerList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        TvShowAdapter tvShowAdapter = new TvShowAdapter(list);
        rvTvShow.setAdapter(tvShowAdapter);
        tvShowAdapter.getItemCount();
        tvShowAdapter.notifyDataSetChanged();
    }

    public TvShowFragment() {
        // Required empty public constructor
    }

    private ArrayList<TvShow> getListTv() {
        String[] dataNameTv = getResources().getStringArray(R.array.data_tv_name);
        String[] dataDateTv = getResources().getStringArray(R.array.data_tv_date);
        String[] dataYearTv = getResources().getStringArray(R.array.data_tv_year);
        String[] dataOverviewTv = getResources().getStringArray(R.array.data_tv_overview);
        @SuppressLint("Recycle") TypedArray dataPhotoTv =
                getResources().obtainTypedArray(R.array.data_tv_bg);
        @SuppressLint("Recycle") TypedArray dataPosterTv =
                getResources().obtainTypedArray(R.array.data_tv_photo);

        String[] dataScoreMovie = getResources().getStringArray(R.array.tv_show_score);
        String[] dataLanguageMovie = getResources().getStringArray(R.array.tv_show_languange);
        String[] dataRuntimeMovie = getResources().getStringArray(R.array.tv_show_runtime);
        String[] dataDescriptionMovie = getResources().getStringArray(R.array.data_tv_overview);

        ArrayList<TvShow> listTv = new ArrayList<>();
        for (int i =0; i < dataNameTv.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setNameTv(dataNameTv[i]);
            tvShow.setDateTv(dataDateTv[i]);
            tvShow.setYearTv(dataYearTv[i]);
            tvShow.setOverviewTv(dataOverviewTv[i]);
            tvShow.setPhotoTv(dataPhotoTv.getResourceId(i, -1));
            tvShow.setPhotoPosterTv(dataPosterTv.getResourceId(i, -1));

            tvShow.setScore(dataScoreMovie[i]);
            tvShow.setLanguage(dataLanguageMovie[i]);
            tvShow.setRuntime(dataRuntimeMovie[i]);
            tvShow.setDescription(dataDescriptionMovie[i]);

            listTv.add(tvShow);
        }
        return listTv;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        rvTvShow = view.findViewById(R.id.rv_tvshow);

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