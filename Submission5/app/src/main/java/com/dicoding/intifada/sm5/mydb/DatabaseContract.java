package com.dicoding.intifada.sm5.mydb;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;



public final class DatabaseContract {
    public static final String AUTHORITY = "com.dicoding.intifada.sm5";
    private static final String SCHEME = "content";

    public static final class MovieColumns implements BaseColumns {
        public static final String TABLE_NAME = "tbl_moviesm";
        public static final String ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_BACK_PATH = "backdrop_path";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_AVATAR_URL = "avatar_url";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }
}
