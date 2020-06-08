package com.dicoding.intifada.consumerapp.activity.interfaces;

import com.dicoding.intifada.consumerapp.recyclerview.model.Film;

import java.util.ArrayList;

public interface LoadTVsCallback {

    void preExecute();
    void postExecute(ArrayList<Film> tvShowItems);

}
