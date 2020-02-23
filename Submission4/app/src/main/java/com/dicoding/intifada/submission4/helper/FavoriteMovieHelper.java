package com.dicoding.intifada.submission4.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dicoding.intifada.submission4.database.DatabaseContract;
import com.dicoding.intifada.submission4.database.DatabaseHelperFavoriteMovie;

import static com.dicoding.intifada.submission4.database.DatabaseContract.FavoriteMovieColumns.MOVIE_ID;
import static com.dicoding.intifada.submission4.database.DatabaseContract.FavoriteMovieColumns.TABLE_NAME;

public class FavoriteMovieHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelperFavoriteMovie databaseHelper;
    private static FavoriteMovieHelper INSTANCE;

    private static SQLiteDatabase database;

    private FavoriteMovieHelper(Context context){
        databaseHelper = new DatabaseHelperFavoriteMovie(context);
    }

    public static FavoriteMovieHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new FavoriteMovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();

        if(database.isOpen()){
            database.close();
        }
    }

    public Cursor queryAll(){
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.FavoriteMovieColumns.MOVIE_ID + " ASC"
        );
    }

    public void insert(ContentValues values){
        database.insert(DATABASE_TABLE, null, values);
    }

    public void deleteById(String id){
        database.delete(DATABASE_TABLE, MOVIE_ID + " = ?", new String[]{id});
    }
}
