package com.dicoding.intifada.submission5.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dicoding.intifada.submission5.database.DatabaseContract;
import com.dicoding.intifada.submission5.database.DatabaseHelperFavoriteTvShow;

import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteTvShowColumns.TABLE_NAME;
import static com.dicoding.intifada.submission5.database.DatabaseContract.FavoriteTvShowColumns.TVSHOW_ID;

public class FavoriteTvShowHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelperFavoriteTvShow databaseHelper;
    private static FavoriteTvShowHelper INSTANCE;

    private static SQLiteDatabase database;

    private FavoriteTvShowHelper(Context context){
        databaseHelper = new DatabaseHelperFavoriteTvShow(context);
    }

    public static FavoriteTvShowHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new FavoriteTvShowHelper(context);
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
                TVSHOW_ID + " ASC"
        );
    }

    public Cursor queryById(String id){
        return database.query(DATABASE_TABLE, null,
                DatabaseContract.FavoriteTvShowColumns.TVSHOW_ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insert(ContentValues values){
        if(database == null){
            Log.d("error kak", "success kak");
            return database.insert(DATABASE_TABLE, null, values);
        }else {
            return database.insert(DATABASE_TABLE, null, values);
        }

    }

    public int update(String id, ContentValues values){
        return database.update(DATABASE_TABLE, values, TVSHOW_ID + " = ?", new String[]{id});
    }

    public int deleteById(String id){
        return database.delete(DATABASE_TABLE, TVSHOW_ID + " = ?", new String[]{id});
    }
}
