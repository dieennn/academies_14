package com.dicoding.intifada.sm5.myapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIClientMovieTv {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String BASE_URL_GITHUB = "https://api.github.com/";
    private static final String BASE_URL_ALLFILE = "https://all-file.herokuapp.com/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofit_github = null;
    private static Retrofit retrofit_allFile = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientGithub() {
        if (retrofit_github == null) {
            retrofit_github = new Retrofit.Builder()
                    .baseUrl(BASE_URL_GITHUB)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit_github;
    }

    public static Retrofit getAllfile() {
        if (retrofit_allFile == null) {
            retrofit_allFile = new Retrofit.Builder()
                    .baseUrl(BASE_URL_ALLFILE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit_allFile;
    }
}
