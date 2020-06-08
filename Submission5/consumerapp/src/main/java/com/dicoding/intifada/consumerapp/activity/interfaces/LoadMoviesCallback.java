package com.dicoding.intifada.consumerapp.activity.interfaces;

import com.dicoding.intifada.consumerapp.recyclerview.model.Film;

import java.util.ArrayList;

public interface LoadMoviesCallback {

    void preExecute();
    void postExecute(ArrayList<Film> movies);

}
