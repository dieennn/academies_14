package com.dicoding.intifada.submission5.fragment.interfaces;

import com.dicoding.intifada.submission5.recyclerview.model.Film;

import java.util.ArrayList;

public interface LoadMoviesCallback {

    void preExecute();
    void postExecute(ArrayList<Film> movies);

}
