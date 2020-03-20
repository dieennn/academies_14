package com.dicoding.intifada.favorite.activity.interfaces;

import com.dicoding.intifada.favorite.recyclerview.model.Film;

import java.util.ArrayList;

public interface LoadMoviesCallback {

    void preExecute();
    void postExecute(ArrayList<Film> movies);

}
