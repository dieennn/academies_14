package com.dicoding.intifada.submission4.helper;

import android.database.Cursor;

import com.dicoding.intifada.submission4.database.DatabaseContract;
import com.dicoding.intifada.submission4.model.FavoriteModel;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<FavoriteModel> mapCursorToArrayList(Cursor cursor){
        ArrayList<FavoriteModel> favoriteModels = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.MOVIE_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.TITLE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.OVERVIEW));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.POSTER));
            int rate = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.RATE));
            String release = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.RELEASE));

            favoriteModels.add(new FavoriteModel(id, title, overview, poster, release, rate));
        }

        return favoriteModels;
    }

    public static ArrayList<FavoriteModel> mapCursorToArrayListTV(Cursor cursor){
        ArrayList<FavoriteModel> favoriteModels = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvShowColumns.TVSHOW_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvShowColumns.TITLE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvShowColumns.OVERVIEW));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvShowColumns.POSTER));
            int rate = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvShowColumns.RATE));
            String release = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvShowColumns.RELEASE));

            favoriteModels.add(new FavoriteModel(id, title, overview, poster, release, rate));
        }

        return favoriteModels;
    }

    public static FavoriteModel mapCursorToObject(Cursor cursor){

        cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns._ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.TITLE));
        String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.OVERVIEW));
        String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.POSTER));
        int rate = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.RATE));
        String release = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteMovieColumns.RELEASE));

        return new FavoriteModel(id, title, overview, poster, release, rate);
    }
}
