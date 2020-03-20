package com.dicoding.intifada.favorite.activity.interfaces;

import com.dicoding.intifada.favorite.recyclerview.model.Film;

import java.util.ArrayList;

public interface LoadTVsCallback {

    void preExecute();
    void postExecute(ArrayList<Film> tvShowItems);

}
