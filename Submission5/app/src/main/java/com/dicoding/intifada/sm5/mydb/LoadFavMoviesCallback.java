package com.dicoding.intifada.sm5.mydb;

import android.database.Cursor;



public interface LoadFavMoviesCallback {
    void postExecute(Cursor cursor);
    void preExecute();
}
