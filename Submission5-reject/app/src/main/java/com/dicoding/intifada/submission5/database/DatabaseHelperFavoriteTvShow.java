package com.dicoding.intifada.submission5.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteTvShowColumns.TABLE_NAME;

public class DatabaseHelperFavoriteTvShow extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbfavoritetvshow";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FAV_TVSHOW = String.format("CREATE TABLE %s "
                    + " (%s INTEGER PRIMARY KEY,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.FavoriteTvShowColumns.TVSHOW_ID,
            DatabaseContract.FavoriteTvShowColumns.TITLE,
            DatabaseContract.FavoriteTvShowColumns.OVERVIEW,
            DatabaseContract.FavoriteTvShowColumns.POSTER,
            DatabaseContract.FavoriteTvShowColumns.RELEASE,
            DatabaseContract.FavoriteTvShowColumns.RATE);

    public DatabaseHelperFavoriteTvShow(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAV_TVSHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.FavoriteTvShowColumns.TABLE_NAME);
        onCreate(db);
    }
}
