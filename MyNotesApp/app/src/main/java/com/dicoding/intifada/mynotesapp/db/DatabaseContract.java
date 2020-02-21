package com.dicoding.intifada.mynotesapp.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTHORITY = "com.dicoding.intifada.mynotesapp";
    private static final String SCHEME = "content";

    private DatabaseContract(){}

    public static final class NoteColumns implements BaseColumns {
        public static final String TABLE_NAME = "note";

        //Note title
        public static final String TITLE = "title";
        //Note description
        public static final String DESCRIPTION = "description";
        //Note date
        public static final String DATE = "date";

        // untuk membuat URI content://com.dicoding.picodiploma.mynotesapp/note
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

    }
}