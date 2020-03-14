package com.dicoding.intifada.submission5.fragment.interfaces;

import com.dicoding.intifada.submission5.recyclerview.model.Film;

import java.util.ArrayList;

public interface LoadTVsCallback {

    void preExecute();
    void postExecute(ArrayList<Film> tvShowItems);

}
