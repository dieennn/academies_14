package com.dicoding.intifada.consumerapp.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    private static final String AUTHORITY = "com.dicoding.intifada.sm5";
    private static final String SCHEME = "content";

    private static String TABLE_MOVIE = "tbl_moviesm";
    public static final class MovieColoumns implements BaseColumns {
        public static final String ID = "id";
        public static String TITLE_MV = "title";
        public static String RELEASE_MV = "release_date";
        public static String OVERVIEW_MV = "overview";
        public static String SCORE_MV = "vote_count";
        public static String POSTER_MV = "poster_path";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }

}
