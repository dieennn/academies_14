package com.dicoding.intifada.submission5.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    private static final String AUTHORITY = "com.dicoding.intifada.submission5";
    private static final String SCHEME = "content";

    private DatabaseContract(){}

    public static final class FavoriteMovieColumns implements BaseColumns {
        public static final String TABLE_NAME = "favorite_movie";

        public static final String MOVIE_ID = "movie_id";

        public static final String TITLE = "title";

        public static final String OVERVIEW = "overview";

        public static final String POSTER = "poster";

        public static final String RELEASE = "release";

        public static final String RATE = "rate";

        // untuk membuat URI content://com.dicoding.picodiploma.mynotesapp/note
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static final class FavoriteTvShowColumns implements BaseColumns {
        public static final String TABLE_NAME = "favorite_tvshow";

        public static final String TVSHOW_ID = "tvshow_id";

        public static final String TITLE = "title";

        public static final String OVERVIEW = "overview";

        public static final String POSTER = "poster";

        public static final String RELEASE = "release";

        public static final String RATE = "rate";

        // untuk membuat URI content://com.dicoding.picodiploma.mynotesapp/note
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }
}