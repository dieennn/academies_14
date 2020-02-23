package com.dicoding.intifada.submission4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.dicoding.intifada.submission4.database.DatabaseContract.FavoriteMovieColumns.TABLE_NAME;

public class DatabaseHelperFavoriteMovie extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbfavoritemovie";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FAV_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.FavoriteMovieColumns.MOVIE_ID,
            DatabaseContract.FavoriteMovieColumns.TITLE,
            DatabaseContract.FavoriteMovieColumns.OVERVIEW,
            DatabaseContract.FavoriteMovieColumns.POSTER,
            DatabaseContract.FavoriteMovieColumns.RELEASE,
            DatabaseContract.FavoriteMovieColumns.RATE);

    public DatabaseHelperFavoriteMovie(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAV_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
