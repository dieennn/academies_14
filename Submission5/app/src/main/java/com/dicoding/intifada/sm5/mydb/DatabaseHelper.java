package com.dicoding.intifada.sm5.mydb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_list_movie";
    private static final int DATABASE_VERSION = 2;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s" +
                    "(%s INTEGER PRIMARY KEY," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s TEXT," +
                    "%s TEXT)",
            DatabaseContract.MovieColumns.TABLE_NAME,
            DatabaseContract.MovieColumns.ID,
            DatabaseContract.MovieColumns.COLUMN_TITLE,
            DatabaseContract.MovieColumns.COLUMN_RELEASE_DATE,
            DatabaseContract.MovieColumns.COLUMN_VOTE_AVERAGE,
            DatabaseContract.MovieColumns.COLUMN_VOTE_COUNT,
            DatabaseContract.MovieColumns.COLUMN_OVERVIEW,
            DatabaseContract.MovieColumns.COLUMN_POSTER_PATH,
            DatabaseContract.MovieColumns.COLUMN_BACK_PATH,
            DatabaseContract.MovieColumns.COLUMN_LOGIN,
            DatabaseContract.MovieColumns.COLUMN_AVATAR_URL

    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.MovieColumns.TABLE_NAME);
        onCreate(db);
    }
}
